package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class SalerBean implements Serializable {

  private String salerId;
  private String salerName;

  public String getSalerId() {
    return salerId;
  }

  public void setSalerId(String salerId) {
    this.salerId = salerId;
  }

  public String getSalerName() {
    return salerName;
  }

  public void setSalerName(String salerName) {
    this.salerName = salerName;
  }
}
