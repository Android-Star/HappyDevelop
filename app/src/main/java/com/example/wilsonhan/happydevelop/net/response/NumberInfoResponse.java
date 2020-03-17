package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class NumberInfoResponse implements Serializable {

  /**
   * businessSource (string, optional): 电商名称，只有当判定类型为 已标记电商虚拟和已标记固定电话时有此属性 ,
   * markFlag (integer): 如果为0表示新客户、未标记虚拟号码、未标记固定电话；如果为1表示老客户、已标记虚拟号码、已标记固定电话 ,
   * name (string, optional): 姓名，只有当号码类型为老客户时才有 ,
   * phone (string): 号码 ,
   * phoneType (string):  普通客户(phoneNumber) 虚拟号码(virtualNumber) 无效电话(invalidNumber) 黑名单电话(blackNumber)
   * receiveCallCount (integer, optional): 来电次数, 当判定为新客户时没有此属性 ,
   * sex (integer, optional): 性别，只有当号码类型为老客户时才有 0女 1男
   */

  private String phoneType;
  private String phone;
  private int markFlag;
  private int receiveCallCount;
  private String businessSource;
  private String name;
  private int sex;

  public String getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(String phoneType) {
    this.phoneType = phoneType;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getMarkFlag() {
    return markFlag;
  }

  public void setMarkFlag(int markFlag) {
    this.markFlag = markFlag;
  }

  public int getReceiveCallCount() {
    return receiveCallCount;
  }

  public void setReceiveCallCount(int receiveCallCount) {
    this.receiveCallCount = receiveCallCount;
  }

  public String getBusinessSource() {
    return businessSource;
  }

  public void setBusinessSource(String businessSource) {
    this.businessSource = businessSource;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }
}
