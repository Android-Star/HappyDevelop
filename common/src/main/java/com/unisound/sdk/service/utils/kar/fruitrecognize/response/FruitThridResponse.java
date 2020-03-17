package com.unisound.sdk.service.utils.kar.fruitrecognize.response;

import java.io.Serializable;
import java.util.List;

public class FruitThridResponse implements Serializable {
  private long errCode = -1;
  private String errMsg;
  private List<FruitUploadResponse> results;

  public long getErrCode() {
    return errCode;
  }

  public void setErrCode(long errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public List<FruitUploadResponse> getResults() {
    return results;
  }

  public void setResults(List<FruitUploadResponse> results) {
    this.results = results;
  }
}
