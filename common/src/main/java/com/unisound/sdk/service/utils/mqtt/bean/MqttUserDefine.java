package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class MqttUserDefine implements Serializable {
  private String protocolType;
  private String businessType;
  private String command;
  private DataBean data;
  private PromiseBean promise;
  private TclBean tcl;
  private String version;

  public String getProtocolType() {
    return protocolType;
  }

  public void setProtocolType(String protocolType) {
    this.protocolType = protocolType;
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

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public PromiseBean getPromise() {
    return promise;
  }

  public void setPromise(PromiseBean promise) {
    this.promise = promise;
  }

  public TclBean getTcl() {
    return tcl;
  }

  public void setTcl(TclBean tcl) {
    this.tcl = tcl;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public static class DataBean {
    private String udid;

    public String getUdid() {
      return udid;
    }

    public void setUdid(String udid) {
      this.udid = udid;
    }
  }

  public static class PromiseBean {
    private String udid;

    public String getUdid() {
      return udid;
    }

    public void setUdid(String udid) {
      this.udid = udid;
    }
  }

  public static class TclBean {
    private String clientId;
    private int subSysId;
    private String token;

    public String getClientId() {
      return clientId;
    }

    public void setClientId(String clientId) {
      this.clientId = clientId;
    }

    public int getSubSysId() {
      return subSysId;
    }

    public void setSubSysId(int subSysId) {
      this.subSysId = subSysId;
    }

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }
  }
}
