package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class SettingIntent implements Intent, Serializable {
  private String confirm;
  private String operator;
  private String operands;
  private String value;
  private String valueDelta;
  private String valueExpr;
  private String valueDeltaExpr;
  private String datatype;
  private String percentValue;
  private String percentValueDelta;
  private String home;
  private String homeExpr;
  private String zone;
  private String zoneType;
  private String zoneExpr;
  private String room;
  private String roomType;
  private String roomExpr;
  private String time;
  private String timeDelta;
  private String endTime;
  private String repeat;
  private String duration;
  private String timeExpr;
  private String offsetTime;
  private String anchorTime;
  private String device;
  private String deviceExpr;
  private String deviceType;
  private String interruptType;
  private String callMethod;
  private String udid;
  private long timeStamp = System.currentTimeMillis();
  private boolean openScreen;

  public boolean isOpenScreen() {
    return openScreen;
  }

  public void setOpenScreen(boolean openScreen) {
    this.openScreen = openScreen;
  }

  public String getCallMethod() {
    return callMethod;
  }

  public void setCallMethod(String callMethod) {
    this.callMethod = callMethod;
  }

  public String getConfirm() {
    return confirm;
  }

  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getOperands() {
    return operands;
  }

  public void setOperands(String operands) {
    this.operands = operands;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValueDelta() {
    return valueDelta;
  }

  public void setValueDelta(String valueDelta) {
    this.valueDelta = valueDelta;
  }

  public String getPercentValue() {
    return percentValue;
  }

  public void setPercentValue(String percentValue) {
    this.percentValue = percentValue;
  }

  public String getPercentValueDelta() {
    return percentValueDelta;
  }

  public void setPercentValueDelta(String percentValueDelta) {
    this.percentValueDelta = percentValueDelta;
  }

  public String getHome() {
    return home;
  }

  public void setHome(String home) {
    this.home = home;
  }

  public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTimeDelta() {
    return timeDelta;
  }

  public void setTimeDelta(String timeDelta) {
    this.timeDelta = timeDelta;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public String getValueExpr() {
    return valueExpr;
  }

  public void setValueExpr(String valueExpr) {
    this.valueExpr = valueExpr;
  }

  public String getValueDeltaExpr() {
    return valueDeltaExpr;
  }

  public void setValueDeltaExpr(String valueDeltaExpr) {
    this.valueDeltaExpr = valueDeltaExpr;
  }

  public String getDatatype() {
    return datatype;
  }

  public void setDatatype(String datatype) {
    this.datatype = datatype;
  }

  public String getHomeExpr() {
    return homeExpr;
  }

  public void setHomeExpr(String homeExpr) {
    this.homeExpr = homeExpr;
  }

  public String getZoneType() {
    return zoneType;
  }

  public void setZoneType(String zoneType) {
    this.zoneType = zoneType;
  }

  public String getZoneExpr() {
    return zoneExpr;
  }

  public void setZoneExpr(String zoneExpr) {
    this.zoneExpr = zoneExpr;
  }

  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public String getRoomExpr() {
    return roomExpr;
  }

  public void setRoomExpr(String roomExpr) {
    this.roomExpr = roomExpr;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getRepeat() {
    return repeat;
  }

  public void setRepeat(String repeat) {
    this.repeat = repeat;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getTimeExpr() {
    return timeExpr;
  }

  public void setTimeExpr(String timeExpr) {
    this.timeExpr = timeExpr;
  }

  public String getOffsetTime() {
    return offsetTime;
  }

  public void setOffsetTime(String offsetTime) {
    this.offsetTime = offsetTime;
  }

  public String getAnchorTime() {
    return anchorTime;
  }

  public void setAnchorTime(String anchorTime) {
    this.anchorTime = anchorTime;
  }

  public String getDeviceExpr() {
    return deviceExpr;
  }

  public void setDeviceExpr(String deviceExpr) {
    this.deviceExpr = deviceExpr;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getInterruptType() {
    return interruptType;
  }

  public void setInterruptType(String interruptType) {
    this.interruptType = interruptType;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }
}
