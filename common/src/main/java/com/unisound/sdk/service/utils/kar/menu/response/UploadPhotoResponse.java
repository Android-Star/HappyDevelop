package com.unisound.sdk.service.utils.kar.menu.response;

import java.io.Serializable;

public class UploadPhotoResponse implements Serializable {

  private Long id;
  private Long deviceId;
  private String deviceImgId;
  private String imgUrl;
  private String imgCreateTime;
  private String fileKey;
  private String bucketName;
  private boolean del;
  private String createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getImgCreateTime() {
    return imgCreateTime;
  }

  public void setImgCreateTime(String imgCreateTime) {
    this.imgCreateTime = imgCreateTime;
  }

  public String getFileKey() {
    return fileKey;
  }

  public void setFileKey(String fileKey) {
    this.fileKey = fileKey;
  }

  public String getBucketName() {
    return bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public boolean isDel() {
    return del;
  }

  public void setDel(boolean del) {
    this.del = del;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
}
