package com.unisound.sdk.service.utils.kar.menu.response;

import java.io.Serializable;

public class GetDataLinkResponse implements Serializable {
  private long id;
  private String url;
  private long totalContentPlayTime;
  private long expiryTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public long getTotalContentPlayTime() {
    return totalContentPlayTime;
  }

  public void setTotalContentPlayTime(long totalContentPlayTime) {
    this.totalContentPlayTime = totalContentPlayTime;
  }

  public long getExpiryTime() {
    return expiryTime;
  }

  public void setExpiryTime(long expiryTime) {
    this.expiryTime = expiryTime;
  }
}
