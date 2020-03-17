package com.unisound.sdk.service.utils.unione.bean.chat;

import java.io.Serializable;

public class MsgAlarmRemind implements Serializable {

  private String id;
  private String date;
  private String time;
  private String countDownTime;
  private String label;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getCountDownTime() {
    return countDownTime;
  }

  public void setCountDownTime(String countDownTime) {
    this.countDownTime = countDownTime;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
