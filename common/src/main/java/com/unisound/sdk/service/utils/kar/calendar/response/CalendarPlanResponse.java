package com.unisound.sdk.service.utils.kar.calendar.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class CalendarPlanResponse implements Serializable {

  @SerializedName("1") private List<CalendarInfo2> monday;
  @SerializedName("2") private List<CalendarInfo2> tuesday;
  @SerializedName("3") private List<CalendarInfo2> wednesday;
  @SerializedName("4") private List<CalendarInfo2> thursday;
  @SerializedName("5") private List<CalendarInfo2> friday;
  @SerializedName("6") private List<CalendarInfo2> saturday;
  @SerializedName("7") private List<CalendarInfo2> sunday;

  public List<CalendarInfo2> getMonday() {
    return monday;
  }

  public void setMonday(List<CalendarInfo2> monday) {
    this.monday = monday;
  }

  public List<CalendarInfo2> getTuesday() {
    return tuesday;
  }

  public void setTuesday(List<CalendarInfo2> tuesday) {
    this.tuesday = tuesday;
  }

  public List<CalendarInfo2> getWednesday() {
    return wednesday;
  }

  public void setWednesday(List<CalendarInfo2> wednesday) {
    this.wednesday = wednesday;
  }

  public List<CalendarInfo2> getThursday() {
    return thursday;
  }

  public void setThursday(List<CalendarInfo2> thursday) {
    this.thursday = thursday;
  }

  public List<CalendarInfo2> getFriday() {
    return friday;
  }

  public void setFriday(List<CalendarInfo2> friday) {
    this.friday = friday;
  }

  public List<CalendarInfo2> getSaturday() {
    return saturday;
  }

  public void setSaturday(List<CalendarInfo2> saturday) {
    this.saturday = saturday;
  }

  public List<CalendarInfo2> getSunday() {
    return sunday;
  }

  public void setSunday(List<CalendarInfo2> sunday) {
    this.sunday = sunday;
  }
}
