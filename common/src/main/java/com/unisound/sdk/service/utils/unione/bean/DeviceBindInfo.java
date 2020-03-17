package com.unisound.sdk.service.utils.unione.bean;

import com.unisound.sdk.service.utils.basebean.EffectiveToken;
import java.io.Serializable;

public class DeviceBindInfo implements Serializable {
  private String version;
  private String command;

  private EffectiveToken tcl;

  private DeviceProfileInfo deviceProfile;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public EffectiveToken getTcl() {
    return tcl;
  }

  public void setTcl(EffectiveToken tcl) {
    this.tcl = tcl;
  }

  public DeviceProfileInfo getDeviceProfile() {
    return deviceProfile;
  }

  public void setDeviceProfile(DeviceProfileInfo deviceProfile) {
    this.deviceProfile = deviceProfile;
  }
}
