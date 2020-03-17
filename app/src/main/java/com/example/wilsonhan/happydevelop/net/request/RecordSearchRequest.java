package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class RecordSearchRequest implements Serializable {

  /**
   * deviceCode (string, optional): 设备码 ,
   * endDate (string, optional): 结束时间 ,
   * fromDate (string, optional): 开始时间 ,
   * name (string, optional): 姓名 ,
   * phone (string, optional): 电话 ,
   * queryDate (string, optional): 查询日期
   * type 通话类型 - 0 全部通话、1 未接来电、2 已接电话、3 拨出电话
   */

  private String deviceCode;
  private String endDate;
  private String fromDate;
  private String name;
  private String phone;
  private String queryDate;
  private int type;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getQueryDate() {
    return queryDate;
  }

  public void setQueryDate(String queryDate) {
    this.queryDate = queryDate;
  }
}
