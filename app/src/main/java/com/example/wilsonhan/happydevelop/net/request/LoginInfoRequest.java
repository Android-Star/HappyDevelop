package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class LoginInfoRequest implements Serializable {

  /**
   * deviceCode (string): 设备码 ,
   * operateDate (string): 操作日期 ,
   * userAction (string): 足迹内容：登录或者登出 - enum('login', 'logout') ,
   * userCode (string): 用户账号
   */

  private String deviceCode;
  private String operateDate;
  private String userAction;
  private String userCode;

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public String getOperateDate() {
    return operateDate;
  }

  public void setOperateDate(String operateDate) {
    this.operateDate = operateDate;
  }

  public String getUserAction() {
    return userAction;
  }

  public void setUserAction(String userAction) {
    this.userAction = userAction;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }
}
