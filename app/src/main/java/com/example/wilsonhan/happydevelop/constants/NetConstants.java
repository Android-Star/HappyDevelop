package com.example.wilsonhan.happydevelop.constants;

public class NetConstants {
  //根据设备id获取置业顾问列表和配置信息
  public static final String GET_DEVICE_CONFIG_INFO = "apk/device/configInfo/{deviceCode}";
  //登录
  public static final String USER_LOGIN = "apk/userInfo/login";
  //登录轨迹记录
  public static final String USER_LOGIN_INFO = "/apk/userInfo/footprint";
  //保存用户名片信息
  public static final String SAVE_USER_INFO = "apk/phone/customerInfo";
  //根据号码获取用户信息
  public static final String GET_USER_INFO = "apk/phone/customerInfo/{deviceCode}/{phone}";
  //来电弹屏获取呼入号码的基本信息
  public static final String GET_NUMBER_INFO = "apk/phone/incomingCallScreen/{deviceCode}/{phone}";
  //标记黑名单
  public static final String MARK_BLACK = "apk/phone/markBlack";
  //标记虚拟
  public static final String MARK_VIRTUAL = "apk/phone/markVirtual";
  //清除标记
  public static final String CLEAR_MARK = "apk/phone/removePhoneNumberFlag";
  //标记无效
  public static final String MARK_INVALID = "apk/phone/markInvalid";
  //判断给定号码是否在黑名单中
  public static final String CHECK_NUM_BLACK = "apk/phone/checkBlackNumber/{deviceCode}/{phone}";
  //设备获取自检信息
  public static final String GET_CHECK_INFO = "apk/device/checkupConfig/{deviceCode}";
  //体检信息上报
  public static final String SAVE_CHECK_INFO = "apk/device/checkupInfo";
  //查看详细信息
  public static final String GET_CALL_RECORD_DETAIL = "apk/phone/detail/{deviceCode}/{phone}";
  //查询通话记录
  public static final String GET_CALL_RECORD = "apk/phone/table";
  //保存通话记录伴随录音文件上传
  public static final String SAVE_CALL_RECORD = "apk/phone/talkWithVideoUpload";
  //获取任务列表
  public static final String GET_TASK_LIST = "apk/outboundTask/list";
  //更新客户任务状态
  public static final String UPDATE_TASK_STATE = "apk/outboundTask/customer/status/update";
  //获取单个客户任务详情
  public static final String GET_FIRST_TASK_INFO = "apk/outboundTask/customer/detail";
  //检测apk升级
  public static final String GET_NEW_APK_VERSION = "apk/software/latest";
  //检查服务是否在有效期
  public static final String CHECK_SERVICE_VALID = "apk/software/valid/{deviceCode}";
  //检查设备是否已经绑定到指定项目
  public static final String CHECK_DEVICE_BIND = "apk/device/checkIsBind/{imei}";
  //查询知识库数据
  public static final String GET_KNOWLEDGE_MSG = "apk/knowledge";
  //获取最新的系统加载图片信息
  public static final String GET_UPDATE_IMAGE = "apk/software/latestSystemLoadPicInfo";

  private NetConstants() {
  }
}
