package com.example.wilsonhan.happydevelop.ui.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import cn.kaer.common.conf.KeConstance;
import com.unisound.sdk.service.utils.NetUtils;
import com.unisound.sdk.service.utils.ToastUtils;
import com.unisound.smartphone.R;
import com.unisound.smartphone.base.BasePresenterImpl;
import com.unisound.smartphone.net.BaseObserver;
import com.unisound.smartphone.net.BaseResponse;
import com.unisound.smartphone.net.SwitchSchedulers;
import com.unisound.smartphone.net.request.LoginInfoRequest;
import com.unisound.smartphone.net.response.CheckInfoResponse;
import com.unisound.smartphone.net.response.ConfigResponse;
import com.unisound.smartphone.receiver.CallReceiver;
import com.unisound.smartphone.ui.callrecord.CallRecordActivity;
import com.unisound.smartphone.ui.contract.HomeContract;
import com.unisound.smartphone.ui.dialnum.DialNumActivity;
import com.unisound.smartphone.ui.home.BindCheckDialog;
import com.unisound.smartphone.ui.home.ValidCheckDialog;
import com.unisound.smartphone.ui.model.HomeModel;
import com.unisound.smartphone.ui.outtask.LoginActivity;
import com.unisound.smartphone.ui.syscheck.CallCheckDialog;
import com.unisound.smartphone.ui.syscheck.NetErrorDialog;
import com.unisound.smartphone.ui.syscheck.SimErrorDialog;
import com.unisound.smartphone.ui.syscheck.SysCheckActivity;
import com.unisound.smartphone.utils.AppUtils;
import com.unisound.smartphone.utils.LoginUtils;
import com.unisound.smartphone.utils.SystemAboutUtils;
import com.unisound.smartphone.utils.SystemCheckUtils;
import com.unisound.smartphone.utils.TimeUtils;
import java.util.List;

public class HomePresenter extends BasePresenterImpl<HomeContract.View>
    implements HomeContract.Presenter {

  private Activity context;
  private HomeModel homeModel;
  private CallReceiver receiver;

  public HomePresenter(Activity context) {
    this.context = context;
    homeModel = new HomeModel();
  }

  @Override public LoginInfoRequest getLoginRequest(boolean isLogin) {
    LoginInfoRequest request = new LoginInfoRequest();
    request.setDeviceCode(AppUtils.getMeid());
    request.setUserAction(isLogin ? "login" : "logout");
    request.setUserCode(LoginUtils.getInstance().getSalerId());
    request.setOperateDate(TimeUtils.getCurrentDate(TimeUtils.NORMAL_FORMAT));
    return request;
  }

  @Override public void getDeviceConfig() {
    view.showLoadingDialog(null);
    homeModel.getDeviceConfig(AppUtils.getMeid())
        .compose(SwitchSchedulers.applySchedulers(view))
        .subscribe(new BaseObserver<ConfigResponse>() {
          @Override public void onSuccess(BaseResponse<ConfigResponse> response) {
            view.hideLoadingDialog();
            if (response != null && response.getData() != null
                && response.getData().getUpdateFieldSaler() != null) {
              view.initLoginData(response.getData().getUpdateFieldSaler().getData().getSaler());
            }
          }

          @Override public void onCodeError(BaseResponse response) {
            view.hideLoadingDialog();
            if (response != null && response.getStatus() == -2) {
              BindCheckDialog.getInstance().showDialog();
            }
          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {
            view.hideLoadingDialog();
          }
        });
  }

  @Override public void getCheckInfo(String deviceCode) {
    homeModel.getCheckInfo(deviceCode)
        .compose(SwitchSchedulers.applySchedulers(view))
        .subscribe(new BaseObserver<List<CheckInfoResponse>>() {
          @Override public void onSuccess(BaseResponse<List<CheckInfoResponse>> response) {
            List<CheckInfoResponse> data = response.getData();
            if (!data.isEmpty()) {
              SystemCheckUtils.getInstance().managerCheck(data);
            }
          }

          @Override public void onCodeError(BaseResponse response) {
            ToastUtils.showShortToast(response.getStatus() + "--" + response.getMessage());
          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {

          }
        });
  }

  @Override public void checkServiceValid(String deviceCode) {
    homeModel.checkServiceValid(deviceCode)
        .compose(SwitchSchedulers.applySchedulers(view))
        .subscribe(new BaseObserver<Boolean>() {
          @Override public void onSuccess(BaseResponse<Boolean> response) {
            if (response.getData()) {
              ValidCheckDialog.getInstance().showDialog();
            }
          }

          @Override public void onCodeError(BaseResponse response) {

          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {

          }
        });
  }

  @Override public void uploadLoginState(boolean isLogin) {
    LoginInfoRequest loginRequest = getLoginRequest(isLogin);
    homeModel.userLoginInfo(loginRequest)
        .compose(SwitchSchedulers.applySchedulers(view))
        .subscribe(new BaseObserver<String>() {

          @Override public void onSuccess(BaseResponse<String> tBaseReponse) {
          }

          @Override public void onCodeError(BaseResponse response) {
            ToastUtils.showShortToast(response.getStatus() + "--" + response.getMessage());
          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {

          }
        });
  }

  @Override public void doOnResume() {
    getCheckInfo(AppUtils.getMeid());
    checkServiceValid(AppUtils.getMeid());
  }

  @Override public void getCheckTimeConfig() {
    homeModel.getCheckInfo(AppUtils.getMeid())
        .compose(SwitchSchedulers.applySchedulers(view))
        .subscribe(new BaseObserver<List<CheckInfoResponse>>() {
          @Override public void onSuccess(BaseResponse<List<CheckInfoResponse>> response) {
            List<CheckInfoResponse> data = response.getData();
            if (!data.isEmpty()) {
              SystemCheckUtils.getInstance().managerCheck(data);
            }
          }

          @Override public void onCodeError(BaseResponse response) {
            ToastUtils.showShortToast(response.getStatus() + "--" + response.getMessage());
          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {

          }
        });
  }

  @Override public void checkRedDot() {
    if (!NetUtils.isNetworkAvailable() || !SystemAboutUtils.isPhoneEnable()
        || !SystemAboutUtils.isCacheEnable()) {
      view.setRedDotVisibility(true);
    } else {
      view.setRedDotVisibility(false);
    }
    if (SystemAboutUtils.isPhoneEnable()) {
      SimErrorDialog.getInstance().dismiss();
      if (NetUtils.isNetworkAvailable()) {
        NetErrorDialog.getInstance().dismiss();
      } else {
        NetErrorDialog.getInstance().showDialog();
      }
    } else {
      SimErrorDialog.getInstance().showDialog();
    }
  }

  @Override public void gotoSetting() {
    Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
    wifiSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(wifiSettingsIntent);

    //Intent intent = new Intent(this, UserMsgEditActivity.class);
    //intent.putExtra(UserMsgEditActivity.USER_NUMBER, "13140167595");
    //intent.putExtra(UserMsgEditActivity.CALL_OUT_OR_COMING, true);
    //intent.putExtra(UserMsgEditActivity.MARK_STR, "");
    //intent.putExtra(UserMsgEditActivity.RING_TIMES, 0);
    //Bundle bundle = new Bundle();
    //bundle.putSerializable(UserMsgEditActivity.MARK_NUMBER_BEAN, MarkNumBean.NUMBER_MARK_NO);
    //intent.putExtras(bundle);
    //startActivity(intent);
  }

  @Override public void gotoDialNum() {
    if (!NetUtils.isNetworkAvailable()) {
      NetErrorDialog.getInstance().showDialog();
      return;
    }
    if (LoginUtils.getInstance().getLoginBean() == null) {
      ToastUtils.showShortToast(R.string.str_home_login_tip);
    } else {
      Intent boardIntent = new Intent(context, DialNumActivity.class);
      context.startActivity(boardIntent);
    }
  }

  @Override public void clickLogin() {
    if (LoginUtils.getInstance().getLoginBean() == null) {
      getDeviceConfig();
    } else {
      view.showPop();
    }
  }

  @Override public void gotoCallRecord() {
    if (LoginUtils.getInstance().getLoginBean() == null) {
      ToastUtils.showShortToast(R.string.str_home_login_tip);
    } else {
      Intent recordIntent = new Intent(context, CallRecordActivity.class);
      context.startActivity(recordIntent);
    }
  }

  @Override public void gotoCheck() {
    Intent checkIntent = new Intent(context, SysCheckActivity.class);
    context.startActivity(checkIntent);
  }

  @Override public void gotoOutTask() {
    if (!NetUtils.isNetworkAvailable()) {
      NetErrorDialog.getInstance().showDialog();
      return;
    }
    if (LoginUtils.getInstance().getLoginBean() == null) {
      ToastUtils.showShortToast(R.string.str_home_login_tip);
    } else {
      Intent outgoingIntent = new Intent(context, LoginActivity.class);
      context.startActivity(outgoingIntent);
    }
  }

  @Override public void registerCallReceiver() {
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(KeConstance.BROADCAST_CALL_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_LINE_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_HOOK_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_PSTN_INCOMING_NUMBER);
    intentFilter.addAction(KeConstance.BROADCAST_PSTN_INCOMING_CANCEL);
    intentFilter.addAction(KeConstance.BROADCAST_PSTN_INCOMING);
    intentFilter.addAction(KeConstance.BROADCAST_WIRELESS_RING_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_WIRELESS_IDLE_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_WIRELESS_OFFHOOK_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_WIRELESS_ACTIVE_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_WIRELESS_OUTGONING_STATE);
    intentFilter.addAction(KeConstance.BROADCAST_WIRELESS_HANGUP);
    intentFilter.addAction("cn.kaer.pstn.online");
    receiver = new CallReceiver();
    context.registerReceiver(receiver, intentFilter);
  }

  @Override public void unRegisterReceiver() {
    if (receiver != null) {
      context.unregisterReceiver(receiver);
    }
  }

  @Override public void release() {
    unRegisterReceiver();
    CallCheckDialog.getInstance().release();
    NetErrorDialog.getInstance().release();
    SimErrorDialog.getInstance().release();
  }
}
