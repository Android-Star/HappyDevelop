package com.unisound.sdk.service.utils.unione.bean;

import java.io.Serializable;
import java.util.List;

public class DeviceGeneralInfo implements Serializable {
  private String totalMemory;
  private String availableMemory;
  private String cpuSize;
  private boolean isRetainScreen;
  private boolean isRetainCamera;
  private boolean isSurpportGps;
  private boolean isRetainSpeaker;
  private boolean isSurpportBluetooth;
  private boolean isSurpportIndoorGps;
  private boolean isSurpportDirectionBySound;

  public String getTotalMemory() {
    return totalMemory;
  }

  public void setTotalMemory(String totalMemory) {
    this.totalMemory = totalMemory;
  }

  public String getAvailableMemory() {
    return availableMemory;
  }

  public void setAvailableMemory(String availableMemory) {
    this.availableMemory = availableMemory;
  }

  public String getCpuSize() {
    return cpuSize;
  }

  public void setCpuSize(String cpuSize) {
    this.cpuSize = cpuSize;
  }

  public boolean isRetainScreen() {
    return isRetainScreen;
  }

  public void setRetainScreen(boolean retainScreen) {
    isRetainScreen = retainScreen;
  }

  public boolean isRetainCamera() {
    return isRetainCamera;
  }

  public void setRetainCamera(boolean retainCamera) {
    isRetainCamera = retainCamera;
  }

  public boolean isSurpportGps() {
    return isSurpportGps;
  }

  public void setSurpportGps(boolean surpportGps) {
    isSurpportGps = surpportGps;
  }

  public boolean isRetainSpeaker() {
    return isRetainSpeaker;
  }

  public void setRetainSpeaker(boolean retainSpeaker) {
    isRetainSpeaker = retainSpeaker;
  }

  public boolean isSurpportBluetooth() {
    return isSurpportBluetooth;
  }

  public void setSurpportBluetooth(boolean surpportBluetooth) {
    isSurpportBluetooth = surpportBluetooth;
  }

  public boolean isSurpportIndoorGps() {
    return isSurpportIndoorGps;
  }

  public void setSurpportIndoorGps(boolean surpportIndoorGps) {
    isSurpportIndoorGps = surpportIndoorGps;
  }

  public boolean isSurpportDirectionBySound() {
    return isSurpportDirectionBySound;
  }

  public void setSurpportDirectionBySound(boolean surpportDirectionBySound) {
    isSurpportDirectionBySound = surpportDirectionBySound;
  }

  public List<String> getOtherSensor() {
    return otherSensor;
  }

  public void setOtherSensor(List<String> otherSensor) {
    this.otherSensor = otherSensor;
  }

  private List<String> otherSensor;
}
