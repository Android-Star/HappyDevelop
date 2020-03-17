package com.unisound.sdk.service.utils.mqtt.bean;

public class MqttReceiveMessage extends SupMessage {

  private String msgId;
  private long ts;
  private String msgType;

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public long getTs() {
    return ts;
  }

  public void setTs(long ts) {
    this.ts = ts;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }
}
