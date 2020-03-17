package com.unisound.sdk.service.utils.kar.menu.request;

import java.io.Serializable;

public class ExistFavoriteRequest implements Serializable {
  private long deviceId;
  private long resourceId;

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  public long getResourceId() {
    return resourceId;
  }

  public void setResourceId(long resourceId) {
    this.resourceId = resourceId;
  }
}
