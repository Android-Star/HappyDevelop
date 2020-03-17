package com.unisound.sdk.service.utils.kar.oral.request;

import java.io.Serializable;

public class GreadRequest implements Serializable {
  private long id;
  private int pageNo;
  private int pageCount;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }
}
