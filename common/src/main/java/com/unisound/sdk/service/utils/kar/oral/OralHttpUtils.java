package com.unisound.sdk.service.utils.kar.oral;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.device.DeviceHttpUtils;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;
import com.unisound.sdk.service.utils.kar.oral.request.ContentRequest;
import com.unisound.sdk.service.utils.kar.oral.request.GreadRequest;
import com.unisound.sdk.service.utils.kar.oral.request.SaveUserResultRequest;
import com.unisound.sdk.service.utils.kar.oral.response.ContentInfoBean;
import com.unisound.sdk.service.utils.kar.oral.response.SaveResult;
import com.unisound.sdk.service.utils.kar.oral.response.VersionBean;
import com.unisound.sdk.service.utils.model.AlbumDetailResponse;
import java.util.List;

import static com.unisound.sdk.service.utils.http.HttpUtils.MEDIA_TYPE_JSON;

public class OralHttpUtils {
  private OralHttpUtils() {

  }

  private static final String TAG = "OralHttpUtils";
  private static String baseUrl = UrlConstant.getInstance().getKarAppServerUrl();
  private static final String ORAL_CLASS_LIST = "kar-pro-kypc/kypc/findContentListById";
  private static final String GET_CONTENT = "kar-pro-kypc/kypc/getContent";
  private static final String VERSION_LIST = "kar-pro-kypc/kypc/menu";
  private static final String SAVE_USER_RESULT = "kar-pro-kypc/kypc/insertUserResult";

  public static void classList(final long id, final int pageCount, final int pageNo,
      final ResponseCallBack<KarBaseResponse<AlbumDetailResponse>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + ORAL_CLASS_LIST;
        KarBaseRequest<GreadRequest> oralBase = new KarBaseRequest<>();
        oralBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        GreadRequest greadRequest = new GreadRequest();
        greadRequest.setId(id);
        greadRequest.setPageCount(pageCount);
        greadRequest.setPageNo(pageNo);
        oralBase.setData(greadRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(oralBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getContent(final long code,
      final ResponseCallBack<KarBaseResponse<ContentInfoBean>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + GET_CONTENT;
        KarBaseRequest<ContentRequest> oralBase = new KarBaseRequest<>();
        oralBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        ContentRequest classRequest = new ContentRequest();
        classRequest.setCode(code);
        oralBase.setData(classRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(oralBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void versionList(
      final ResponseCallBack<KarBaseResponse<List<VersionBean>>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + VERSION_LIST;
        KarBaseRequest<Object> oralBase = new KarBaseRequest<>();
        oralBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        oralBase.setData(new Object());
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(oralBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void saveUserResult(final String area, final String sessionId,
      final long testCreateTime, final ResponseCallBack<KarBaseResponse<SaveResult>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + SAVE_USER_RESULT;
        KarBaseRequest<SaveUserResultRequest> oralBase = new KarBaseRequest<>();
        oralBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        SaveUserResultRequest saveUserResultRequest = new SaveUserResultRequest();
        saveUserResultRequest.setArea(area);
        saveUserResultRequest.setSessionId(sessionId);
        saveUserResultRequest.setTestCreateTime(testCreateTime);
        oralBase.setData(saveUserResultRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(oralBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void cancel() {
    LogMgr.d(TAG, "cancel");
    HttpUtils.cancel(TAG);
  }
}
