package com.unisound.sdk.service.utils.kar.calendar.request;

import java.io.Serializable;

public class CalendarDeleteRequest implements Serializable {
  private long id;
  private int type;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
