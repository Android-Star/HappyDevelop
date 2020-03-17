package com.unisound.sdk.service.utils.unione.bean.chat;

import java.io.Serializable;
import java.util.List;

public class MsgWeather implements Serializable {

  private String cityName;
  private String cityCode;
  private int focusDateIndex;
  private List<WeatherDaysBean> weatherDays;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public int getFocusDateIndex() {
    return focusDateIndex;
  }

  public void setFocusDateIndex(int focusDateIndex) {
    this.focusDateIndex = focusDateIndex;
  }

  public List<WeatherDaysBean> getWeatherDays() {
    return weatherDays;
  }

  public void setWeatherDays(List<WeatherDaysBean> weatherDays) {
    this.weatherDays = weatherDays;
  }

  public static class WeatherDaysBean {
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private int highestTemperature;
    private int lowestTemperature;
    private int currentTemperature;
    private String humidity;
    private String wind;
    private String windDirection;
    private String weather;
    private String quality;

    public int getYear() {
      return year;
    }

    public void setYear(int year) {
      this.year = year;
    }

    public int getMonth() {
      return month;
    }

    public void setMonth(int month) {
      this.month = month;
    }

    public int getDay() {
      return day;
    }

    public void setDay(int day) {
      this.day = day;
    }

    public int getDayOfWeek() {
      return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
      this.dayOfWeek = dayOfWeek;
    }

    public int getHighestTemperature() {
      return highestTemperature;
    }

    public void setHighestTemperature(int highestTemperature) {
      this.highestTemperature = highestTemperature;
    }

    public int getLowestTemperature() {
      return lowestTemperature;
    }

    public void setLowestTemperature(int lowestTemperature) {
      this.lowestTemperature = lowestTemperature;
    }

    public int getCurrentTemperature() {
      return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
      this.currentTemperature = currentTemperature;
    }

    public String getHumidity() {
      return humidity;
    }

    public void setHumidity(String humidity) {
      this.humidity = humidity;
    }

    public String getWind() {
      return wind;
    }

    public void setWind(String wind) {
      this.wind = wind;
    }

    public String getWindDirection() {
      return windDirection;
    }

    public void setWindDirection(String windDirection) {
      this.windDirection = windDirection;
    }

    public String getWeather() {
      return weather;
    }

    public void setWeather(String weather) {
      this.weather = weather;
    }

    public String getQuality() {
      return quality;
    }

    public void setQuality(String quality) {
      this.quality = quality;
    }
  }
}
