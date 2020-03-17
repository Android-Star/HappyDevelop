package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class StockIntent implements Intent, Serializable {
  public static final String SH = "sh";
  public static final String SZ = "sz";
  public static final String HK = "hk";
  public static final String US = "us";

  private String id;
  private String name;
  private String exchange;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }
}
