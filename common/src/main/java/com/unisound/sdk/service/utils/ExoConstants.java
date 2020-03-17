package com.unisound.sdk.service.utils;

public final class ExoConstants {
  public static final String APP_START_TAG = "AutoStart";
  public static final String APP_KEY = "azyepndb4t6ixzlksfl64kdeq656waparzir2cqp";
  public static final String APP_SECRET = "d966d2d11e26174f3d20cf9b7816b593";
  public static final String APP_DEVICE_KEY_SOURCE = "robot";
  public static final String APP_MOBILE_KEY_SOURCE = "mobile";
  public static final int ACCEPT_NLU = 0;
  public static final int NOT_ACCEPT_NLU = 1;

  public static final int NOT_ACCEPT_EVENT = -1;
  public static final int ACCEPT_EVENT_AND_RESUME = 0;
  public static final int ACCEPT_EVENT_NOT_RESUME = 1;
  public static final int ACCEPT_EVENT_AND_ASR_NET_NOT_EMOTION = 2;
  public static final int ACCEPT_EVENT_AND_ASR_LOCAL_NOT_EMOTION = 3;
  public static final int ACCEPT_EVENT_AND_ASR_MIX_NOT_EMOTION = 4;
  public static final int ACCEPT_EVENT_AND_ASR_NET_SHOW_EMOTION = 5;
  public static final int ACCEPT_EVENT_AND_ASR_LOCAL_SHOW_EMOTION = 6;
  public static final int ACCEPT_EVENT_AND_ASR_MIX_SHOW_EMOTION = 7;
  public static final int ACCEPT_EVENT_AND_DO_NOTHING = 8;

  public static final String INTERRUPT_WAKE_UP = "INTERRUPT_WAKE_UP";

  public static final String INTERRUPT_WAKE_CMD = "INTERRUPT_WAKE_CMD";

  public static final String INTERRUPT_UNLOCK_SCREEN = "INTERRUPT_UNLOCK_SCREEN";

  public static final String INTERRUPT_VIDEO_CALL = "INTERRUPT_VIDEO_CALL";

  public static final String INTERRUPT_MQTT = "INTERRUPT_MQTT";

  public static final String SERVICE_NAME = "SERVICE_NAME";

  public static final String RESULT = "RESULT";

  public static final String CLOSE_APP = "CLOSE_APP";

  public static final String DISMISS_EMOTION = "DISMISS_EMOTION";

  public static final String DISMISS_EMOTION_EXTRA = "DISMISS_EMOTION_EXTRA";

  public static final String ABANDON_AUDIO_FOCUS = "ABANDON_AUDIO_FOCUS";

  public static final String PACKAGE_NAME = "PACKAGE_NAME";

  public static final String FACE_CAMERA_SHOW_OR_DISMISS = "FACE_CAMERA_SHOW_OR_DISMISS";

  public static final String POPUP_WINDOW_SHOW_OR_DISMISS = "POPUP_WINDOW_SHOW_OR_DISMISS";

  public static final String VIDEO_CALL_SHOW_OR_DISMISS = "VIDEO_CALL_SHOW_OR_DISMISS";

  public static final String DEVICE_UN_BIND = "DEVICE_UN_BIND";

  public static final String UPLOAD_DEVICE_INFO = "UPLOAD_DEVICE_INFO";

  public static final String UPLOAD_VIDEO_CALL_INFO = "UPLOAD_VIDEO_CALL_INFO";


  private ExoConstants() {
  }
}
