package com.unisound.sdk.service.utils.mqtt;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

public class MqttSignUtils {

  private MqttSignUtils() {

  }

  public static String buildSignature(List<String> params) {
    if (params == null || params.isEmpty()) {
      return "";
    }
    Collections.sort(params);
    StringBuilder sb = new StringBuilder();
    for (String param : params) {
      sb.append(param == null ? "" : param);
    }
    return getSHA1Digest(sb.toString());
  }

  public static String getSHA1Digest(String data) {
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

  public static long getCurrentUnixTimestamp() {
    return System.currentTimeMillis() / 1000;
  }
}