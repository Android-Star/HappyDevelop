package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class CallIntent implements Intent, Serializable {
  public static final String TYPE_MOBILE = "TYPE_MOBILE";
  public static final String TYPE_HOME = "TYPE_HOME";
  public static final String TYPE_WORK = "TYPE_WORK";
  public static final String TYPE_CAR = "TYPE_CAR";
  public static final String TYPE_ASSISTANT = "TYPE_ASSISTANT";
  private String name;
  private String number;
  private String method;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }
}
