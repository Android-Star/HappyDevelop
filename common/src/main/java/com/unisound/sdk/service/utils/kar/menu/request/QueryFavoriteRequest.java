package com.unisound.sdk.service.utils.kar.menu.request;

import java.io.Serializable;

public class QueryFavoriteRequest implements Serializable {
  private long deviceId;
  private int resourceType;
  private int pageNum;
  private int pageSize;

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  public int getResourceType() {
    return resourceType;
  }

  public void setResourceType(int resourceType) {
    this.resourceType = resourceType;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
