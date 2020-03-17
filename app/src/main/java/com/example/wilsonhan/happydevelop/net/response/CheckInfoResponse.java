package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class CheckInfoResponse implements Serializable {

  /**
   * checkupTime : 9:00
   * checkupRate : 每天
   * checkupRate (string): 自检频率(everyDay 每天 - nextDay 隔天 - everyThreeDays 每三天 - weekly 每周) ,
   */

  private String checkupTime;
  private String checkupRate;

  public String getCheckupTime() {
    return checkupTime;
  }

  public void setCheckupTime(String checkupTime) {
    this.checkupTime = checkupTime;
  }

  public String getCheckupRate() {
    return checkupRate;
  }

  public void setCheckupRate(String checkupRate) {
    this.checkupRate = checkupRate;
  }
}
