package com.example.wilsonhan.happydevelop.constants;

public class EventConstants {

  private EventConstants() {
  }

  //外呼挂断
  public static final int CALL_HANGUP = 0x500;
  //4G接通
  public static final int CALL_4G_SUCCESS = 0x501;
  //sim卡状态更新
  public static final int SIM_STATE_CHANGE = 0x502;
  //pstn在线状态更新
  public static final int PSTN_STATE_CHANGE = 0x503;
  //话筒状态改变
  public static final int HOOK_STATE_CHANGE = 0x504;
  //通知系统自检
  public static final int SYSTEM_CHECK = 0x505;
  //通话记录更新
  public static final int CALL_RECORD_UPDATE = 0x506;
  //任务状态更新
  public static final int TASK_STATE_UPDATE = 0x507;
}
