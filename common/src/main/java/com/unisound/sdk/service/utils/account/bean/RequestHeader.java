package com.unisound.sdk.service.utils.account.bean;

import android.text.TextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.constants.Constant;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RequestHeader implements Serializable {
  private static final String TAG = "RequestHeader";
  private int subsystemId;
  protected String clientId;
  private long timestamp;
  private String signature;
  protected List<String> list = new ArrayList<>();
  protected StringBuilder builder = new StringBuilder();

  public RequestHeader() {
    subsystemId = Constant.getSystemId();
    timestamp = getCurrentUnixTimestamp();
    clientId = SystemUtils.getDeviceId();
    list.add(String.valueOf(subsystemId));
    list.add(String.valueOf(timestamp));
    list.add(clientId);
    try {
      builder.append("subsystemId=").append(subsystemId);
      builder.append("&timestamp=").append(URLEncoder.encode(String.valueOf(timestamp), "UTF-8"));
      builder.append("&clientId=").append(URLEncoder.encode(clientId, "UTF-8"));
    } catch (Exception e) {
      LogMgr.e(TAG, e.toString());
    }
  }

  public RequestHeader(String clientId) {
    subsystemId = Constant.getSystemId();
    timestamp = getCurrentUnixTimestamp();
    this.clientId = clientId;
    list.add(String.valueOf(subsystemId));
    list.add(String.valueOf(timestamp));
    list.add(clientId);
    try {
      builder.append("subsystemId=").append(subsystemId);
      builder.append("&timestamp=").append(URLEncoder.encode(String.valueOf(timestamp), "UTF-8"));
      builder.append("&clientId=").append(URLEncoder.encode(clientId, "UTF-8"));
    } catch (Exception e) {
      LogMgr.e(TAG, e.toString());
    }
  }

  public void setSignature(String signature) {
    try {
      builder.append("&signature=").append(URLEncoder.encode(signature, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    this.signature = signature;
  }

  public abstract String generateSignature();

  public abstract String formatParam();

  /**
   * 对参数列表构造响应签名
   */
  protected String buildSignature(List<String> params) {
    if (params == null || params.isEmpty()) {
      return "";
    }
    List<String> paramsList = new ArrayList<>();
    for (String item : params) {
      if (!TextUtils.isEmpty(item)) {
        paramsList.add(item);
      }
    }
    Collections.sort(paramsList);
    StringBuilder sb = new StringBuilder();
    for (String param : paramsList) {
      sb.append(param == null ? "" : param);
    }
    return getSHA1Digest(sb.toString());
  }

  /**
   * 将字符串进行SHA1获取摘要，摘要为十六进制字符串
   */
  public String getSHA1Digest(String data) {
    String digest = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] bytes = md.digest(data.getBytes("UTF-8"));
      digest = byte2hex(bytes);
    } catch (Exception e) {
      LogMgr.e(TAG, e.getMessage());
    }

    return digest;
  }

  public String getMD5Digest(String data) {
    String digest = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] bytes = md.digest(data.getBytes("UTF-8"));
      digest = byte2hex(bytes);
    } catch (Exception e) {
      LogMgr.e(TAG, e.getMessage());
    }
    return digest;
  }

  /**
   * 二进制转十六进制字符串
   */
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

  /**
   * 获取当前UNIX的时间戳
   */
  public static long getCurrentUnixTimestamp() {
    return System.currentTimeMillis() / 1000;
  }
}
