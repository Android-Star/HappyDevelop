package com.unisound.sdk.service.utils.ota.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

public class OtaChipVersion implements Serializable {
  private String appKey = "";
  private int version = 1;
  private String udid = "";
  private String signature = "";
  private int nonce;
  private StringBuilder builder = new StringBuilder();

  public OtaChipVersion() {
    setNonce(Math.abs(new Random().nextInt()));
  }

  public int getNonce() {
    return nonce;
  }

  public void setNonce(int nonce) {
    builder.append("nonce=").append(nonce);
    this.nonce = nonce;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    try {
      builder.append("&appKey=").append(URLEncoder.encode(appKey, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    this.appKey = appKey;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    builder.append("&version=").append(version);
    this.version = version;
  }

  public String getUdid() {
    return udid;
  }

  public void setUdid(String udid) {
    try {
      builder.append("&udid=").append(URLEncoder.encode(udid, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    this.udid = udid;
  }

  public void setSignature(String signature) {
    try {
      builder.append("&signature=").append(URLEncoder.encode(signature, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    this.signature = signature;
  }

  public String formatParam() {
    return builder.toString();
  }

  public String generateSignature() {
    StringBuilder source = new StringBuilder();
    source.append(nonce).append(version).append(appKey).append(udid);
    StringBuilder newSig = new StringBuilder();
    char[] charArray = String.valueOf(nonce).toCharArray();
    for (char c : charArray) {
      int index = Integer.valueOf(String.valueOf(c));
      if (index >= source.length()) {
        continue;
      }
      newSig.append(source.charAt(index));
    }
    return newSig.toString();
  }
}
