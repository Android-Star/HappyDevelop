package com.unisound.sdk.service.utils.kar.menu.response;

import java.io.Serializable;
import java.util.List;

public class SkillTipResponse implements Serializable {
  private String categoryName;
  private List<String> tipList;

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public List<String> getTipList() {
    return tipList;
  }

  public void setTipList(List<String> tipList) {
    this.tipList = tipList;
  }
}
