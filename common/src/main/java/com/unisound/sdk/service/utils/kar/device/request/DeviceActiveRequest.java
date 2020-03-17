package com.unisound.sdk.service.utils.kar.device.request;

import com.unisound.sdk.service.utils.ExoConstants;
import com.unisound.sdk.service.utils.SystemUtils;
import java.io.Serializable;

public class DeviceActiveRequest implements Serializable {
  //必须传的参数
  private String appKey;        //应用KEY
  private String udid;          //设备唯一标识（由deviceSn加密生成）
  private long timestamp;       //访问时间戳,Unix时间戳
  //非必须传的参数
  private String deviceSn;      //客户认可的设备唯一标识（由客户端APP设置）
  private String appVersion;    //应用版本号
  private String pkgName;       //包名
  private String imei;          //设备的IMEI
  private String macAddress;    //设备MAC地址
  private String wifiSsid;      //WIFI的名称
  private String telecomOperator; //运营商
  private String bssId;           //当前的接入点MAC地址
  private String productName;     //设备名称
  private String productModel;    //设备型号名称
  private String productMfr;      //制造商名称
  private String productOs;       //操作系统
  private String productOsVersion; //操作系统版本号
  private String hardwareSn;      //厂商硬件序列号
  private String memo;            //备注

  public DeviceActiveRequest() {
    //必须传的参数
    appKey = ExoConstants.APP_KEY;
    udid = SystemUtils.getDeviceId();
    timestamp = SystemUtils.getCurrentUnixTimestamp();
    //非必须传的参数
    deviceSn = "";
    memo = "";
    appVersion = SystemUtils.getVersionString();
    bssId = SystemUtils.getNetMac();
    hardwareSn = SystemUtils.getHardwareSn();
    imei = SystemUtils.getDeviceId();
    macAddress = SystemUtils.getMac();
    pkgName = SystemUtils.getPackName();
    productMfr = SystemUtils.getProductMfr();
    productModel = SystemUtils.getProductModel();
    productName = SystemUtils.getProductName();
    productOs = SystemUtils.getProductOs();
    productOsVersion = SystemUtils.getProductOsVersion();
    telecomOperator = SystemUtils.getOperator();
    wifiSsid = SystemUtils.getWifiName();
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getDeviceSn() {
    return deviceSn;
  }

  public void setDeviceSn(String deviceSn) {
    this.deviceSn = deviceSn;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getPkgName() {
    return pkgName;
  }

  public void setPkgName(String pkgName) {
    this.pkgName = pkgName;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public String getWifiSsid() {
    return wifiSsid;
  }

  public void setWifiSsid(String wifiSsid) {
    this.wifiSsid = wifiSsid;
  }

  public String getTelecomOperator() {
    return telecomOperator;
  }

  public void setTelecomOperator(String telecomOperator) {
    this.telecomOperator = telecomOperator;
  }

  public String getBssId() {
    return bssId;
  }

  public void setBssId(String bssId) {
    this.bssId = bssId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductModel() {
    return productModel;
  }

  public void setProductModel(String productModel) {
    this.productModel = productModel;
  }

  public String getProductMfr() {
    return productMfr;
  }

  public void setProductMfr(String productMfr) {
    this.productMfr = productMfr;
  }

  public String getProductOs() {
    return productOs;
  }

  public void setProductOs(String productOs) {
    this.productOs = productOs;
  }

  public String getProductOsVersion() {
    return productOsVersion;
  }

  public void setProductOsVersion(String productOsVersion) {
    this.productOsVersion = productOsVersion;
  }

  public String getHardwareSn() {
    return hardwareSn;
  }

  public void setHardwareSn(String hardwareSn) {
    this.hardwareSn = hardwareSn;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
