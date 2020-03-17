package com.example.wilsonhan.happydevelop.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.wilsonhan.happydevelop.R;
import com.example.wilsonhan.happydevelop.base.BaseActivity;
import com.example.wilsonhan.happydevelop.bean.EventBean;
import com.example.wilsonhan.happydevelop.constants.Constants;
import com.example.wilsonhan.happydevelop.constants.EventConstants;
import com.example.wilsonhan.happydevelop.net.BaseObserver;
import com.example.wilsonhan.happydevelop.net.BaseRequest;
import com.example.wilsonhan.happydevelop.net.BaseResponse;
import com.example.wilsonhan.happydevelop.net.SwitchSchedulers;
import com.example.wilsonhan.happydevelop.ui.contract.FlashContract;
import com.example.wilsonhan.happydevelop.ui.presenter.FlashPresenter;
import com.example.wilsonhan.happydevelop.utils.AppUtils;
import com.example.wilsonhan.happydevelop.utils.EventBusUtils;
import com.example.wilsonhan.happydevelop.utils.TimeUtils;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.NetUtils;
import com.unisound.sdk.service.utils.SharedPreferencesHelper;
import com.unisound.sdk.service.utils.callback.NetWorkChangeCallBack;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FlashActivity extends BaseActivity<FlashPresenter>
    implements FlashContract.View, NetWorkChangeCallBack {
  private static final String TAG = "FlashActivity";
  @BindView(R.id.rlMain) RelativeLayout rlMain;
  @BindView(R.id.tvTip) TextView tvTip;
  @BindView(R.id.llDownload) LinearLayout llDownload;

  @Override protected int getLayoutId() {
    return R.layout.activity_flash;
  }

  @Override protected void createPresenter() {
    presenter = new FlashPresenter(this);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    NetUtils.addNetWorkChangeCallBack(this);
    LogMgr.d(TAG, "=============flashActivity onCreate");
    SharedPreferencesHelper.getInstance().saveStringValue(Constants.SYSTEM_CHECK_INFO, "");
    presenter.dealImage();
  }

  @Override protected void onResume() {
    super.onResume();
    setStatusBarPadding(rlMain);
    presenter.doOnResume();
  }

  @Override protected void onPause() {
    super.onPause();
    if (isFinishing()) {
      presenter.release();
      NetUtils.removeNetWorkChangeCallBack(this);
    }
  }

  @Override public void onNetWorkAvailable() {
    presenter.checkApkVersion();
    presenter.getHomeImage();
  }

  @Override public void onNetWorkUnAvailable() {
  }

  @Override public void showDownloadView() {
    llDownload.setVisibility(View.VISIBLE);
  }

  @Override public void updateDownloadProgress(long progress) {
    tvTip.setText(String.format(getString(R.string.str_download_tip), progress));
  }

  private void saveCallRecord(String filePath) {
    String salerId = "";
    if (TextUtils.isEmpty(salerId)) {
      salerId = "未知接电人";
    }
    File file = new File(filePath);
    RequestBody fileRQ = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    RequestBody body = new MultipartBody.Builder().addFormDataPart("phoneNumber", "")
        .addFormDataPart("talkType", true ? "0" : "1")
        .addFormDataPart("talkDate", TimeUtils.getCurrentDate(TimeUtils.NORMAL_FORMAT))
        .addFormDataPart("talkCostTime", "")
        .addFormDataPart("ringCostTime", "")
        .addFormDataPart("taskId", "0")
        .addFormDataPart("deviceCode", AppUtils.getMeid())
        .addFormDataPart("salerId", salerId)
        .addFormDataPart("file", file.getName(), fileRQ)
        .build();

    BaseRequest.getInstance()
        .getApiService()
        .saveCallRecord(body)
        .compose(SwitchSchedulers.applySchedulers(this))
        .subscribe(new BaseObserver<String>() {
          @Override public void onSuccess(BaseResponse<String> response) {
            EventBusUtils.sendEvent(new EventBean(EventConstants.CALL_RECORD_UPDATE));
            if (file != null && file.exists()) {
              file.delete();
            }
          }

          @Override public void onCodeError(BaseResponse response) {

          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {

          }
        });
  }
}
