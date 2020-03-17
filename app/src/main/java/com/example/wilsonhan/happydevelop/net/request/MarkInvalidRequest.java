package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class MarkInvalidRequest implements Serializable {

  /**
   * deviceCode (string): 设备码 ,
   * invalidReason (string, optional): 无效原因 ,
   * markSource (string, optional): 标记来源楼盘 ,
   * markSourceBy (string): 执行标记操作的的置业顾问 ,
   * phoneNumber (string): 号码
   */

  private String deviceCode;
  private String invalidReason;
  private String markSource;
  private String markSourceBy;
  private String phoneNumber;

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public String getInvalidReason() {
    return invalidReason;
  }

  public void setInvalidReason(String invalidReason) {
    this.invalidReason = invalidReason;
  }

  public String getMarkSource() {
    return markSource;
  }

  public void setMarkSource(String markSource) {
    this.markSource = markSource;
  }

  public String getMarkSourceBy() {
    return markSourceBy;
  }

  public void setMarkSourceBy(String markSourceBy) {
    this.markSourceBy = markSourceBy;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
