package com.unisound.sdk.service.utils.kar.fruitrecognize.request;

import java.io.Serializable;

public class FruitRequest implements Serializable {
  private long deviceId;

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }
}
