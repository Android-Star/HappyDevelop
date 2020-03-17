package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class MessageTtsInfo implements Comparable<MessageTtsInfo>, Serializable {
  private String text;
  private int msgContentType;
  private String msgId;
  private String msgFromContactsId;
  private String msgFromName;
  private String groupMemberId;
  private long time;

  public MessageTtsInfo(String text, int msgContentType, String msgId, String msgFromContactsId,
      String msgFromGroupMemberId, String msgFromName, long time) {
    this.text = text;
    this.msgContentType = msgContentType;
    this.msgId = msgId;
    this.msgFromContactsId = msgFromContactsId;
    this.msgFromName = msgFromName;
    this.groupMemberId = msgFromGroupMemberId;
    this.time = time;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getGroupMemberId() {
    return groupMemberId;
  }

  public void setGroupMemberId(String groupMemberId) {
    this.groupMemberId = groupMemberId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getMsgContentType() {
    return msgContentType;
  }

  public void setMsgContentType(int msgContentType) {
    this.msgContentType = msgContentType;
  }

  public String getMsgFromContactsId() {
    return msgFromContactsId;
  }

  public void setMsgFromContactsId(String msgFromContactsId) {
    this.msgFromContactsId = msgFromContactsId;
  }

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public String getMsgFromName() {
    return msgFromName;
  }

  public void setMsgFromName(String msgFromName) {
    this.msgFromName = msgFromName;
  }

  public boolean isNaviMessage() {
    if (text != null && text.startsWith("type=location")) {
      return true;
    }
    return false;
  }

  @Override public int compareTo(MessageTtsInfo another) {
    return (int) (this.getTime() - another.getTime());
  }
}
