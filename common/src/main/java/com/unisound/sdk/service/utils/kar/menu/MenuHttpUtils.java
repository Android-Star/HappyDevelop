package com.unisound.sdk.service.utils.kar.menu;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.device.DeviceHttpUtils;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;
import com.unisound.sdk.service.utils.kar.menu.request.AddFavoriteRequest;
import com.unisound.sdk.service.utils.kar.menu.request.AlbumRequest;
import com.unisound.sdk.service.utils.kar.menu.request.AudioRequest;
import com.unisound.sdk.service.utils.kar.menu.request.ExistFavoriteRequest;
import com.unisound.sdk.service.utils.kar.menu.request.LauncherRequest;
import com.unisound.sdk.service.utils.kar.menu.request.QueryFavoriteRequest;
import com.unisound.sdk.service.utils.kar.menu.request.UploadPhotoRequest;
import com.unisound.sdk.service.utils.kar.menu.response.ExistFavoriteResponse;
import com.unisound.sdk.service.utils.kar.menu.response.LauncherResponse;
import com.unisound.sdk.service.utils.kar.menu.response.QueryFavoriteResponse;
import com.unisound.sdk.service.utils.kar.menu.response.SkillTipResponse;
import com.unisound.sdk.service.utils.kar.menu.response.UploadPhotoResponse;
import com.unisound.sdk.service.utils.model.AlbumDetailData;
import com.unisound.sdk.service.utils.model.AlbumDetailResponse;
import com.unisound.sdk.service.utils.model.UploadUseTimeBean;
import java.util.HashMap;
import java.util.List;

public class MenuHttpUtils {

  private MenuHttpUtils() {

  }

  private static final String TAG = "MenuHttpUtils";

  private static String baseUrl = UrlConstant.getInstance().getKarAppServerUrl();

  public static void setBaseUrl(String baseUrl) {
    MenuHttpUtils.baseUrl = baseUrl;
  }

  private static final String IS_EXIST_DEVICE_FAVORITE = "kar-pro-mobile/v3/favorite/exist";
  private static final String REMOVE_DEVICE_FAVORITE = "kar-pro-mobile/v3/favorite/remove";
  private static final String ADD_DEVICE_FAVORITE = "kar-pro-mobile/v3/favorite/add";
  private static final String GET_DEVICE_FAVORITE = "kar-pro-mobile/v3/favorite/query";
  private static final String GET_LAUNCHER_URL = "kar-pro-launcher/menu/v2/index";
  private static final String FIND_CHILD_AUDIO_LIST_BY_ALBUM_ID =
      "kar-pro-launcher/menu/findChildAudioListByAlbumId";
  private static final String FIND_CHILD_AUDIO_BY_ID = "kar-pro-launcher/menu/findChildAudioById";
  private static final String DEVICE_USE_MINUTE_COUNT =
      "kar-pro-mobile/statistic/deviceUseMinuteCount";
  private static final String UPLOAD_DEVICE_IMG = "kar-pro-file/deviceImgs/uploadDeviceImgs";
  private static final String GET_SKILL_TIP_URL = "kar-pro-launcher/tips/getAll";

  public static void getSkillTip(final String tag,
      final ResponseCallBack<KarBaseResponse<List<SkillTipResponse>>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + GET_SKILL_TIP_URL;
        KarBaseRequest baseRequest = new KarBaseRequest<String>();
        baseRequest.setData(null);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(tag, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getAlbumDetailData(final String tag, final long id, final int pageCount,
      final int pageNo, final ResponseCallBack<KarBaseResponse<AlbumDetailResponse>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + FIND_CHILD_AUDIO_LIST_BY_ALBUM_ID;
        KarBaseRequest baseRequest = new KarBaseRequest<AlbumRequest>();
        AlbumRequest albumRequest = new AlbumRequest();
        albumRequest.setPageNo(pageNo);
        albumRequest.setPageCount(pageCount);
        albumRequest.setId(id);
        baseRequest.setData(albumRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(tag, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void isExistDeviceFavorite(final String tag, final long deviceId,
      final long resourceId,
      final ResponseCallBack<KarBaseResponse<ExistFavoriteResponse>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IS_EXIST_DEVICE_FAVORITE;
        KarBaseRequest baseRequest = new KarBaseRequest<ExistFavoriteRequest>();
        ExistFavoriteRequest existFavoriteRequest = new ExistFavoriteRequest();
        existFavoriteRequest.setResourceId(resourceId);
        existFavoriteRequest.setDeviceId(deviceId);
        baseRequest.setData(existFavoriteRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(tag, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void removeDeviceFavorite(final String tag, final long deviceId,
      final long resourceId, final ResponseCallBack<KarBaseResponse<Object>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + REMOVE_DEVICE_FAVORITE;
        KarBaseRequest baseRequest = new KarBaseRequest<ExistFavoriteRequest>();
        ExistFavoriteRequest existFavoriteRequest = new ExistFavoriteRequest();
        existFavoriteRequest.setResourceId(resourceId);
        existFavoriteRequest.setDeviceId(deviceId);
        baseRequest.setData(existFavoriteRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(tag, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void addDeviceFavorite(final String tag,
      final AddFavoriteRequest addFavoriteRequest,
      final ResponseCallBack<KarBaseResponse<Object>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + ADD_DEVICE_FAVORITE;
        KarBaseRequest baseRequest = new KarBaseRequest<AddFavoriteRequest>();
        baseRequest.setData(addFavoriteRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(tag, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getDeviceFavorite(final String tag, final long deviceId,
      final int resourceType, final int pageNum, final int pageSize,
      final ResponseCallBack<KarBaseResponse<List<QueryFavoriteResponse>>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + GET_DEVICE_FAVORITE;
        KarBaseRequest baseRequest = new KarBaseRequest<QueryFavoriteRequest>();
        QueryFavoriteRequest queryFavoriteRequest = new QueryFavoriteRequest();
        queryFavoriteRequest.setPageNum(pageNum);
        queryFavoriteRequest.setPageSize(pageSize);
        queryFavoriteRequest.setResourceType(resourceType);
        queryFavoriteRequest.setDeviceId(deviceId);
        baseRequest.setData(queryFavoriteRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(tag, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void deviceUseMinuteCount(final String tag,
      final List<UploadUseTimeBean> uploadUseTimeList,
      final ResponseCallBack<KarBaseResponse<Boolean>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + DEVICE_USE_MINUTE_COUNT;
        KarBaseRequest baseRequest = new KarBaseRequest<List<UploadUseTimeBean>>();
        baseRequest.setData(uploadUseTimeList);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(tag, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void uploadPhoto(final String tag, final HashMap<String, Object> param,
      final List<UploadPhotoRequest> uploadPhotoRequest,
      final ResponseCallBack<KarBaseResponse<List<UploadPhotoResponse>>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + UPLOAD_DEVICE_IMG;
        KarBaseRequest baseRequest = new KarBaseRequest<List<UploadPhotoRequest>>();
        baseRequest.setData(uploadPhotoRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        param.put("baseParam", JsonTool.toJson(baseRequest));
        HttpUtils.post(TAG, url, null, param, true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getLauncher(final String tag, final String scopeType, final String scopeValue,
      final long updateTime, final ResponseCallBack<KarBaseResponse<LauncherResponse>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + GET_LAUNCHER_URL;
        KarBaseRequest baseRequest = new KarBaseRequest<LauncherRequest>();
        LauncherRequest launcherRequest = new LauncherRequest();
        launcherRequest.setScopeType(scopeType);
        launcherRequest.setScopeValue(scopeValue);
        launcherRequest.setUpdateTime(updateTime);
        baseRequest.setData(launcherRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HashMap<String, String> headMap = new HashMap<>();
        headMap.put(Constant.HEAD_TCL, JsonTool.toJson(baseRequest.getTcl()));
        headMap.put(Constant.HEAD_DEVICE_VERSION, Constant.DEVICE_VERSION);
        HttpUtils.post(tag, url, headMap, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void findChildAudioById(final long id,
      final ResponseCallBack<KarBaseResponse<AlbumDetailData>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + FIND_CHILD_AUDIO_BY_ID;
        KarBaseRequest baseRequest = new KarBaseRequest<AudioRequest>();
        AudioRequest audioRequest = new AudioRequest();
        audioRequest.setId(id);
        baseRequest.setData(audioRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_JSON, JsonTool.toJson(baseRequest),
            true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }
}
