package com.unisound.sdk.service.utils.kar.calendar.response;

import java.io.Serializable;

public class CalendarInfo2 implements Serializable {
  private long id;
  private String mobileUdid;
  private Long deviceId;
  private int scheduleType;
  private String scheduleName;
  private String weekList;
  private String actionTime;
  private String detail;
  private int status;
  private String createTime;
  private String updateTime;
  private long uid;
  private int index;
  private int requestCode;

  public long getCalendarId() {
    return id;
  }

  public void setCalendarId(long calendarId) {
    this.id = calendarId;
  }

  public String getMobileUdid() {
    return mobileUdid;
  }

  public void setMobileUdid(String mobileUdid) {
    this.mobileUdid = mobileUdid;
  }

  public Long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }

  public int getScheduleType() {
    return scheduleType;
  }

  public void setScheduleType(int scheduleType) {
    this.scheduleType = scheduleType;
  }

  public String getScheduleName() {
    return scheduleName;
  }

  public void setScheduleName(String scheduleName) {
    this.scheduleName = scheduleName;
  }

  public String getWeekList() {
    return weekList;
  }

  public void setWeekList(String weekList) {
    this.weekList = weekList;
  }

  public String getActionTime() {
    return actionTime;
  }

  public void setActionTime(String actionTime) {
    this.actionTime = actionTime;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getRequestCode() {
    return requestCode;
  }

  public void setRequestCode(int requestCode) {
    this.requestCode = requestCode;
  }
}
