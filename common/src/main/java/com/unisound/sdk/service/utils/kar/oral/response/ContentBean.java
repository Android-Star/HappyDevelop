package com.unisound.sdk.service.utils.kar.oral.response;

import java.io.Serializable;

public class ContentBean implements Serializable {
  private int id;
  private long code;
  private String poemName;
  private String verseName;
  private int orderNum;
  private String url;
  private long duration;
  private int bitrate;
  private long frequency;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getCode() {
    return code;
  }

  public void setCode(long code) {
    this.code = code;
  }

  public String getPoemName() {
    return poemName;
  }

  public void setPoemName(String poemName) {
    this.poemName = poemName;
  }

  public String getVerseName() {
    return verseName;
  }

  public void setVerseName(String verseName) {
    this.verseName = verseName;
  }

  public int getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(int orderNum) {
    this.orderNum = orderNum;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public int getBitrate() {
    return bitrate;
  }

  public void setBitrate(int bitrate) {
    this.bitrate = bitrate;
  }

  public long getFrequency() {
    return frequency;
  }

  public void setFrequency(long frequency) {
    this.frequency = frequency;
  }
}
