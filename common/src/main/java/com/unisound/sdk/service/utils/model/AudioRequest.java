package com.unisound.sdk.service.utils.model;

import com.unisound.sdk.service.utils.ExoConstants;

public class AudioRequest {
  private long id = 1;
  private String appKey = ExoConstants.APP_KEY;
  private String scenario = "child";
  private String userId;
  private String deviceId;

  public String getAppKey() {
    return appKey;
  }

  public String getScenario() {
    return scenario;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
}
