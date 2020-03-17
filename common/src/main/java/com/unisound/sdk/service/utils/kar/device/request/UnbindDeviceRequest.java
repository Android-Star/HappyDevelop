package com.unisound.sdk.service.utils.kar.device.request;

import java.io.Serializable;

public class UnbindDeviceRequest implements Serializable {
  private String udid;
  private String sign;

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}
