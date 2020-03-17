package com.unisound.sdk.service.utils.kar.calendar.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.litepal.crud.LitePalSupport;

public class CalendarInfo extends LitePalSupport implements Serializable {
  @SerializedName("id") private long calendarId;
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

  public CalendarInfo(CalendarInfo2 calendarInfo2) {
    this.calendarId = calendarInfo2.getCalendarId();
    this.mobileUdid = calendarInfo2.getMobileUdid();
    this.deviceId = calendarInfo2.getDeviceId();
    this.scheduleType = calendarInfo2.getScheduleType();
    this.scheduleName = calendarInfo2.getScheduleName();
    this.weekList = calendarInfo2.getWeekList();
    this.actionTime = calendarInfo2.getActionTime();
    this.detail = calendarInfo2.getDetail();
    this.status = calendarInfo2.getStatus();
    this.createTime = calendarInfo2.getCreateTime();
    this.updateTime = calendarInfo2.getUpdateTime();
    this.uid = calendarInfo2.getUid();
    this.index = calendarInfo2.getIndex();
    this.requestCode = calendarInfo2.getRequestCode();
  }

  public long getId() {
    return calendarId;
  }

  public void setId(long id) {
    this.calendarId = id;
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
