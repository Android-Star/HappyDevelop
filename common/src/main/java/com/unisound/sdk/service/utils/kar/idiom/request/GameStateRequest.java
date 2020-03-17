package com.unisound.sdk.service.utils.kar.idiom.request;

import java.io.Serializable;

public class GameStateRequest implements Serializable {
  private String udid;
  private boolean needRanking;

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public boolean isNeedRanking() {
    return needRanking;
  }

  public void setNeedRanking(boolean needRanking) {
    this.needRanking = needRanking;
  }
}
