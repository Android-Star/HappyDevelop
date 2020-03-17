package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class LoginRequest implements Serializable {
  /**
   * password (string, optional): 登录密码 ,
   * userId (string): 账号信息
   */

  private String password;
  private String userId;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
