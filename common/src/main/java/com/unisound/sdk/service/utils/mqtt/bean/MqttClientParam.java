package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class MqttClientParam implements Serializable {
  private String connectUrl;
  private String clientId;
  private String userName;
  private String passWord;
  private String[] subscribe;
  private String publish;

  public String getConnectUrl() {
    return connectUrl;
  }

  public void setConnectUrl(String connectUrl) {
    this.connectUrl = connectUrl;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String[] getSubscribe() {
    return subscribe;
  }

  public void setSubscribe(String[] subscribe) {
    this.subscribe = subscribe;
  }

  public String getPublish() {
    return publish;
  }

  public void setPublish(String publish) {
    this.publish = publish;
  }
}
