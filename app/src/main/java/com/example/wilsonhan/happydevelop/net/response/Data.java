package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
  private String isExist;
  private String phone;
  private String name;
  private String sex;
  private String customerId;
  private String salerName;
  private String salerId;
  private List<FollowInfo> followInfo;
  private String note;
  private List<Tags> tags;

  public void setIsExist(String isExist) {
    this.isExist = isExist;
  }

  public String getIsExist() {
    return isExist;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhone() {
    return phone;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getSex() {
    return sex;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setSalerName(String salerName) {
    this.salerName = salerName;
  }

  public String getSalerName() {
    return salerName;
  }

  public void setSalerId(String salerId) {
    this.salerId = salerId;
  }

  public String getSalerId() {
    return salerId;
  }

  public void setFollowInfo(List<FollowInfo> followInfo) {
    this.followInfo = followInfo;
  }

  public List<FollowInfo> getFollowInfo() {
    return followInfo;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getNote() {
    return note;
  }

  public void setTags(List<Tags> tags) {
    this.tags = tags;
  }

  public List<Tags> getTags() {
    return tags;
  }
}
