package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class Data<R extends Result> implements Serializable {
  private String header;

  private String headerTts;

  private R result;

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getHeaderTts() {
    return headerTts;
  }

  public void setHeaderTts(String headerTts) {
    this.headerTts = headerTts;
  }

  public R getResult() {
    return result;
  }

  public void setResult(R result) {
    this.result = result;
  }
}
