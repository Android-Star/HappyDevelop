package com.unisound.sdk.service.utils.model;

import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;

public class CommonRequest {
  private String businessType;
  private String command;
  private Object data;
  private KarBaseRequest.TclBean tcl;
  private String version;

  public String getBusinessType() {
    return businessType;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public KarBaseRequest.TclBean getTcl() {
    return tcl;
  }

  public void setTcl(KarBaseRequest.TclBean tcl) {
    this.tcl = tcl;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
