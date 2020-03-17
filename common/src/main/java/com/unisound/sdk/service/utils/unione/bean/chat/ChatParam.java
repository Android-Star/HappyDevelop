package com.unisound.sdk.service.utils.unione.bean.chat;

import com.unisound.sdk.service.utils.basebean.BaseRequest;

public class ChatParam extends BaseRequest {

  private int pageNo;

  private int pageSize;

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
