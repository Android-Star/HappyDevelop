package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class VideoIntent implements Intent, Serializable {
  private String keyword;
  private String name;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
