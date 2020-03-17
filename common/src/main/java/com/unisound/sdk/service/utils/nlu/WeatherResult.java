package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class WeatherResult implements Result, Serializable {

  private List<WeatherDay> weatherDays = Collections.emptyList();
  private String cityName;
  private String cityCode;
  private String updateTime;
  private int focusDateIndex;
  private int errorCode;

  public List<WeatherDay> getWeatherDays() {
    return weatherDays;
  }

  public void setWeatherDays(List<WeatherDay> weatherDays) {
    this.weatherDays = weatherDays;
  }

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

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public int getFocusDateIndex() {
    return focusDateIndex;
  }

  public void setFocusDateIndex(int focusDateIndex) {
    this.focusDateIndex = focusDateIndex;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public static class WeatherDay implements Serializable {
    private String year;
    private String month;
    private String day;
    private String dayOfWeek;
    private String weather;
    private String highestTemperature;
    private String lowestTemperature;
    private String currentTemperature;
    private String pm2_5;
    private String quality;
    private String wind;
    private String imageTitleOfDay;
    private String imageTitleOfNight;
    private String comfortIndex;
    private String comfortIndexDesc;
    private String carWashIndex;
    private String carWashIndexDesc;
    private String dressIndex;
    private String dressIndexDesc;
    private String sunBlockIndex;
    private String sunBlockIndexDesc;
    private String sportIndex;
    private String sportIndexDesc;
    private String dryingIndex;
    private String dryingIndexDesc;
    private String morningExerciseIndex;
    private String morningExerciseIndexDesc;
    private String coldIndex;
    private String coldIndexDesc;
    private String datingIndex;
    private String datingIndexDesc;
    private String umbrellaIndex;
    private String umbrellaIndexDesc;
    private String travelIndex;
    private String travelIndexDesc;
    private String suggest;

    public String getYear() {
      return year;
    }

    public void setYear(String year) {
      this.year = year;
    }

    public String getMonth() {
      return month;
    }

    public void setMonth(String month) {
      this.month = month;
    }

    public String getDay() {
      return day;
    }

    public void setDay(String day) {
      this.day = day;
    }

    public String getDayOfWeek() {
      return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
      this.dayOfWeek = dayOfWeek;
    }

    public String getWeather() {
      return weather;
    }

    public void setWeather(String weather) {
      this.weather = weather;
    }

    public String getHighestTemperature() {
      return highestTemperature;
    }

    public void setHighestTemperature(String highestTemperature) {
      this.highestTemperature = highestTemperature;
    }

    public String getLowestTemperature() {
      return lowestTemperature;
    }

    public void setLowestTemperature(String lowestTemperature) {
      this.lowestTemperature = lowestTemperature;
    }

    public String getCurrentTemperature() {
      return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
      this.currentTemperature = currentTemperature;
    }

    public String getQuality() {
      return quality;
    }

    public void setQuality(String quality) {
      this.quality = quality;
    }

    public String getWind() {
      return wind;
    }

    public void setWind(String wind) {
      this.wind = wind;
    }

    public String getImageTitleOfDay() {
      return imageTitleOfDay;
    }

    public void setImageTitleOfDay(String imageTitleOfDay) {
      this.imageTitleOfDay = imageTitleOfDay;
    }

    public String getImageTitleOfNight() {
      return imageTitleOfNight;
    }

    public void setImageTitleOfNight(String imageTitleOfNight) {
      this.imageTitleOfNight = imageTitleOfNight;
    }

    public String getComfortIndex() {
      return comfortIndex;
    }

    public void setComfortIndex(String comfortIndex) {
      this.comfortIndex = comfortIndex;
    }

    public String getComfortIndexDesc() {
      return comfortIndexDesc;
    }

    public void setComfortIndexDesc(String comfortIndexDesc) {
      this.comfortIndexDesc = comfortIndexDesc;
    }

    public String getCarWashIndex() {
      return carWashIndex;
    }

    public void setCarWashIndex(String carWashIndex) {
      this.carWashIndex = carWashIndex;
    }

    public String getCarWashIndexDesc() {
      return carWashIndexDesc;
    }

    public void setCarWashIndexDesc(String carWashIndexDesc) {
      this.carWashIndexDesc = carWashIndexDesc;
    }

    public String getDressIndex() {
      return dressIndex;
    }

    public void setDressIndex(String dressIndex) {
      this.dressIndex = dressIndex;
    }

    public String getDressIndexDesc() {
      return dressIndexDesc;
    }

    public void setDressIndexDesc(String dressIndexDesc) {
      this.dressIndexDesc = dressIndexDesc;
    }

    public String getSunBlockIndex() {
      return sunBlockIndex;
    }

    public void setSunBlockIndex(String sunBlockIndex) {
      this.sunBlockIndex = sunBlockIndex;
    }

    public String getSunBlockIndexDesc() {
      return sunBlockIndexDesc;
    }

    public void setSunBlockIndexDesc(String sunBlockIndexDesc) {
      this.sunBlockIndexDesc = sunBlockIndexDesc;
    }

    public String getSportIndex() {
      return sportIndex;
    }

    public void setSportIndex(String sportIndex) {
      this.sportIndex = sportIndex;
    }

    public String getSportIndexDesc() {
      return sportIndexDesc;
    }

    public void setSportIndexDesc(String sportIndexDesc) {
      this.sportIndexDesc = sportIndexDesc;
    }

    public String getDryingIndex() {
      return dryingIndex;
    }

    public void setDryingIndex(String dryingIndex) {
      this.dryingIndex = dryingIndex;
    }

    public String getDryingIndexDesc() {
      return dryingIndexDesc;
    }

    public void setDryingIndexDesc(String dryingIndexDesc) {
      this.dryingIndexDesc = dryingIndexDesc;
    }

    public String getMorningExerciseIndex() {
      return morningExerciseIndex;
    }

    public void setMorningExerciseIndex(String morningExerciseIndex) {
      this.morningExerciseIndex = morningExerciseIndex;
    }

    public String getMorningExerciseIndexDesc() {
      return morningExerciseIndexDesc;
    }

    public void setMorningExerciseIndexDesc(String morningExerciseIndexDesc) {
      this.morningExerciseIndexDesc = morningExerciseIndexDesc;
    }

    public String getColdIndex() {
      return coldIndex;
    }

    public void setColdIndex(String coldIndex) {
      this.coldIndex = coldIndex;
    }

    public String getColdIndexDesc() {
      return coldIndexDesc;
    }

    public void setColdIndexDesc(String coldIndexDesc) {
      this.coldIndexDesc = coldIndexDesc;
    }

    public String getDatingIndex() {
      return datingIndex;
    }

    public void setDatingIndex(String datingIndex) {
      this.datingIndex = datingIndex;
    }

    public String getDatingIndexDesc() {
      return datingIndexDesc;
    }

    public void setDatingIndexDesc(String datingIndexDesc) {
      this.datingIndexDesc = datingIndexDesc;
    }

    public String getUmbrellaIndex() {
      return umbrellaIndex;
    }

    public void setUmbrellaIndex(String umbrellaIndex) {
      this.umbrellaIndex = umbrellaIndex;
    }

    public String getUmbrellaIndexDesc() {
      return umbrellaIndexDesc;
    }

    public void setUmbrellaIndexDesc(String umbrellaIndexDesc) {
      this.umbrellaIndexDesc = umbrellaIndexDesc;
    }

    public String getTravelIndex() {
      return travelIndex;
    }

    public void setTravelIndex(String travelIndex) {
      this.travelIndex = travelIndex;
    }

    public String getTravelIndexDesc() {
      return travelIndexDesc;
    }

    public void setTravelIndexDesc(String travelIndexDesc) {
      this.travelIndexDesc = travelIndexDesc;
    }

    public String getSuggest() {
      return suggest;
    }

    public void setSuggest(String suggest) {
      this.suggest = suggest;
    }

    public String getPm2_5() {
      return pm2_5;
    }

    public void setPm2_5(String pm2_5) {
      this.pm2_5 = pm2_5;
    }
  }
}
