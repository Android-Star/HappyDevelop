package com.unisound.sdk.service.utils.unione.bean;

import java.io.Serializable;

public class DeviceStatus implements Serializable {

  private String deviceId;
  private boolean isOnline;
  private String lastOnlineTime;
  private String tdid;

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public boolean getOnline() {
    return isOnline;
  }

  public void setOnline(boolean online) {
    isOnline = online;
  }

  public String getLastOnlineTime() {
    return lastOnlineTime;
  }

  public void setLastOnlineTime(String lastOnlineTime) {
    this.lastOnlineTime = lastOnlineTime;
  }

  public String getTdid() {
    return tdid;
  }

  public void setTdid(String tdid) {
    this.tdid = tdid;
  }
}
