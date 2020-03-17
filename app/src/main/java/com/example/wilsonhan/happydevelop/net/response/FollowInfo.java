package com.example.wilsonhan.happydevelop.net.response;

import java.sql.Date;

public class FollowInfo {
  private Date followTime;
  private String followType;
  private String followContent;

  public void setFollowTime(Date followTime) {
    this.followTime = followTime;
  }

  public Date getFollowTime() {
    return followTime;
  }

  public void setFollowType(String followType) {
    this.followType = followType;
  }

  public String getFollowType() {
    return followType;
  }

  public void setFollowContent(String followContent) {
    this.followContent = followContent;
  }

  public String getFollowContent() {
    return followContent;
  }
}
