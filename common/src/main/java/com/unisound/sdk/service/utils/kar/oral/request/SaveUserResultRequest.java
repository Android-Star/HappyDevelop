package com.unisound.sdk.service.utils.kar.oral.request;

import java.io.Serializable;

public class SaveUserResultRequest implements Serializable {
  private long testCreateTime;
  private String sessionId;
  private String area;

  public long getTestCreateTime() {
    return testCreateTime;
  }

  public void setTestCreateTime(long testCreateTime) {
    this.testCreateTime = testCreateTime;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }
}
