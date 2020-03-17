package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class Semantic<T extends Intent> implements Serializable {
  private String normalHeader;
  private String normalHeaderTts;
  private String noDataHeader;
  private String noDataHeaderTts;
  private T intent;

  public String getNormalHeader() {
    return normalHeader;
  }

  public void setNormalHeader(String normalHeader) {
    this.normalHeader = normalHeader;
  }

  public String getNormalHeaderTts() {
    return normalHeaderTts;
  }

  public void setNormalHeaderTts(String normalHeaderTts) {
    this.normalHeaderTts = normalHeaderTts;
  }

  public String getNoDataHeader() {
    return noDataHeader;
  }

  public void setNoDataHeader(String noDataHeader) {
    this.noDataHeader = noDataHeader;
  }

  public String getNoDataHeaderTts() {
    return noDataHeaderTts;
  }

  public void setNoDataHeaderTts(String noDataHeaderTts) {
    this.noDataHeaderTts = noDataHeaderTts;
  }

  public T getIntent() {
    return intent;
  }

  public void setIntent(T intent) {
    this.intent = intent;
  }
}
