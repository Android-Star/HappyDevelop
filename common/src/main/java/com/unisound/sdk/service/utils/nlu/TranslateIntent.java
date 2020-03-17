package com.unisound.sdk.service.utils.nlu;

public class TranslateIntent implements Intent {
  private String source;
  private String target;
  private String sentence;

  public String getSource() {
    return source;
  }

  public void setSource(String s) {
    source = s;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String t) {
    this.target = t;
  }

  public String getSentence() {
    return sentence;
  }

  public void setSentence(String s) {
    this.sentence = s;
  }
}
