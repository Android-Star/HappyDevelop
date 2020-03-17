package com.unisound.sdk.service.utils.basebean;

import java.io.Serializable;

public class BaseRequestHeader<T> implements Serializable {
  private String version = "1.0.0";
  private EffectiveToken tcl;
  private String businessType;
  private String command;
  private String protocolType;
  private Promise promise;
  private T data;

  public BaseRequestHeader() {
  }

  public EffectiveToken getTcl() {
    return tcl;
  }

  public void setTcl(EffectiveToken tcl) {
    this.tcl = tcl;
  }

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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getProtocolType() {
    return protocolType;
  }

  public void setProtocolType(String protocolType) {
    this.protocolType = protocolType;
  }

  public Promise getPromise() {
    return promise;
  }

  public void setPromise(Promise promise) {
    this.promise = promise;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public static class Promise {
    private String udid;

    public String getUdid() {
      return udid;
    }

    public void setUdid(String udid) {
      this.udid = udid;
    }
  }
}
