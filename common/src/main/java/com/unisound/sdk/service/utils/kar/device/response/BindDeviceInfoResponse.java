package com.unisound.sdk.service.utils.kar.device.response;

import java.io.Serializable;

public class BindDeviceInfoResponse implements Serializable {
  private long id;
  private String udid;
  private int del;
  private String encryptedFlushToken;
  private Object createDate;
  private long uid;
  private long deviceId;

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public int getDel() {
    return del;
  }

  public void setDel(int del) {
    this.del = del;
  }

  public String getEncryptedFlushToken() {
    return encryptedFlushToken;
  }

  public void setEncryptedFlushToken(String encryptedFlushToken) {
    this.encryptedFlushToken = encryptedFlushToken;
  }

  public Object getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Object createDate) {
    this.createDate = createDate;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }
}
