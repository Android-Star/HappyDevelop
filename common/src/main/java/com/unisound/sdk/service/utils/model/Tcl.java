package com.unisound.sdk.service.utils.model;

import com.unisound.sdk.service.utils.constants.Constant;

public class Tcl {

  private int subSysId = Constant.SUB_SYSTEM_ID;
  private String token;

  public void setSubSysId(int subSysId) {
    this.subSysId = subSysId;
  }

  public int getSubSysId() {
    return subSysId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
