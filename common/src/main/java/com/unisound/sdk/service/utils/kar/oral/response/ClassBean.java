package com.unisound.sdk.service.utils.kar.oral.response;

import java.io.Serializable;

public class ClassBean implements Serializable {
  private long id;
  private String name;
  private String img;
  private long code;
  private String audioUrl;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public long getCode() {
    return code;
  }

  public void setCode(long code) {
    this.code = code;
  }

  public String getAudioUrl() {
    return audioUrl;
  }

  public void setAudioUrl(String audioUrl) {
    this.audioUrl = audioUrl;
  }
}
