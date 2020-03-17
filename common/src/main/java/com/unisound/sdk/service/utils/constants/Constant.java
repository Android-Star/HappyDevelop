package com.unisound.sdk.service.utils.constants;

public final class Constant {
  private Constant() {

  }

  public static final String INIT_DEVICE = "init_device";
  public static final String INIT_DEVICE_ID = "init_device_id";
  public static final String KAR_SERVER_URL = "kar_server_url";
  public static final String DEVICE_UDID = "str_key_device_user";
  public static final String PERMISSION_PASSWORD = "PERMISSION_PASSWORD";
  public static final String VOICE_LIMIT = "VOICE_LIMIT";
  public static final int LIMIT_LEVEL = 5;
  public static final int SLEEP_TIMEOUT = 5 * 60 * 1000; //休眠时间
  public static final int SUB_SYSTEM_ID = 11;

  public static final String HTTP_VERSION = "1.0.0";
  public static final String PLAY_TIME_LIMIT = "play_time_limit";
  public static final String PLAY_TIME_LIMIT_IS_OPEN = "play_time_limit_is_open";
  public static final String PLAY_SERIES_LIMIT = "play_series_limit";
  private static int systemId = 11;

  public static final String SELF_DEFINE_MANAGER = "selfDefinedManager";
  public static final String SELF_DEFINED_SERVICE = "selfDefinedService";
  public static final long CODE_SUCCESS = 0;              //成功

  public static final String ADDRESS_DEVICE_ID = "address_device_id"; //用于通讯录查找的Id,绑定设备时候存下来

  public static final String DEVICE_TOKEN_ERROR = "DEVICE_TOKEN_ERROR";

  public static final String HEAD_TCL = "tcl";
  public static final String HEAD_DEVICE_VERSION = "deviceVersion";
  public static final String DEVICE_VERSION = "V1.0.11.1562994776";

  public static final String FAMILY_MEMBER_KINSHIP = "kinship";
  public static final String FAMILY_MEMBER_BABY_NAME = "babyName";

  public static final String FAMILY_MEMBER_CHANGE_ACTION =
      "android.intent.action.FAMILY_MEMBER_CHANGE";
  public static final String FAMILY_MEMBER_CHANGE_EXTRA = "family_member_change_extra";

  public static int getSystemId() {
    return systemId;
  }

  public static void setSystemId(int id) {
    systemId = id;
  }
}
