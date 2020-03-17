package com.unisound.sdk.service.utils.kar.calendar.request;

import java.io.Serializable;

public class ManagerScheduleRequest implements Serializable {
  private String mappKey;
  private long uid;
  private long id;
  private int acceptType;
  private String mobileUdid;

  public String getMappKey() {
    return mappKey;
  }

  public void setMappKey(String mappKey) {
    this.mappKey = mappKey;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getAcceptType() {
    return acceptType;
  }

  public void setAcceptType(int acceptType) {
    this.acceptType = acceptType;
  }

  public String getMobileUdid() {
    return mobileUdid;
  }

  public void setMobileUdid(String mobileUdid) {
    this.mobileUdid = mobileUdid;
  }
}
