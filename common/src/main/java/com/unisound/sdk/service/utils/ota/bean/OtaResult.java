package com.unisound.sdk.service.utils.ota.bean;

import java.io.Serializable;

public class OtaResult implements Serializable {
  private String config;
  private String downloadUrl;
  private long fileSize;
  private int version;
  private String md5;
  private String releaseNote;
  private int updateType;

  public String getConfig() {
    return config;
  }

  public void setConfig(String config) {
    this.config = config;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public String getReleaseNote() {
    return releaseNote;
  }

  public void setReleaseNote(String releaseNote) {
    this.releaseNote = releaseNote;
  }

  public int getUpdateType() {
    return updateType;
  }

  public void setUpdateType(int updateType) {
    this.updateType = updateType;
  }
}
