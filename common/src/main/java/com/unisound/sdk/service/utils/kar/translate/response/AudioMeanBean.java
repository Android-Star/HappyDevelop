package com.unisound.sdk.service.utils.kar.translate.response;

public class AudioMeanBean {
  private String tspeech;
  private String query;
  private String translation;
  private String origin;
  private String target;
  private String speech;

  public String getTspeech() {
    return tspeech;
  }

  public void setTspeech(String tspeech) {
    this.tspeech = tspeech;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getTranslation() {
    return translation;
  }

  public void setTranslation(String translation) {
    this.translation = translation;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getSpeech() {
    return speech;
  }

  public void setSpeech(String speech) {
    this.speech = speech;
  }
}
