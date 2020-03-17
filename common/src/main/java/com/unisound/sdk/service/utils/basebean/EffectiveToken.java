package com.unisound.sdk.service.utils.basebean;

import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.constants.Constant;
import java.io.Serializable;

public class EffectiveToken implements Serializable {
  private String token;
  private String clientId = SystemUtils.getDeviceId();
  private int subSysId = Constant.getSystemId();

  public EffectiveToken() {
  }

  public EffectiveToken(String token) {
    this.token = token;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public int getSubSysId() {
    return subSysId;
  }

  public void setSubSysId(int subSysId) {
    this.subSysId = subSysId;
  }
}
