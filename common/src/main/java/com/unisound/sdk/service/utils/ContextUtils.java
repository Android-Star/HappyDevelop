package com.unisound.sdk.service.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.unisound.sdk.service.utils.model.VideoCallInfo;
import java.util.List;

public class ContextUtils {
  private static Context appContext;
  private static Context activityContext;
  private static final String TAG = "ContextUtils";

  private ContextUtils() {

  }

  public static Context getContext() {
    return appContext;
  }

  public static Application getApplication() {
    return (Application) appContext;
  }

  public static void setContext(Context context) {
    ContextUtils.appContext = context.getApplicationContext();
  }

  public static Context getActivityContext() {
    return activityContext;
  }

  public static void setActivityContext(Context activityContext) {
    ContextUtils.activityContext = activityContext;
  }

  public static void sendCloseAppBroadCast() {
    LogMgr.d(TAG, "sendCloseAppBroadCast");
    Intent intent = new Intent();
    intent.setAction(ExoConstants.CLOSE_APP);
    intent.putExtra(ExoConstants.PACKAGE_NAME, getMyProcessName());
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendFaceCameraShowOrDismiss(boolean show) {
    LogMgr.d(TAG, "sendFaceCameraShowOrDismiss:" + show);
    Intent intent = new Intent();
    intent.setAction(ExoConstants.FACE_CAMERA_SHOW_OR_DISMISS);
    intent.putExtra(ExoConstants.FACE_CAMERA_SHOW_OR_DISMISS, show);
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendPopupWindowShowOrDismiss(boolean show) {
    LogMgr.d(TAG, "sendPopupWindowShowOrDismiss:" + show);
    Intent intent = new Intent();
    intent.setAction(ExoConstants.POPUP_WINDOW_SHOW_OR_DISMISS);
    intent.putExtra(ExoConstants.POPUP_WINDOW_SHOW_OR_DISMISS, show);
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendVideoCallShowOrDismiss(boolean show) {
    LogMgr.d(TAG, "sendVideoCallShowOrDismiss:" + show);
    Intent intent = new Intent();
    intent.setAction(ExoConstants.VIDEO_CALL_SHOW_OR_DISMISS);
    intent.putExtra(ExoConstants.VIDEO_CALL_SHOW_OR_DISMISS, show);
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendDismissEmotion() {
    LogMgr.d(TAG, "sendDismissEmotion");
    Intent intent = new Intent();
    intent.setAction(ExoConstants.DISMISS_EMOTION);
    intent.putExtra(ExoConstants.PACKAGE_NAME, getMyProcessName());
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendDismissEmotion(String extra) {
    LogMgr.d(TAG, "sendDismissEmotion");
    Intent intent = new Intent();
    intent.setAction(ExoConstants.DISMISS_EMOTION);
    intent.putExtra(ExoConstants.PACKAGE_NAME, getMyProcessName());
    intent.putExtra(ExoConstants.DISMISS_EMOTION_EXTRA, extra);
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendUploadDeviceInfo(String info) {
    LogMgr.d(TAG, "sendUploadDeviceInfo");
    Intent intent = new Intent();
    intent.setAction(ExoConstants.UPLOAD_DEVICE_INFO);
    intent.putExtra(ExoConstants.PACKAGE_NAME, getMyProcessName());
    intent.putExtra(ExoConstants.UPLOAD_DEVICE_INFO, info);
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendUploadVideoCallInfo(VideoCallInfo videoCallInfo) {
    LogMgr.d(TAG, "sendUploadVideoCallInfo");
    Intent intent = new Intent();
    intent.setAction(ExoConstants.UPLOAD_VIDEO_CALL_INFO);
    intent.putExtra(ExoConstants.PACKAGE_NAME, getMyProcessName());
    intent.putExtra(ExoConstants.UPLOAD_VIDEO_CALL_INFO, videoCallInfo);
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendAbandonAudioFocus() {
    LogMgr.d(TAG, "sendAbandonAudioFocus");
    Intent intent = new Intent();
    intent.setAction(ExoConstants.ABANDON_AUDIO_FOCUS);
    intent.putExtra(ExoConstants.PACKAGE_NAME, getMyProcessName());
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static void sendDeviceUnbind() {
    LogMgr.d(TAG, "sendDeviceUnbind");
    Intent intent = new Intent();
    intent.setAction(ExoConstants.DEVICE_UN_BIND);
    intent.putExtra(ExoConstants.PACKAGE_NAME, getMyProcessName());
    ContextUtils.getContext().sendBroadcast(intent);
  }

  public static String getMyProcessName() {
    int pid = android.os.Process.myPid();
    ActivityManager activityManager =
        (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
    if (activityManager != null) {
      List<ActivityManager.RunningAppProcessInfo> processInfoList =
          activityManager.getRunningAppProcesses();
      if (processInfoList != null && processInfoList.size() > 0) {
        for (ActivityManager.RunningAppProcessInfo appProcess : processInfoList) {
          if (appProcess.pid == pid) {
            return appProcess.processName;
          }
        }
      }
    }
    return "";
  }
}
