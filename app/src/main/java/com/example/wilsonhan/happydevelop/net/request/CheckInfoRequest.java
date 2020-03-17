package com.example.wilsonhan.happydevelop.net.request;

import java.io.Serializable;

public class CheckInfoRequest implements Serializable {

  /**
   * active (integer, optional): 0:待激活、1:已激活 ,
   * apkUpgradeDate (string, optional): apk升级时间 ,
   * apkValidDate (string, optional): api有效时间 ,
   * apkVersion (string, optional): apk版本号 ,
   * deviceCode (string, optional): 设备码 ,
   * deviceTime (integer, optional): 设备系统时间：0已修复、1正常 ,
   * disable (integer, optional): 0:未禁用、1:已禁用 ,
   * disableDate (string, optional): 失效时间 ,
   * hardtwareVersion (string, optional): 硬件版本号 ,
   * internetSpeed (string, optional): 网速 ,
   * networkConnection (string, optional): 网络连接方式 ,
   * softwareVersion (string, optional): 软件版本号 ,
   * systemCache (string, optional): 系统缓存 ,
   * telLineStatus (integer, optional): 电话线路状态：0断开、1正常
   */

  private int active;
  private String apkUpgradeDate;
  private String apkValidDate;
  private String apkVersion;
  private String deviceCode;
  private int deviceTime;
  private int disable;
  private String disableDate;
  private String hardtwareVersion;
  private String internetSpeed;
  private String networkConnection;
  private String softwareVersion;
  private String systemCache;
  private int telLineStatus;

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public String getApkUpgradeDate() {
    return apkUpgradeDate;
  }

  public void setApkUpgradeDate(String apkUpgradeDate) {
    this.apkUpgradeDate = apkUpgradeDate;
  }

  public String getApkValidDate() {
    return apkValidDate;
  }

  public void setApkValidDate(String apkValidDate) {
    this.apkValidDate = apkValidDate;
  }

  public String getApkVersion() {
    return apkVersion;
  }

  public void setApkVersion(String apkVersion) {
    this.apkVersion = apkVersion;
  }

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public int getDeviceTime() {
    return deviceTime;
  }

  public void setDeviceTime(int deviceTime) {
    this.deviceTime = deviceTime;
  }

  public int getDisable() {
    return disable;
  }

  public void setDisable(int disable) {
    this.disable = disable;
  }

  public String getDisableDate() {
    return disableDate;
  }

  public void setDisableDate(String disableDate) {
    this.disableDate = disableDate;
  }

  public String getHardtwareVersion() {
    return hardtwareVersion;
  }

  public void setHardtwareVersion(String hardtwareVersion) {
    this.hardtwareVersion = hardtwareVersion;
  }

  public String getInternetSpeed() {
    return internetSpeed;
  }

  public void setInternetSpeed(String internetSpeed) {
    this.internetSpeed = internetSpeed;
  }

  public String getNetworkConnection() {
    return networkConnection;
  }

  public void setNetworkConnection(String networkConnection) {
    this.networkConnection = networkConnection;
  }

  public String getSoftwareVersion() {
    return softwareVersion;
  }

  public void setSoftwareVersion(String softwareVersion) {
    this.softwareVersion = softwareVersion;
  }

  public String getSystemCache() {
    return systemCache;
  }

  public void setSystemCache(String systemCache) {
    this.systemCache = systemCache;
  }

  public int getTelLineStatus() {
    return telLineStatus;
  }

  public void setTelLineStatus(int telLineStatus) {
    this.telLineStatus = telLineStatus;
  }
}
