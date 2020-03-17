package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class AlarmIntent implements Intent, Serializable {
  private String date;
  private String time;
  private String repeatDate;
  private String label;
  private String questionFocus;
  private String orderNumber;
  private String uncertainIntent;
  private String baseTime;
  private boolean isOrigin;

  public String getQuestionFocus() {
    return questionFocus;
  }

  public void setQuestionFocus(String questionFocus) {
    this.questionFocus = questionFocus;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getUncertainIntent() {
    return uncertainIntent;
  }

  public void setUncertainIntent(String uncertainIntent) {
    this.uncertainIntent = uncertainIntent;
  }

  public String getBaseTime() {
    return baseTime;
  }

  public void setBaseTime(String baseTime) {
    this.baseTime = baseTime;
  }

  public boolean isOrigin() {
    return isOrigin;
  }

  public void setOrigin(boolean origin) {
    isOrigin = origin;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getRepeatDate() {
    return repeatDate;
  }

  public void setRepeatDate(String repeatDate) {
    this.repeatDate = repeatDate;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
