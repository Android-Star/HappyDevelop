package com.unisound.sdk.service.utils.kar.idiom.response;

import java.io.Serializable;

public class RankingBean implements Serializable {
  private String uicon;
  private int uscore;
  private long id;
  private String updateTime;
  private String nickname;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getUicon() {
    return uicon;
  }

  public void setUicon(String uicon) {
    this.uicon = uicon;
  }

  public int getUscore() {
    return uscore;
  }

  public void setUscore(int uscore) {
    this.uscore = uscore;
  }
}
