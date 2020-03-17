package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class SettingExtIntent implements Intent, Serializable {
  private List<SettingIntent> operations = Collections.emptyList();
  private String confirm;
  private String operator;

  public List<SettingIntent> getOperations() {
    return operations;
  }

  public void setOperations(List<SettingIntent> operations) {

    this.operations = operations;
  }

  public String getConfirm() {
    return confirm;
  }

  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
