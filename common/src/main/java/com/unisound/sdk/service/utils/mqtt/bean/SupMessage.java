package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class SupMessage implements Serializable {
  private String msg;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
