package com.unisound.sdk.service.utils.kar.idiom.response;

import java.io.Serializable;

public class MedalBean implements Serializable {
  private static final long serialVersionUID = 2L;
  private long id;
  private int levelCount;
  private String createTime;
  private String micon;
  private String miconGray;
  private String miconBig;
  private String mname;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getLevelCount() {
    return levelCount;
  }

  public void setLevelCount(int levelCount) {
    this.levelCount = levelCount;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getMicon() {
    return micon;
  }

  public void setMicon(String micon) {
    this.micon = micon;
  }

  public String getMiconGray() {
    return miconGray;
  }

  public void setMiconGray(String miconGray) {
    this.miconGray = miconGray;
  }

  public String getMiconBig() {
    return miconBig;
  }

  public void setMiconBig(String miconBig) {
    this.miconBig = miconBig;
  }

  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }
}
