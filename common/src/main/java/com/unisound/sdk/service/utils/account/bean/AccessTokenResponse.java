package com.unisound.sdk.service.utils.account.bean;

public class AccessTokenResponse {

  private String accessToken;
  private long validTime;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public long getValidTime() {
    return validTime;
  }

  public void setValidTime(long validTime) {
    this.validTime = validTime;
  }
}
