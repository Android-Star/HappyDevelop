package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;
import java.util.List;

public class UserMsgRequest implements Serializable {
  private int isNotInput;           //是否是不愿意透漏号码
  private String userName;          //名字
  private String phoneNumber;       //号码
  private String gender;            //性别
  private String saler;             //置业顾问
  private List<String> knowChannel; //认知渠道
  private List<String> attention;   //关注点
  private String intent;            //意向业态
  private String consumer;          //客户组
  private String houseType;         //意向户型
  private String houseTarget;       //置业目的
  private String consumerType;      //客户类型
  private String intentHouse;       //意向房源
  private String resistance;        //抗性
  private String buyIntent;         //意愿
  private String note;              //备注
  private String building;          //所属楼盘
  private String deviceCode;        //设备码
  private long taskId;
  private long id;
  private String salerName;
  private int isNotInputNumber;     //是否是不愿意透漏号码

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public int getIsNotInput() {
    return isNotInput;
  }

  public void setIsNotInput(int isNotInput) {
    this.isNotInput = isNotInput;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getSaler() {
    return saler;
  }

  public void setSaler(String saler) {
    this.saler = saler;
  }

  public List<String> getKnowChannel() {
    return knowChannel;
  }

  public void setKnowChannel(List<String> knowChannel) {
    this.knowChannel = knowChannel;
  }

  public List<String> getAttention() {
    return attention;
  }

  public void setAttention(List<String> attention) {
    this.attention = attention;
  }

  public String getIntent() {
    return intent;
  }

  public void setIntent(String intent) {
    this.intent = intent;
  }

  public String getConsumer() {
    return consumer;
  }

  public void setConsumer(String consumer) {
    this.consumer = consumer;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getHouseTarget() {
    return houseTarget;
  }

  public void setHouseTarget(String houseTarget) {
    this.houseTarget = houseTarget;
  }

  public String getConsumerType() {
    return consumerType;
  }

  public void setConsumerType(String consumerType) {
    this.consumerType = consumerType;
  }

  public String getIntentHouse() {
    return intentHouse;
  }

  public void setIntentHouse(String intentHouse) {
    this.intentHouse = intentHouse;
  }

  public String getResistance() {
    return resistance;
  }

  public void setResistance(String resistance) {
    this.resistance = resistance;
  }

  public String getBuyIntent() {
    return buyIntent;
  }

  public void setBuyIntent(String buyIntent) {
    this.buyIntent = buyIntent;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public long getTaskId() {
    return taskId;
  }

  public void setTaskId(long taskId) {
    this.taskId = taskId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSalerName() {
    return salerName;
  }

  public void setSalerName(String salerName) {
    this.salerName = salerName;
  }

  public int getIsNotInputNumber() {
    return isNotInputNumber;
  }

  public void setIsNotInputNumber(int isNotInputNumber) {
    this.isNotInputNumber = isNotInputNumber;
  }
}
