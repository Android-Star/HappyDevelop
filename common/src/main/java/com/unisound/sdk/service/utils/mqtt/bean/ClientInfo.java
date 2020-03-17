package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class ClientInfo implements Serializable {

  private String extras;
  private String appKey;
  private String udid;
  private int subsystemId;
  private long passportId;

  public String getExtras() {
    return extras;
  }

  public void setExtras(String extras) {
    this.extras = extras;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public int getSubsystemId() {
    return subsystemId;
  }

  public void setSubsystemId(int subsystemId) {
    this.subsystemId = subsystemId;
  }

  public long getPassportId() {
    return passportId;
  }

  public void setPassportId(long passportId) {
    this.passportId = passportId;
  }
}
