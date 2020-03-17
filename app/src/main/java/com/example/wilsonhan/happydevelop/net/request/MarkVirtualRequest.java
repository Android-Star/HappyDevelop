package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class MarkVirtualRequest implements Serializable {

  /**
   * deviceCode (string): 设备码 ,
   * businessSource (string, optional): 电商来源 ,
   * markSource (string, optional): 标记来源楼盘 ,
   * markSourceBy (string, optional): 标记来源操作人 ,
   * phoneNumber (string, optional): 标记号码
   */

  private String deviceCode;
  private String businessSource;
  private String markSource;
  private String markSourceBy;
  private String phoneNumber;

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public String getBusinessSource() {
    return businessSource;
  }

  public void setBusinessSource(String businessSource) {
    this.businessSource = businessSource;
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
