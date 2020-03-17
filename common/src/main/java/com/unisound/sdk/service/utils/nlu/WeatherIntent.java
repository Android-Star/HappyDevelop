package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class WeatherIntent implements Intent, Serializable {
  private String province;
  private String city;
  private String cityCode;
  private String focusDate;
  private String topic;

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public String getFocusDate() {
    return focusDate;
  }

  public void setFocusDate(String focusDate) {
    this.focusDate = focusDate;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }
}
