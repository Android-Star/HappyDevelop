package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class NavigationIntent implements Intent, Serializable {

  private String sourcePckName;
  private double fLatitude;
  private double fLontitude;
  private double tLatitude;
  private double tLontitude;
  private String fromCity;
  private String fromPoi;
  private String toCity;
  private String toPoi;
  private String condition;
  private int category = 0;
  private boolean addPassPoint;
  private String pathPointName;
  private double pathPointLatitude;
  private double pathPointLontitude;
  private String playTts;

  public String getSourcePckName() {
    return sourcePckName;
  }

  public void setSourcePckName(String sourcePckName) {
    this.sourcePckName = sourcePckName;
  }

  public String getPlayTts() {
    return playTts;
  }

  public void setPlayTts(String playTts) {
    this.playTts = playTts;
  }

  public String getPathPointName() {
    return pathPointName;
  }

  public void setPathPointName(String pathPointName) {
    this.pathPointName = pathPointName;
  }

  public double getPathPointLatitude() {
    return pathPointLatitude;
  }

  public void setPathPointLatitude(double pathPointNameLatitude) {
    this.pathPointLatitude = pathPointNameLatitude;
  }

  public double getPathPointLontitude() {
    return pathPointLontitude;
  }

  public void setPathPointLontitude(double pathPointNameLontitude) {
    this.pathPointLontitude = pathPointNameLontitude;
  }

  public boolean isAddPassPoint() {
    return addPassPoint;
  }

  public void setAddPassPoint(boolean addPassPoint) {
    this.addPassPoint = addPassPoint;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public double getfLatitude() {
    return fLatitude;
  }

  public void setfLatitude(double fLatitude) {
    this.fLatitude = fLatitude;
  }

  public double getfLontitude() {
    return fLontitude;
  }

  public void setfLontitude(double fLontitude) {
    this.fLontitude = fLontitude;
  }

  public double gettLatitude() {
    return tLatitude;
  }

  public void settLatitude(double tLatitude) {
    this.tLatitude = tLatitude;
  }

  public double gettLontitude() {
    return tLontitude;
  }

  public void settLontitude(double tLontitude) {
    this.tLontitude = tLontitude;
  }

  public String getFromCity() {
    return fromCity;
  }

  public void setFromCity(String fromCity) {
    this.fromCity = fromCity;
  }

  public String getFromPoi() {
    return fromPoi;
  }

  public void setFromPoi(String fromPoi) {
    this.fromPoi = fromPoi;
  }

  public String getToCity() {
    return toCity;
  }

  public void setToCity(String toCity) {
    this.toCity = toCity;
  }

  public String getToPoi() {
    return toPoi;
  }

  public void setToPoi(String toPoi) {
    this.toPoi = toPoi;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }
}
