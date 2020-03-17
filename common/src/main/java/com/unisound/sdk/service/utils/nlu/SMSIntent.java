package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class SMSIntent extends CallIntent implements Serializable {
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
