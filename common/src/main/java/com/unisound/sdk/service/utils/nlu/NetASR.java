/*
 * Copyright (c) 2012-2015 Beijing Unisound Information Technology Co., Ltd. All right reserved.
 */

package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class NetASR extends ASR implements Serializable {
  private boolean lastResult;

  private String sessionID;

  public boolean isLastResult() {
    return lastResult;
  }

  public void setLastResult(boolean lastResult) {
    this.lastResult = lastResult;
  }

  public String getSessionID() {
    return sessionID;
  }

  public void setSessionID(String sessionID) {
    this.sessionID = sessionID;
  }
}