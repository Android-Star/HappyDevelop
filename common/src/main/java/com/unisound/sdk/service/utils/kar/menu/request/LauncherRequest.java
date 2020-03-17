package com.unisound.sdk.service.utils.kar.menu.request;

import java.io.Serializable;

public class LauncherRequest implements Serializable {
  private String scopeType;
  private String scopeValue;
  private long updateTime;

  public String getScopeType() {
    return scopeType;
  }

  public void setScopeType(String scopeType) {
    this.scopeType = scopeType;
  }

  public String getScopeValue() {
    return scopeValue;
  }

  public void setScopeValue(String scopeValue) {
    this.scopeValue = scopeValue;
  }

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }
}
