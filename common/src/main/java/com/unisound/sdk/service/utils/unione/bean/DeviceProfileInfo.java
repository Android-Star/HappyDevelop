package com.unisound.sdk.service.utils.unione.bean;

import java.io.Serializable;
import java.util.List;

public class DeviceProfileInfo implements Serializable {

  private String udid;
  private String pUdid;
  private String phoneNumber;
  private String deviceType;
  private String deviceModel;
  private String category;
  private String aiChip;
  private boolean isActive;
  private List<String> capabilites;
  private DeviceGeneralInfo deviceGeneralInfo;
  private String appKey;

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getDeviceModel() {
    return deviceModel;
  }

  public void setDeviceModel(String deviceModel) {
    this.deviceModel = deviceModel;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getAiChip() {
    return aiChip;
  }

  public void setAiChip(String aiChip) {
    this.aiChip = aiChip;
  }

  public List<String> getCapabilites() {
    return capabilites;
  }

  public void setCapabilites(List<String> capabilites) {
    this.capabilites = capabilites;
  }

  public DeviceGeneralInfo getDeviceGeneralInfo() {
    return deviceGeneralInfo;
  }

  public void setDeviceGeneralInfo(DeviceGeneralInfo deviceGeneralInfo) {
    this.deviceGeneralInfo = deviceGeneralInfo;
  }

  public String getpUdid() {
    return pUdid;
  }

  public void setpUdid(String pUdid) {
    this.pUdid = pUdid;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    this.isActive = active;
  }
}
