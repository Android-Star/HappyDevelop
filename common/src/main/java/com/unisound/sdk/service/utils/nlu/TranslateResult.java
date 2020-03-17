package com.unisound.sdk.service.utils.nlu;

public class TranslateResult implements Result {

  private String target;
  private String translated;
  private int errorCode;
  private String sententce;

  public String getTarget() {
    return target;
  }

  public void setTarget(String s) {
    target = s;
  }

  public String getTranslated() {
    return translated;
  }

  public void setTranslated(String s) {
    translated = s;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int c) {
    errorCode = c;
  }

  public String getSententce() {
    return sententce;
  }

  public void setSententce(String s) {
    sententce = s;
  }
}
