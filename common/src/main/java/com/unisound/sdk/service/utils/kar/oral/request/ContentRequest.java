package com.unisound.sdk.service.utils.kar.oral.request;

import java.io.Serializable;

public class ContentRequest implements Serializable {
  public long getCode() {
    return code;
  }
  public void setCode(long code) {
    this.code = code;
  }
  private long code;
}
