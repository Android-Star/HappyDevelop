package com.unisound.sdk.service.utils.nlu;

public class TrafficControlIntent implements Intent {
  private String city;
  private String date;
  private String timeExpr;
  private String focus;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTimeExpr() {
    return timeExpr;
  }

  public void setTimeExpr(String timeExpr) {
    this.timeExpr = timeExpr;
  }

  public String getFocus() {
    return focus;
  }

  public void setFocus(String focus) {
    this.focus = focus;
  }
}
