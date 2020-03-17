package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class WechatIntent implements Intent, Serializable {
  private String content;
  private String name;
  private MessageTtsInfo ttsInfo;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MessageTtsInfo getTtsInfo() {
    return ttsInfo;
  }

  public void setTtsInfo(MessageTtsInfo ttsInfo) {
    this.ttsInfo = ttsInfo;
  }
}
