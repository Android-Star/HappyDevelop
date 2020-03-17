package com.unisound.sdk.service.utils.model;

import java.io.Serializable;

public class UploadUseTimeBean implements Serializable {
  private String startDateTime;
  private String endDateTime;
  private long deviceId;

  public String getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(String startDateTime) {
    this.startDateTime = startDateTime;
  }

  public String getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(String endDateTime) {
    this.endDateTime = endDateTime;
  }

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }
}
