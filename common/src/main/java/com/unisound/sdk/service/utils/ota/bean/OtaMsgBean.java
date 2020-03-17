package com.unisound.sdk.service.utils.ota.bean;

import java.io.Serializable;

public class OtaMsgBean<T> implements Serializable {

  public static final String OPERATE_QUERY_VERSION = "queryVersion";
  public static final String OPERATE_NOTIFY_UPDATE = "notifyUpdate";
  public static final String OPERATE_UPDATE_STATUS = "updateStatus";
  public static final String UPDATE_STATUS_START = "start";
  public static final String UPDATE_STATUS_FAIL = "fail";
  public static final String UPDATE_STATUS_SUCCESS = "success";
  public static final String UPDATE_STATUS_REBOOT = "reboot";

  private String field = "ota";
  private String operate;
  private T info;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getOperate() {
    return operate;
  }

  public void setOperate(String operate) {
    this.operate = operate;
  }

  public T getInfo() {
    return info;
  }

  public void setInfo(T info) {
    this.info = info;
  }
}
