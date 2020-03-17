package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class RecordListResponse implements Serializable {

  /**
   * phoneName (string): 通话姓名 ,
   * phoneNumber (string): 通话号码 ,
   * talkCostTime (integer): 通话持续时间 ,
   * talkDate (string): 通话日期 ,
   * talkType (integer): 通话类型0 来电已接 1去电已接 2 来电未接 3去电未接 4来电拒接
   * phoneType 普通客户(phoneNumber) 虚拟号码(virtualNumber) 无效电话(invalidNumber) 黑名单号码(blackNumber)
   */

  private String phoneNumber;
  private String phoneName;
  private int talkType;
  private String talkDate;
  private long talkCostTime;
  private String phoneType;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneName() {
    return phoneName;
  }

  public void setPhoneName(String phoneName) {
    this.phoneName = phoneName;
  }

  public int getTalkType() {
    return talkType;
  }

  public void setTalkType(int talkType) {
    this.talkType = talkType;
  }

  public String getTalkDate() {
    return talkDate;
  }

  public void setTalkDate(String talkDate) {
    this.talkDate = talkDate;
  }

  public long getTalkCostTime() {
    return talkCostTime;
  }

  public void setTalkCostTime(long talkCostTime) {
    this.talkCostTime = talkCostTime;
  }

  public String getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(String phoneType) {
    this.phoneType = phoneType;
  }
}
