package com.unisound.sdk.service.utils.kar.device.response;

import java.io.Serializable;

public class DeviceActiveResponse implements Serializable {
  private String token;
  private long validTime;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getValidTime() {
    return validTime;
  }

  public void setValidTime(long validTime) {
    this.validTime = validTime;
  }
}
