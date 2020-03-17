package com.unisound.sdk.service.utils.basebean;

import java.io.Serializable;

public class BaseRequest implements Serializable {
  private String tdid;
  private String udid;
  private String operate;

  public String getTdid() {
    return tdid;
  }

  public void setTdid(String tdid) {
    this.tdid = tdid;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public String getOperate() {
    return operate;
  }

  public void setOperate(String operate) {
    this.operate = operate;
  }
}
