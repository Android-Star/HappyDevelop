package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class AudioIntent extends MusicIntent implements Serializable {
  private String category;
  private String tag;
  private String name;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
