package com.unisound.sdk.service.utils.kar.oral.response;

import java.io.Serializable;
import java.util.List;

public class ContentInfoBean implements Serializable {
  private int errorCode;
  private int count;
  private List<ContentBean> poemVerses;

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<ContentBean> getPoemVerses() {
    return poemVerses;
  }

  public void setPoemVerses(List<ContentBean> poemVerses) {
    this.poemVerses = poemVerses;
  }
}
