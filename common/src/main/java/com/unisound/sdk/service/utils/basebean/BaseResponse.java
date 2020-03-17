package com.unisound.sdk.service.utils.basebean;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
  private String version;
  private int status = Integer.MAX_VALUE;
  private String detailInfo;
  private T controlInfo;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getDetailInfo() {
    return detailInfo;
  }

  public void setDetailInfo(String detailInfo) {
    this.detailInfo = detailInfo;
  }

  public T getControlInfo() {
    return controlInfo;
  }

  public void setControlInfo(T controlInfo) {
    this.controlInfo = controlInfo;
  }

  public boolean isSuccessResponse() {
    return status == 0 || status == 200;
  }

  public boolean isAccessTokenOverdue() {
    return status == 409001;
  }
}
