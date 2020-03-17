package com.example.wilsonhan.happydevelop.net.response;

import java.util.List;

public class Tags {
  private String key;
  private String name;
  private List<Values> values;

  public void setKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setValues(List<Values> values) {
    this.values = values;
  }

  public List<Values> getValues() {
    return values;
  }
}
