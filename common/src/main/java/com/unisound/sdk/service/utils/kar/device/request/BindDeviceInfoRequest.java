package com.unisound.sdk.service.utils.kar.device.request;

import java.io.Serializable;

public class BindDeviceInfoRequest implements Serializable {
  private String udid;

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }
}
