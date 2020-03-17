package com.unisound.sdk.service.utils.kar.calendar.request;

import java.io.Serializable;

public class CalendarPlanRequest implements Serializable {
  private long deviceId;

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Long deviceId) {
    this.deviceId = deviceId;
  }
}
