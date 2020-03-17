package com.unisound.sdk.service.utils.account.bean;

import java.io.Serializable;

public class AccountBaseResponse<T> implements Serializable {

  private String returnCode;
  private String message;
  private String costTime;
  private T result;

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public String getCostTime() {
    return costTime;
  }

  public void setCostTime(String costTime) {
    this.costTime = costTime;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isSuccessResult() {
    return "uc_0000".equals(returnCode);
  }
}
