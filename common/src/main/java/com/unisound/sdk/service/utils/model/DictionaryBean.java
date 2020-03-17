package com.unisound.sdk.service.utils.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class DictionaryBean implements Serializable {

  private String jijie;
  private String wenzi;
  private HashMap<String, String> pinyinUrl;
  private String zi;
  private List<String> pinyin;

  public String getJijie() {
    return jijie;
  }

  public void setJijie(String jijie) {
    this.jijie = jijie;
  }

  public String getWenzi() {
    return wenzi;
  }

  public void setWenzi(String wenzi) {
    this.wenzi = wenzi;
  }

  public HashMap<String, String> getPinyinUrl() {
    return pinyinUrl;
  }

  public void setPinyinUrl(HashMap<String, String> pinyinUrl) {
    this.pinyinUrl = pinyinUrl;
  }

  public String getZi() {
    return zi;
  }

  public void setZi(String zi) {
    this.zi = zi;
  }

  public List<String> getPinyin() {
    return pinyin;
  }

  public void setPinyin(List<String> pinyin) {
    this.pinyin = pinyin;
  }
}
