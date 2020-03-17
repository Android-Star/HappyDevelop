package com.unisound.sdk.service.utils.kar.menu;

import android.text.TextUtils;
import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.account.UserLoginUtils;
import com.unisound.sdk.service.utils.account.callback.UserCheckLoginCallBack;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.device.DeviceHttpUtils;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;
import com.unisound.sdk.service.utils.kar.menu.request.GetDataLinkRequest;
import com.unisound.sdk.service.utils.kar.menu.response.GetDataLinkResponse;

public class AudioHttpUtils {

  private AudioHttpUtils() {

  }

  private static String baseUrl = UrlConstant.getInstance().getKarAppServerUrl();
  private static final String GET_DATA_LINK = "kar-pro-mobile/childLink/getDataLink";
  private static final String TAG = "AudioHttpUtils";

  public static void setBaseUrl(String baseUrl) {
    AudioHttpUtils.baseUrl = baseUrl;
  }

  public static void findChildLinkByIdHaveDefaultValue(long fid, long type, String dataSourceCode,
      String dataType, ResponseCallBack<KarBaseResponse<GetDataLinkResponse>> callBack) {
    if (!SystemUtils.isIsDeviceUse()) {
      getMobileDataLink(fid, type, dataSourceCode, dataType, callBack);
    } else {
      getDeviceDataLink(fid, type, dataSourceCode, dataType, callBack);
    }
  }

  private static void getMobileDataLink(final long fid, final long type,
      final String dataSourceCode, final String dataType,
      final ResponseCallBack<KarBaseResponse<GetDataLinkResponse>> callBack) {
    UserLoginUtils.getToken(false, new UserCheckLoginCallBack() {
      @Override public void checkLoginSuccess(String accessToken) {
        String url = baseUrl + GET_DATA_LINK;
        KarBaseRequest baseRequest = new KarBaseRequest<GetDataLinkRequest>();
        GetDataLinkRequest getDataLinkRequest = new GetDataLinkRequest();
        getDataLinkRequest.setId(fid);
        getDataLinkRequest.setResourceType(type);
        if (!TextUtils.isEmpty(dataSourceCode) && !TextUtils.isEmpty(dataType)) {
          getDataLinkRequest.setDataSourceCode(dataSourceCode);
          getDataLinkRequest.setDataType(dataType);
        }
        baseRequest.setData(getDataLinkRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclPhone(accessToken));
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void checkLoginFail(String ret) {
        callBack.onError(ret);
      }
    });
  }

  private static void getDeviceDataLink(final long fid, final long type,
      final String dataSourceCode, final String dataType,
      final ResponseCallBack<KarBaseResponse<GetDataLinkResponse>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + GET_DATA_LINK;
        KarBaseRequest baseRequest = new KarBaseRequest<GetDataLinkRequest>();
        GetDataLinkRequest getDataLinkRequest = new GetDataLinkRequest();
        getDataLinkRequest.setId(fid);
        getDataLinkRequest.setResourceType(type);
        if (!TextUtils.isEmpty(dataSourceCode) && !TextUtils.isEmpty(dataType)) {
          getDataLinkRequest.setDataSourceCode(dataSourceCode);
          getDataLinkRequest.setDataType(dataType);
        }
        baseRequest.setData(getDataLinkRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void cancelAll() {
    HttpUtils.cancel(TAG);
  }
}
