package com.unisound.sdk.service.utils.ota.bean;

import java.io.Serializable;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class OtaVersion implements Serializable {
  private static final String TAG = "OtaVersion";

  private String osType = "";
  private String appKey = "";
  private int version;
  private int targetVersion;
  private long timestamp;
  private String udid = "";
  private int channelCode;
  private String pkgName = "";
  private String hardwareType = "";
  private String gps = "";
  private String signature = "";
  private TreeMap<String, String> paramMap = new TreeMap<>();

  public OtaVersion() {
    setOsType("Android");
    setChannelCode(1);
    setTimestamp(System.currentTimeMillis() / 1000);
  }

  public String getOsType() {
    return osType;
  }

  public void setOsType(String osType) {
    this.osType = osType;
    paramMap.put("osType", osType);
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
    paramMap.put("appKey", appKey);
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
    paramMap.put("version", String.valueOf(version));
  }

  public int getTargetVersion() {
    return targetVersion;
  }

  public void setTargetVersion(int targetVersion) {
    this.targetVersion = targetVersion;
    paramMap.put("targetVersion", String.valueOf(targetVersion));
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    paramMap.put("timestamp", String.valueOf(timestamp));
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    this.udid = udid;
    paramMap.put("udid", String.valueOf(udid));
  }

  public int getChannelCode() {
    return channelCode;
  }

  public void setChannelCode(int channelCode) {
    this.channelCode = channelCode;
    paramMap.put("channelCode", String.valueOf(channelCode));
  }

  public String getPkgName() {
    return pkgName;
  }

  public void setPkgName(String pkgName) {
    this.pkgName = pkgName;
    paramMap.put("pkgName", String.valueOf(pkgName));
  }

  public String getHardwareType() {
    return hardwareType;
  }

  public void setHardwareType(String hardwareType) {
    this.hardwareType = hardwareType;
    paramMap.put("hardwareType", String.valueOf(hardwareType));
  }

  public String getGps() {
    return gps;
  }

  public void setGps(String gps) {
    this.gps = gps;
    paramMap.put("gps", String.valueOf(gps));
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
    paramMap.put("signature", String.valueOf(signature));
  }

  protected String buildSignature() {
    StringBuilder sb = new StringBuilder();
    List<String> paramList = new ArrayList<>();
    for (String key : paramMap.keySet()) {
      paramList.add(paramMap.get(key));
    }
    Collections.sort(paramList);
    for (String value : paramList) {
      sb.append(value);
    }
    return getSHA1Digest(sb.toString());
  }

  public String getSHA1Digest(String data) {
    String digest = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] bytes = md.digest(data.getBytes("UTF-8"));
      digest = byte2hex(bytes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return digest;
  }

  private static String byte2hex(byte[] bytes) {
    StringBuilder sign = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() == 1) {
        sign.append("0");
      }
      sign.append(hex.toUpperCase());
    }
    return sign.toString();
  }

  public String getHttpParam() {
    setSignature(buildSignature());
    StringBuilder builder = new StringBuilder();
    try {
      for (String key : paramMap.keySet()) {
        if (builder.length() == 0) {
          builder.append(key + "=").append(URLEncoder.encode(paramMap.get(key), "UTF-8"));
        } else {
          builder.append("&" + key + "=").append(URLEncoder.encode(paramMap.get(key), "UTF-8"));
        }
      }
      return builder.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
}
