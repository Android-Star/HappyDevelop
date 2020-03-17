package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class TrafficControlResult implements Result, Serializable {

  private List<TrafficControlInfo> trafficControlInfos = Collections.emptyList();
  private String nonlocal;
  private String local;
  private String city;
  private String errorCode;

  public List<TrafficControlInfo> getTrafficControlInfos() {
    return trafficControlInfos;
  }

  public void setTrafficControlInfos(List<TrafficControlInfo> trafficControlInfos) {
    this.trafficControlInfos = trafficControlInfos;
  }

  public String getNonlocal() {
    return nonlocal;
  }

  public void setNonlocal(String nonlocal) {
    this.nonlocal = nonlocal;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public static class TrafficControlInfo implements Serializable {
    private String forbiddenTailNumber;
    private String week;
    private String forbiddenDate;

    public String getForbiddenTailNumber() {
      return forbiddenTailNumber;
    }

    public void setForbiddenTailNumber(String forbiddenTailNumber) {
      this.forbiddenTailNumber = forbiddenTailNumber;
    }

    public String getWeek() {
      return week;
    }

    public void setWeek(String week) {
      this.week = week;
    }

    public String getForbiddenDate() {
      return forbiddenDate;
    }

    public void setForbiddenDate(String forbiddenDate) {
      this.forbiddenDate = forbiddenDate;
    }
  }
}
