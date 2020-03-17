package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class ClearPhoneFlagRequest implements Serializable {

  /**
   * deviceCode (string): 设备码 ,
   * phoneNumber (string, optional): 号码 ,
   * sourceType (string, optional): 源号码类型 - 黑名单：black、虚拟号码：virtual、无效号码：invalid
   */

  private String deviceCode;
  private String phoneNumber;
  private String sourceType;

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

  public String getSourceType() {
    return sourceType;
  }

  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }
}
