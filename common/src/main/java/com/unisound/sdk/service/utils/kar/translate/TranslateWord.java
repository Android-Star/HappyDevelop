package com.unisound.sdk.service.utils.kar.translate;

import java.io.Serializable;

public class TranslateWord implements Serializable {

  private String query;
  private String origin;
  private String target;
  private String bindingId;
  private int pageNum;
  private int pageSize;
  private long startId;
  private int num;
  private String word;
  private String udid;
  private String imei;
  private String[] idList;

  public String getQuery() {
    return query;
  }

  public long getStartId() {
    return startId;
  }

  public void setStartId(long startId) {
    this.startId = startId;
  }

  public void setQuery(String query) {

    this.query = query;
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

  public String getBindingId() {
    return bindingId;
  }

  public void setBindingId(String bindingId) {
    this.bindingId = bindingId;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String[] getIdList() {
    return idList;
  }

  public void setIdList(String[] idList) {
    this.idList = idList;
  }
}
