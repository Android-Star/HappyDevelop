package com.unisound.sdk.service.utils.account.bean;

public class UserInfoResponse {
  private int passportId;
  private String userName;
  private String userCell;
  private String userMail;
  private String createTime;

  public int getPassportId() {
    return passportId;
  }

  public void setPassportId(int passportId) {
    this.passportId = passportId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserCell() {
    return userCell;
  }

  public void setUserCell(String userCell) {
    this.userCell = userCell;
  }

  public String getUserMail() {
    return userMail;
  }

  public void setUserMail(String userMail) {
    this.userMail = userMail;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
}
