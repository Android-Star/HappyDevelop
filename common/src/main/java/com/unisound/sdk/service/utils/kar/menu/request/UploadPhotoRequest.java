package com.unisound.sdk.service.utils.kar.menu.request;

import java.io.Serializable;

public class UploadPhotoRequest implements Serializable {

  private Long deviceId;
  private String deviceImgId;
  private String imgCreateTime;

  public Long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceImgId() {
    return deviceImgId;
  }

  public void setDeviceImgId(String deviceImgId) {
    this.deviceImgId = deviceImgId;
  }

  public String getImgCreateTime() {
    return imgCreateTime;
  }

  public void setImgCreateTime(String imgCreateTime) {
    this.imgCreateTime = imgCreateTime;
  }
}
