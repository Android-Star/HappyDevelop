package com.unisound.sdk.service.utils.unione.bean.chat;

import java.io.Serializable;

public class ChatBaseMsg<T> implements Serializable {

  public static final String TYPE_CHAT = "chat";
  public static final String TYPE_MUSIC = "music";
  public static final String TYPE_ALARM = "alarm";
  public static final String TYPE_REMINDER = "reminder";
  public static final String TYPE_COUNTDOWN = "countdown";
  public static final String TYPE_WEATHER = "weather";

  private String msgId;
  private String type;
  private String command;
  private String reply;
  private String createTime;
  private T data;

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
