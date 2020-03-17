package com.unisound.sdk.service.utils.kar.basebean;

import java.io.Serializable;

public class KarBaseResponse<T> implements Serializable {
  private int errorCode = Integer.MAX_VALUE;
  private String errorMsg;
  private T data;

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
