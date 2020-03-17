package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class NoteIntent implements Intent, Serializable {

  String topic;
  String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }
}
