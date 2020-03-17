package com.unisound.sdk.service.utils.ota.bean;

import java.io.Serializable;

public class OtaBaseResponse<T> implements Serializable {

  private String returnCode;
  private String message;
  private int costTime;
  private T result;

  public String getReturnCode() {
    return returnCode;
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

  public int getCostTime() {
    return costTime;
  }

  public void setCostTime(int costTime) {
    this.costTime = costTime;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
