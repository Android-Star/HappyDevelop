package com.unisound.sdk.service.utils.kar.fruitrecognize.response;

import java.io.Serializable;

public class FruitInfoResponse implements Serializable {
  private double confidence;
  private String category;
  private FruitBoxInfo box;
  private String imgUrl;
  private String chName;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public FruitBoxInfo getBox() {
    return box;
  }

  public void setBox(FruitBoxInfo box) {
    this.box = box;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getChName() {
    return chName;
  }

  public void setChName(String chName) {
    this.chName = chName;
  }
}
