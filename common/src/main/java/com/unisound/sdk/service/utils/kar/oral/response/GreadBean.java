package com.unisound.sdk.service.utils.kar.oral.response;

import java.io.Serializable;

public class GreadBean implements Serializable {
  private int id;
  private String name;
  private String img;
  private String tcList;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public String getTcList() {
    return tcList;
  }

  public void setTcList(String tcList) {
    this.tcList = tcList;
  }
}
