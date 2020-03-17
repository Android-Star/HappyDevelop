package com.unisound.sdk.service.utils.model;

import com.unisound.sdk.service.utils.ExoConstants;

public class MenuRequest {
  private long menuId = 1;
  private String appKey = ExoConstants.APP_KEY;
  private String userId;
  private String deviceId;

  public String getAppKey() {
    return appKey;
  }

  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
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
