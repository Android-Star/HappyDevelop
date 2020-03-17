package com.unisound.sdk.service.utils.kar.idiom.request;

import java.io.Serializable;

public class PlayGameRequest implements Serializable {
  private String lastWord;
  private String udid;
  private String imei;

  public String getLastWord() {
    return lastWord;
  }

  public void setLastWord(String lastWord) {
    this.lastWord = lastWord;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }
}
