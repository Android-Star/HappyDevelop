package com.unisound.sdk.service.utils.kar.calendar.request;

import java.io.Serializable;

public class QueryScheduleRequest implements Serializable {
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
