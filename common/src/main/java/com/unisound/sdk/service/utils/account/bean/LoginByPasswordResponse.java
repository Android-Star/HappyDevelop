package com.unisound.sdk.service.utils.account.bean;

import java.io.Serializable;

public class LoginByPasswordResponse implements Serializable {

  private String flushToken;
  private long validTime;

  public String getFlushToken() {
    return flushToken;
  }

  public void setFlushToken(String flushToken) {
    this.flushToken = flushToken;
  }

  public long getValidTime() {
    return validTime;
  }

  public void setValidTime(long validTime) {
    this.validTime = validTime;
  }
}
