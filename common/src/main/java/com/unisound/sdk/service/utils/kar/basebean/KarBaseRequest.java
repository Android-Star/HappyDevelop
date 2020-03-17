package com.unisound.sdk.service.utils.kar.basebean;

import com.unisound.sdk.service.utils.ExoConstants;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.constants.Constant;
import java.io.Serializable;

public class KarBaseRequest<T> implements Serializable {

  private String businessType;
  private String command;
  private T data;
  private TclBean tcl;
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

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
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

  public static class TclBean {

    private String clientId;
    private int subSysId;
    private String accessToken;
    private String deviceToken;
    private String appKey = ExoConstants.APP_KEY;
    private String appKeySource;

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

    public String getAccessToken() {
      return accessToken;
    }

    public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
    }

    public String getDeviceToken() {
      return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
      this.deviceToken = deviceToken;
    }

    public String getAppKey() {
      return appKey;
    }

    public void setAppKeySource(String appKeySource) {
      this.appKeySource = appKeySource;
    }

    public String getAppKeySource() {
      return appKeySource;
    }

    public static TclBean getDefaultTclBeanDevice(String deviceToken) {
      TclBean tclBean = new TclBean();
      tclBean.setSubSysId(Constant.getSystemId());
      tclBean.setClientId(SystemUtils.getDeviceId());
      tclBean.setDeviceToken(deviceToken);
      tclBean.setAppKeySource(ExoConstants.APP_DEVICE_KEY_SOURCE);
      return tclBean;
    }

    public static TclBean getDefaultTclPhone(String accessToken) {
      TclBean tclBean = new TclBean();
      tclBean.setSubSysId(Constant.getSystemId());
      tclBean.setClientId(SystemUtils.getDeviceId());
      tclBean.setAccessToken(accessToken);
      tclBean.setAppKeySource(ExoConstants.APP_MOBILE_KEY_SOURCE);
      return tclBean;
    }
  }
}
