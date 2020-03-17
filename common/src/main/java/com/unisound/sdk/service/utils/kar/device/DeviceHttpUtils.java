package com.unisound.sdk.service.utils.kar.device;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.UserPreferenceUtils;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;
import com.unisound.sdk.service.utils.kar.device.request.BindDeviceInfoRequest;
import com.unisound.sdk.service.utils.kar.device.request.DeviceActiveRequest;
import com.unisound.sdk.service.utils.kar.device.request.InsertDeviceRequest;
import com.unisound.sdk.service.utils.kar.device.request.UnbindDeviceRequest;
import com.unisound.sdk.service.utils.kar.device.response.BindDeviceInfoResponse;
import com.unisound.sdk.service.utils.kar.device.response.DeviceActiveResponse;
import com.unisound.sdk.service.utils.kar.device.response.DeviceBaseResponse;
import com.unisound.sdk.service.utils.mqtt.MqttSignUtils;
import java.util.ArrayList;
import java.util.List;

public class DeviceHttpUtils {

  public static final int ERROR_DEVICE_NOT_BIND = 100608;

  private DeviceHttpUtils() {

  }

  private static final String TAG = "DeviceHttpUtils";

  public static final String CODE_DEVICE_SUCCESS = "dc_0000";              //成功
  private static final String ACTIVE_DEVICE_URL = "rest/v2/device/activate";

  private static ArrayList<DeviceTokenCallBack> deviceTokenCallBackList = new ArrayList<>();

  private static String baseUrl = UrlConstant.getInstance().getKarAppServerUrl();
  private static String version = "1.0.2";
  private static final String GET_BIND_DEVICE_INFO_URL =
      "kar-pro-user-device/userDevice/findDeviceBindingInfoByUdid";
  private static final String INSERT_DEVICE_INFO_URL =
      "kar-pro-user-device/userDevice/insertOrUpdateDeviceInfo";
  private static final String UNBIND_DEVICE_URL =
      "kar-pro-user-device/userDevice/delUserDeviceByUdid";
  private static final int ACCESS_DELTA = 10 * 60 * 1000;
  private static boolean firstGetDeviceToken = false;

  public static void setFirstGetDeviceToken(boolean firstGetDeviceToken) {
    DeviceHttpUtils.firstGetDeviceToken = firstGetDeviceToken;
  }

  public static void getBindDeviceInfoUrl(
      final ResponseCallBack<KarBaseResponse<BindDeviceInfoResponse>> callBack) {

    getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + GET_BIND_DEVICE_INFO_URL;
        KarBaseRequest<BindDeviceInfoRequest> karBase = new KarBaseRequest<>();
        karBase.setBusinessType(GET_BIND_DEVICE_INFO_URL.split("/")[0]);
        karBase.setCommand(GET_BIND_DEVICE_INFO_URL.split("/")[1]);
        karBase.setVersion(version);
        karBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        BindDeviceInfoRequest bindDeviceInfoRequest = new BindDeviceInfoRequest();
        bindDeviceInfoRequest.setUdid(SystemUtils.getDeviceId());
        karBase.setData(bindDeviceInfoRequest);
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(karBase), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void insertDevice(final InsertDeviceRequest insertDeviceRequest,
      final ResponseCallBack<KarBaseResponse<Boolean>> callBack) {
    getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + INSERT_DEVICE_INFO_URL;
        KarBaseRequest<InsertDeviceRequest> karBase = new KarBaseRequest<>();
        karBase.setBusinessType(INSERT_DEVICE_INFO_URL.split("/")[0]);
        karBase.setCommand(INSERT_DEVICE_INFO_URL.split("/")[1]);
        karBase.setVersion(version);
        karBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        karBase.setData(insertDeviceRequest);
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(karBase), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(error);
      }
    });
  }

  public static void unbindDevice(final ResponseCallBack<KarBaseResponse<Integer>> callBack) {
    getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + UNBIND_DEVICE_URL;
        KarBaseRequest<UnbindDeviceRequest> karBase = new KarBaseRequest<>();
        UnbindDeviceRequest unbindDeviceRequest = new UnbindDeviceRequest();
        String udid = SystemUtils.getDeviceId();
        unbindDeviceRequest.setUdid(udid);
        List<String> params = new ArrayList<>();
        params.add(udid);
        unbindDeviceRequest.setSign(MqttSignUtils.buildSignature(params));
        karBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        karBase.setData(unbindDeviceRequest);
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(karBase), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(error);
      }
    });
  }

  public static void getDeviceToken(boolean forceRefresh,
      final DeviceTokenCallBack deviceTokenCallBack) {
    if (deviceTokenCallBack == null) {
      LogMgr.d(TAG, "deviceTokenCallBack need not null");
      return;
    }
    final String url = UrlConstant.getInstance().getDeviceCenterUrl() + ACTIVE_DEVICE_URL;
    if (!forceRefresh && !firstGetDeviceToken) {
      if (hasValidDeviceToken(url)) {
        deviceTokenCallBack.getDeviceTokenSuccess(
            SystemUtils.getStringFromFile(UserPreferenceUtils.DEVICE_TOKEN));
        return;
      }
    }
    synchronized (TAG) {
      LogMgr.d(TAG, "deviceTokenCallBackList add:" + deviceTokenCallBack);
      deviceTokenCallBackList.add(deviceTokenCallBack);
      if (deviceTokenCallBackList.size() > 1) {
        LogMgr.d(TAG, "deviceTokenCallBackList size:" + deviceTokenCallBackList.size());
        return;
      }
    }
    DeviceActiveRequest deviceActiveRequest = new DeviceActiveRequest();
    String param = DeviceParamsUtils.formatParam(deviceActiveRequest);
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true,
        new ResponseCallBack<DeviceBaseResponse<DeviceActiveResponse>>() {
          @Override public void onResponse(DeviceBaseResponse<DeviceActiveResponse> response) {
            if (response != null && DeviceHttpUtils.CODE_DEVICE_SUCCESS.equals(
                response.getReturnCode()) && response.getResult() != null) {
              firstGetDeviceToken = false;
              SystemUtils.saveStringToFile(UserPreferenceUtils.DEVICE_URL, url);
              SystemUtils.saveLongToFile(UserPreferenceUtils.DEVICE_VALID_TIME,
                  response.getResult().getValidTime());
              SystemUtils.saveStringToFile(UserPreferenceUtils.DEVICE_TOKEN,
                  response.getResult().getToken());
              SystemUtils.saveLongToFile(UserPreferenceUtils.DEVICE_REFRESH_TIME,
                  System.currentTimeMillis());
              callBackDeviceTokenSuccess(response.getResult().getToken());
            } else if (response != null) {
              callBackDeviceTokenFail(response.getReturnCode());
            } else {
              callBackDeviceTokenFail("");
            }
          }

          @Override public void onError(String error) {
            callBackDeviceTokenFail(error);
          }
        });
  }

  private static void callBackDeviceTokenSuccess(String token) {
    synchronized (TAG) {
      if (deviceTokenCallBackList.size() > 0) {
        ArrayList<DeviceTokenCallBack> arrayList =
            (ArrayList<DeviceTokenCallBack>) deviceTokenCallBackList.clone();
        deviceTokenCallBackList.clear();
        for (DeviceTokenCallBack deviceTokenCallBack : arrayList) {
          deviceTokenCallBack.getDeviceTokenSuccess(token);
        }
      }
    }
  }

  private static void callBackDeviceTokenFail(String error) {
    synchronized (TAG) {
      if (deviceTokenCallBackList.size() > 0) {
        ArrayList<DeviceTokenCallBack> arrayList =
            (ArrayList<DeviceTokenCallBack>) deviceTokenCallBackList.clone();
        deviceTokenCallBackList.clear();
        for (DeviceTokenCallBack deviceTokenCallBack : arrayList) {
          deviceTokenCallBack.getDeviceTokenFail(error);
        }
      }
    }
  }

  private static boolean hasValidDeviceToken(String url) {
    String deviceUrl = SystemUtils.getStringFromFile(UserPreferenceUtils.DEVICE_URL);
    LogMgr.d(TAG, "deviceUrl:" + deviceUrl);
    if (url != null && url.equals(deviceUrl)) {
      long validTime = SystemUtils.getLongFromFile(UserPreferenceUtils.DEVICE_VALID_TIME);
      long refreshTime = SystemUtils.getLongFromFile(UserPreferenceUtils.DEVICE_REFRESH_TIME);
      LogMgr.d(TAG, "validTime:" + validTime + ",refreshTime:" + refreshTime);
      return (refreshTime + validTime * 1000 - ACCESS_DELTA > System.currentTimeMillis());
    }
    return false;
  }

  public static String getValidToken() {
    final String url = UrlConstant.getInstance().getDeviceCenterUrl() + ACTIVE_DEVICE_URL;
    if (hasValidDeviceToken(url)) {
      return SystemUtils.getStringFromFile(UserPreferenceUtils.DEVICE_TOKEN);
    }
    return "";
  }
}
