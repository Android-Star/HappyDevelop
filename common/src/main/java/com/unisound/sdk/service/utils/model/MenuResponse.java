package com.unisound.sdk.service.utils.model;

import java.io.Serializable;
import java.util.List;

public class MenuResponse implements Serializable {
  private int errorCode;
  private int count;
  private List<MenuData> menuList;
  private long totalTime;
  private long timeStamp;

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<MenuData> getMenuList() {
    return menuList;
  }

  public void setMenuList(List<MenuData> menuList) {
    this.menuList = menuList;
  }

  public long getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(long totalTime) {
    this.totalTime = totalTime;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }
}
