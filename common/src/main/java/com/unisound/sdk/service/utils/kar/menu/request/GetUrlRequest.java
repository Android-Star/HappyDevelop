package com.unisound.sdk.service.utils.kar.menu.request;

import java.io.Serializable;


public class GetUrlRequest implements Serializable {
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
