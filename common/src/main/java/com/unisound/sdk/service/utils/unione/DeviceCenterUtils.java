package com.unisound.sdk.service.utils.unione;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.account.UserLoginUtils;
import com.unisound.sdk.service.utils.account.callback.UserCheckLoginCallBack;
import com.unisound.sdk.service.utils.basebean.BaseRequest;
import com.unisound.sdk.service.utils.basebean.BaseRequestHeader;
import com.unisound.sdk.service.utils.basebean.BaseResponse;
import com.unisound.sdk.service.utils.basebean.EffectiveToken;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.unione.bean.DeviceBindInfo;
import com.unisound.sdk.service.utils.unione.bean.DeviceControlInfoBean;
import com.unisound.sdk.service.utils.unione.bean.DeviceProfileInfo;
import com.unisound.sdk.service.utils.unione.bean.DeviceStatus;
import com.unisound.sdk.service.utils.unione.bean.chat.ChatMsg;
import com.unisound.sdk.service.utils.unione.bean.chat.ChatParam;

public class DeviceCenterUtils {

  public static final int ERROR_CODE_ALREADY_BINDED = 400013;
  public static final int ERROR_TOKEN = 409001;
  public static final String VERSION_1 = "1.0.0";
  public static final String DEVICE_TYPE_UNI_ONE = "UniOne";
  private static final String TAG = "DeviceCenterUtils";
  private static final String GET_DEVICE = "getUserDevices";
  private static final String BIND_SERVICE = "bind";
  private static final String GET_DEVICE_STATE = "onlineInfo";
  private static final String GET_LOG = "synLogService";
  private static final String REQUEST_BIND = "bind";
  private static final String REQUEST_UNBIND = "unbind";
  private static final String DEVICE_SETTING = "deviceSetting";
  private static final String ONLINE_STATE = "getDeviceOnlineState";
  private static final String GET_USER_DEVICE = "getUserDevices";
  private static final String LOG_MANAGER = "logManager";
  private static final String GET_CHAT_LOG = "getChatLog";

  private DeviceCenterUtils() {

  }

  public static void bindDevice(final String udid, final String deviceType, final String appKey,
      final String version, final String phoneNumber,
      final ResponseCallBack<BaseResponse> callBack) {
    UserLoginUtils.getToken(true, new UserCheckLoginCallBack() {
      @Override public void checkLoginSuccess(String accessToken) {
        DeviceBindInfo info = new DeviceBindInfo();
        info.setVersion(version);
        info.setCommand(REQUEST_BIND);
        EffectiveToken token = new EffectiveToken(accessToken);
        DeviceProfileInfo deviceProfileInfo = new DeviceProfileInfo();
        deviceProfileInfo.setUdid(udid);
        deviceProfileInfo.setpUdid("");
        deviceProfileInfo.setPhoneNumber(phoneNumber);
        deviceProfileInfo.setAppKey(appKey);
        deviceProfileInfo.setDeviceType(deviceType);
        deviceProfileInfo.setActive(true);
        info.setDeviceProfile(deviceProfileInfo);
        info.setTcl(token);
        String url = UrlConstant.getInstance().getAppServerUrl() + BIND_SERVICE;
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(info), true,
            callBack);
      }

      @Override public void checkLoginFail(String ret) {
        callBack.onError("token not get");
      }
    });
  }

  public static void unBindDevice(final String udid, final String deviceType, final String appKey,
      final String version, final String phoneNumber,
      final ResponseCallBack<BaseResponse> callBack) {
    UserLoginUtils.getToken(true, new UserCheckLoginCallBack() {
      @Override public void checkLoginSuccess(String accessToken) {
        DeviceBindInfo info = new DeviceBindInfo();
        info.setVersion(version);
        info.setCommand(REQUEST_UNBIND);
        EffectiveToken token = new EffectiveToken(accessToken);
        DeviceProfileInfo deviceProfileInfo = new DeviceProfileInfo();
        deviceProfileInfo.setUdid(udid);
        deviceProfileInfo.setpUdid("");
        deviceProfileInfo.setPhoneNumber(phoneNumber);
        deviceProfileInfo.setAppKey(appKey);
        deviceProfileInfo.setDeviceType(deviceType);
        deviceProfileInfo.setActive(true);
        info.setDeviceProfile(deviceProfileInfo);
        info.setTcl(token);
        String url = UrlConstant.getInstance().getAppServerUrl() + BIND_SERVICE;
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(info), true,
            callBack);
      }

      @Override public void checkLoginFail(String ret) {
        callBack.onError("token not get");
      }
    });
  }

  public static void getBindDevices(
      final ResponseCallBack<BaseResponse<DeviceControlInfoBean>> callBack) {
    UserLoginUtils.getToken(false, new UserCheckLoginCallBack() {
      @Override public void checkLoginSuccess(String accessToken) {
        BaseRequest baseRequest = new BaseRequest();
        EffectiveToken effectiveToken = new EffectiveToken(accessToken);
        BaseRequestHeader<BaseRequest> baseRequestHeader = new BaseRequestHeader();
        baseRequestHeader.setTcl(effectiveToken);
        baseRequestHeader.setBusinessType(DEVICE_SETTING);
        baseRequestHeader.setCommand(GET_USER_DEVICE);
        baseRequestHeader.setData(baseRequest);
        String url = UrlConstant.getInstance().getAppServerUrl() + GET_DEVICE;
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequestHeader), true, callBack);
      }

      @Override public void checkLoginFail(String ret) {
        callBack.onError(ret);
      }
    });
  }

  public static void getBindDevicesOnlineState(final String udid,
      final ResponseCallBack<BaseResponse<DeviceStatus>> callBack) {
    UserLoginUtils.getToken(false, new UserCheckLoginCallBack() {
      @Override public void checkLoginSuccess(String accessToken) {
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setUdid(udid);
        EffectiveToken effectiveToken = new EffectiveToken(accessToken);
        BaseRequestHeader<BaseRequest> baseRequestHeader = new BaseRequestHeader();
        baseRequestHeader.setTcl(effectiveToken);
        baseRequestHeader.setBusinessType(DEVICE_SETTING);
        baseRequestHeader.setCommand(ONLINE_STATE);
        baseRequestHeader.setData(baseRequest);
        String url = UrlConstant.getInstance().getAppServerUrl() + GET_DEVICE_STATE;
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequestHeader), true, callBack);
      }

      @Override public void checkLoginFail(String ret) {
        callBack.onError(ret);
      }
    });
  }

  public static void getChatHistoryList(final String udid, final int pageNo, final int pageSize,
      final ResponseCallBack<BaseResponse<ChatMsg>> callBack) {
    UserLoginUtils.getToken(false, new UserCheckLoginCallBack() {
      @Override public void checkLoginSuccess(String accessToken) {
        BaseRequestHeader<ChatParam> baseRequestHeader = new BaseRequestHeader();
        ChatParam param = new ChatParam();
        param.setPageNo(pageNo);
        param.setPageSize(pageSize);
        param.setUdid(udid);
        baseRequestHeader.setBusinessType(LOG_MANAGER);
        baseRequestHeader.setCommand(GET_CHAT_LOG);
        baseRequestHeader.setData(param);
        EffectiveToken effectiveToken = new EffectiveToken();
        effectiveToken.setToken(accessToken);
        baseRequestHeader.setTcl(effectiveToken);
        String url = UrlConstant.getInstance().getAppServerUrl() + GET_LOG;
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequestHeader), true, callBack);
      }

      @Override public void checkLoginFail(String ret) {
        callBack.onError("get token fail");
      }
    });
  }

  public static void cancel() {
    HttpUtils.cancel(TAG);
  }
}
