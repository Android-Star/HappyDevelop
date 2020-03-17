package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class ActionResponse implements Serializable {

  private String actionResponseId;
  private String actionTimestamp;
  private int actionStatus;
  private String detailInfo;

  public String getActionResponseId() {
    return actionResponseId;
  }

  public void setActionResponseId(String actionResponseId) {
    this.actionResponseId = actionResponseId;
  }

  public String getActionTimestamp() {
    return actionTimestamp;
  }

  public void setActionTimestamp(String actionTimestamp) {
    this.actionTimestamp = actionTimestamp;
  }

  public int getActionStatus() {
    return actionStatus;
  }

  public void setActionStatus(int actionStatus) {
    this.actionStatus = actionStatus;
  }

  public String getDetailInfo() {
    return detailInfo;
  }

  public void setDetailInfo(String detailInfo) {
    this.detailInfo = detailInfo;
  }
}
