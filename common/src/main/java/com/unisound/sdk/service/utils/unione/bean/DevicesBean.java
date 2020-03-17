package com.unisound.sdk.service.utils.unione.bean;

import java.io.Serializable;

public class DevicesBean implements Serializable {
  private String deviceType;
  private String phoneNumber;
  private long createTime;
  private String pUdid;
  private boolean online;
  private String appKey;
  private boolean isActive;
  private String udid;
  private String userId;

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public String getPUdid() {
    return pUdid;
  }

  public void setPUdid(String pUdid) {
    this.pUdid = pUdid;
  }

  public boolean isOnline() {
    return online;
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public boolean isIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
