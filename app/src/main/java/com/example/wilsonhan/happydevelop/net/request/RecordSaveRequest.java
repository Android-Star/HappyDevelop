package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class RecordSaveRequest implements Serializable {

  /**
   * deviceCode (string, optional): 设备码 ,
   * phoneNumber (string, optional): 通话号码 ,
   * talkAudio (string, optional): 通话音频地址 ,
   * talkCostTime (integer, optional): 通化持续时间 ,
   * talkDate (string, optional): 通话日期 ,
   * talkType (integer, optional): 童话类型 ,
   * taskId (integer, optional): 任务id
   */

  private String deviceCode;
  private String phoneNumber;
  private String talkAudio;
  private long talkCostTime;
  private String talkDate;
  private int talkType;
  private int taskId;

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getTalkAudio() {
    return talkAudio;
  }

  public void setTalkAudio(String talkAudio) {
    this.talkAudio = talkAudio;
  }

  public long getTalkCostTime() {
    return talkCostTime;
  }

  public void setTalkCostTime(long talkCostTime) {
    this.talkCostTime = talkCostTime;
  }

  public String getTalkDate() {
    return talkDate;
  }

  public void setTalkDate(String talkDate) {
    this.talkDate = talkDate;
  }

  public int getTalkType() {
    return talkType;
  }

  public void setTalkType(int talkType) {
    this.talkType = talkType;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }
}
