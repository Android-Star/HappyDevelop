package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class DialogBean implements Serializable {

  /*****
   * 会话状态集
   */
  public static final String DIALOG_IDLE = "0";
  public static final String DIALOG_START = "1";
  public static final String DIALOG_FINISH = "2";

  private String dstState;
  private String dstService;
  private SendToCloudResponseBean sendToCloudResponse;
  private SendToCloudResponseBean sendToTerminalResponse;

  public String getDstState() {
    return dstState;
  }

  public void setDstState(String dstState) {
    this.dstState = dstState;
  }

  public String getDstService() {
    return dstService;
  }

  public void setDstService(String dstService) {
    this.dstService = dstService;
  }

  public SendToCloudResponseBean getSendToCloudResponse() {
    return sendToCloudResponse;
  }

  public void setSendToCloudResponse(SendToCloudResponseBean sendToCloudResponse) {
    this.sendToCloudResponse = sendToCloudResponse;
  }

  public SendToCloudResponseBean getSendToTerminalResponse() {
    return sendToTerminalResponse;
  }

  public void setSendToTerminalResponse(SendToCloudResponseBean sendToTerminalResponse) {
    this.sendToTerminalResponse = sendToTerminalResponse;
  }

  public static class SendToCloudResponseBean implements Serializable {
    private String actionResponseId;
    private String actionDstServiceId;
    private long actionTimestamp;

    public SendToCloudResponseBean(String actionResponseId) {
      this.actionResponseId = actionResponseId;
      this.actionTimestamp = System.currentTimeMillis();
    }

    public String getActionResponseId() {
      return actionResponseId;
    }

    public void setActionResponseId(String actionResponseId) {
      this.actionResponseId = actionResponseId;
    }

    public String getActionDstServiceId() {
      return actionDstServiceId;
    }

    public void setActionDstServiceId(String actionDstServiceId) {
      this.actionDstServiceId = actionDstServiceId;
    }

    public long getActionTimestamp() {
      return actionTimestamp;
    }

    public void setActionTimestamp(long actionTimestamp) {
      this.actionTimestamp = actionTimestamp;
    }
  }
}
