package com.unisound.sdk.service.utils.mqtt.bean;

public class OnlineMessage extends SupMessage {

  private String clientId;
  private int eventType;
  private ClientInfo clientInfo;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public int getEventType() {
    return eventType;
  }

  public void setEventType(int eventType) {
    this.eventType = eventType;
  }

  public ClientInfo getClientInfo() {
    return clientInfo;
  }

  public void setClientInfo(ClientInfo clientInfo) {
    this.clientInfo = clientInfo;
  }
}
