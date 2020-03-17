package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class DstServiceBean<P> implements Serializable {

  private String dstState;
  private UniCommandBean<P> uniCommand;
  private String dstServiceName;
  private String dstName;
  private Accelerate accelerate;
  private P parameter;

  public String getDstState() {
    return dstState;
  }

  public void setDstState(String dstState) {
    this.dstState = dstState;
  }

  public UniCommandBean<P> getUniCommand() {
    return uniCommand;
  }

  public void setUniCommand(UniCommandBean<P> uniCommand) {
    this.uniCommand = uniCommand;
  }

  public String getDstServiceName() {
    return dstServiceName;
  }

  public void setDstServiceName(String dstServiceName) {
    this.dstServiceName = dstServiceName;
  }

  public P getParameter() {
    return parameter;
  }

  public void setParameter(P parameter) {
    this.parameter = parameter;
  }

  public Accelerate getAccelerate() {
    return accelerate;
  }

  public void setAccelerate(Accelerate accelerate) {
    this.accelerate = accelerate;
  }

  public String getDstName() {
    return dstName;
  }

  public void setDstName(String dstName) {
    this.dstName = dstName;
  }

  public static class UniCommandBean<P> implements Serializable {
    private String version;
    private String capability;
    private String operation;
    private P parameter;

    public String getVersion() {
      return version;
    }

    public void setVersion(String version) {
      this.version = version;
    }

    public String getCapability() {
      return capability;
    }

    public void setCapability(String capability) {
      this.capability = capability;
    }

    public String getOperation() {
      return operation;
    }

    public void setOperation(String operation) {
      this.operation = operation;
    }

    public P getParameter() {
      return parameter;
    }

    public void setParameter(P parameter) {
      this.parameter = parameter;
    }
  }

  public static class Accelerate {
    String command;
    String valuse;

    public String getCommand() {
      return command;
    }

    public void setCommand(String command) {
      this.command = command;
    }

    public String getValuse() {
      return valuse;
    }

    public void setValuse(String valuse) {
      this.valuse = valuse;
    }
  }
}
