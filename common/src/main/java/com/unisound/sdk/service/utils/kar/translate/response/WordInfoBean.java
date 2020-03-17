package com.unisound.sdk.service.utils.kar.translate.response;

public class WordInfoBean {
  private String bindingId;
  private String createTime;
  private String explains;
  private String id;
  private String word;
  private boolean isSelect;

  public boolean isSelect() {
    return isSelect;
  }

  public void setSelect(boolean select) {
    isSelect = select;
  }

  public String getBindingId() {
    return bindingId;
  }

  public void setBindingId(String bindingId) {
    this.bindingId = bindingId;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getExplains() {
    return explains;
  }

  public void setExplains(String explains) {
    this.explains = explains;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }
}
