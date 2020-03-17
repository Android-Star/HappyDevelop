package com.unisound.sdk.service.utils.unione.bean;

import java.io.Serializable;
import java.util.List;

public class DeviceControlInfoBean implements Serializable {
  private String tdid;
  private List<DevicesBean> devices;

  public String getTdid() {
    return tdid;
  }

  public void setTdid(String tdid) {
    this.tdid = tdid;
  }

  public List<DevicesBean> getDevices() {
    return devices;
  }

  public void setDevices(List<DevicesBean> devices) {
    this.devices = devices;
  }
}
