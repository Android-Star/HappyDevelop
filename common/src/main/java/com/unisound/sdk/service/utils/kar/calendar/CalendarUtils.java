package com.unisound.sdk.service.utils.kar.calendar;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.calendar.request.CalendarDeleteRequest;
import com.unisound.sdk.service.utils.kar.calendar.request.CalendarPlanRequest;
import com.unisound.sdk.service.utils.kar.calendar.request.CourseInfoRequest;
import com.unisound.sdk.service.utils.kar.calendar.request.ManagerScheduleRequest;
import com.unisound.sdk.service.utils.kar.calendar.request.QueryScheduleRequest;
import com.unisound.sdk.service.utils.kar.calendar.response.CalendarInfo;
import com.unisound.sdk.service.utils.kar.calendar.response.CalendarPlanResponse;
import com.unisound.sdk.service.utils.kar.calendar.response.CourseDetailResponse;
import com.unisound.sdk.service.utils.kar.device.DeviceHttpUtils;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;

public class CalendarUtils {

  private CalendarUtils() {

  }

  private static final String TAG = "CalendarUtils";
  private static final String BASE_URL = UrlConstant.getInstance().getKarAppServerUrl();
  private static final String GET_ALL_SCHEDULE = "kar-pro-mobile/schedule/get/device/all";
  private static final String GET_ONE_SCHEDULE = "kar-pro-mobile/schedule/get/one";
  private static final String GET_ONE_COURSE = "kar-pro-mobile/schedule/get/course/detail";
  private static final String MANAGER_SCHEDULE = "kar-pro-mobile/schedule/device/accept/status";
  private static final String DELETE_SCHEDULE = "kar-pro-mobile/schedule/delete/one";

  public static void getAllSchedule(final String tag, final long deviceAddressId,
      final ResponseCallBack<KarBaseResponse<CalendarPlanResponse>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = BASE_URL + GET_ALL_SCHEDULE;
        KarBaseRequest baseRequest = new KarBaseRequest<CalendarPlanRequest>();
        CalendarPlanRequest calendarPlanRequest = new CalendarPlanRequest();
        calendarPlanRequest.setDeviceId(deviceAddressId);
        baseRequest.setData(calendarPlanRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(TAG + tag, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequest), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getOneSchedule(final String tag, final long id,
      final ResponseCallBack<KarBaseResponse<CalendarInfo>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = BASE_URL + GET_ONE_SCHEDULE;
        KarBaseRequest baseRequest = new KarBaseRequest<QueryScheduleRequest>();
        QueryScheduleRequest queryScheduleRequest = new QueryScheduleRequest();
        queryScheduleRequest.setId(id);
        baseRequest.setData(queryScheduleRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(TAG + tag, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequest), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getOneCourse(final String tag, final long courseId, final int pageNum,
      final int pageSize, final ResponseCallBack<KarBaseResponse<CourseDetailResponse>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = BASE_URL + GET_ONE_COURSE;
        KarBaseRequest baseRequest = new KarBaseRequest<CourseInfoRequest>();
        CourseInfoRequest courseInfoRequest = new CourseInfoRequest();
        courseInfoRequest.setCourseId(courseId);
        courseInfoRequest.setPageNum(pageNum);
        courseInfoRequest.setPageSize(pageSize);
        baseRequest.setData(courseInfoRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(TAG + tag, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequest), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void managerSchedule(final String tag,
      final ManagerScheduleRequest managerScheduleRequest,
      final ResponseCallBack<KarBaseResponse<Object>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = BASE_URL + MANAGER_SCHEDULE;
        KarBaseRequest baseRequest = new KarBaseRequest<ManagerScheduleRequest>();
        baseRequest.setData(managerScheduleRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(TAG + tag, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequest), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void deleteSchedule(final String tag, final long id, final int type,
      final ResponseCallBack<KarBaseResponse<Object>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = BASE_URL + DELETE_SCHEDULE;
        KarBaseRequest baseRequest = new KarBaseRequest<CalendarDeleteRequest>();
        CalendarDeleteRequest calendarDeleteRequest = new CalendarDeleteRequest();
        calendarDeleteRequest.setId(id);
        calendarDeleteRequest.setType(type);
        baseRequest.setData(calendarDeleteRequest);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HttpUtils.post(TAG + tag, url, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequest), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void cancelByTag(String tag) {
    HttpUtils.cancel(TAG + tag);
  }
}
