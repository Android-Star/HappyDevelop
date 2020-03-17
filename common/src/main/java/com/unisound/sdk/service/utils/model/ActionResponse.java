package com.unisound.sdk.service.utils.model;

import java.io.Serializable;

public class ActionResponse implements Serializable {
  private int errorCode;
  private String detailInfo;
  private String packageName;
  private String activityName;

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getDetailInfo() {
    return detailInfo;
  }

  public void setDetailInfo(String detailInfo) {
    this.detailInfo = detailInfo;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }
}
