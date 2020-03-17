package com.unisound.sdk.service.utils.kar.device;

import com.unisound.sdk.service.utils.ExoConstants;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.kar.device.request.DeviceActiveRequest;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DeviceParamsUtils {
  private static final String TAG = "DeviceParamsUtils";
  protected static StringBuilder builder;

  public DeviceParamsUtils() {

  }

  public static String formatParam(DeviceActiveRequest activeBean) {
    builder = new StringBuilder();
    try {
      builder.append("appKey=").append(activeBean.getAppKey());
      builder.append("&deviceSn=").append(URLEncoder.encode(activeBean.getDeviceSn(), "UTF-8"));
      builder.append("&udid=").append(URLEncoder.encode(activeBean.getUdid(), "UTF-8"));
      builder.append("&timestamp=")
          .append(URLEncoder.encode(String.valueOf(activeBean.getTimestamp()), "UTF-8"));
      builder.append("&appVersion=").append(URLEncoder.encode(activeBean.getAppVersion(), "UTF-8"));
      builder.append("&pkgName=").append(URLEncoder.encode(activeBean.getPkgName(), "UTF-8"));
      builder.append("&imei=").append(URLEncoder.encode(activeBean.getImei(), "UTF-8"));
      builder.append("&macAddress=").append(URLEncoder.encode(activeBean.getMacAddress(), "UTF-8"));
      builder.append("&wifiSsid=").append(URLEncoder.encode(activeBean.getWifiSsid(), "UTF-8"));
      builder.append("&telecomOperator=")
          .append(URLEncoder.encode(activeBean.getTelecomOperator(), "UTF-8"));
      builder.append("&bssId=").append(URLEncoder.encode(activeBean.getBssId(), "UTF-8"));
      builder.append("&productName=")
          .append(URLEncoder.encode(activeBean.getProductName(), "UTF-8"));
      builder.append("&productModel=")
          .append(URLEncoder.encode(activeBean.getProductModel(), "UTF-8"));
      builder.append("&productMfr=").append(URLEncoder.encode(activeBean.getProductMfr(), "UTF-8"));
      builder.append("&productOs=").append(URLEncoder.encode(activeBean.getProductOs(), "UTF-8"));
      builder.append("&productOsVersion=")
          .append(URLEncoder.encode(activeBean.getProductOsVersion(), "UTF-8"));
      builder.append("&hardwareSn=").append(URLEncoder.encode(activeBean.getHardwareSn(), "UTF-8"));
      builder.append("&memo=").append(URLEncoder.encode(activeBean.getMemo(), "UTF-8"));
      builder.append("&signature=")
          .append(URLEncoder.encode(buildActivateSignature(activeBean), "UTF-8"));
    } catch (Exception e) {
      LogMgr.e(TAG, e.toString());
    }
    return builder.toString();
  }

  private static String buildActivateSignature(DeviceActiveRequest reqVO) {
    List<String> params = new ArrayList<>();
    params.add(reqVO.getUdid());
    params.add(reqVO.getDeviceSn());
    params.add(reqVO.getAppKey());
    params.add(String.valueOf(reqVO.getTimestamp()));
    params.add(reqVO.getPkgName());
    params.add(reqVO.getAppVersion());
    params.add(reqVO.getImei());
    params.add(reqVO.getMacAddress());
    params.add(reqVO.getWifiSsid());
    params.add(reqVO.getTelecomOperator());
    params.add(reqVO.getBssId());
    params.add(reqVO.getProductName());
    params.add(reqVO.getProductModel());
    params.add(reqVO.getProductMfr());
    params.add(reqVO.getProductOs());
    params.add(reqVO.getProductOsVersion());
    params.add(reqVO.getHardwareSn());
    params.add(reqVO.getMemo());
    params.add(ExoConstants.APP_SECRET);

    String signature = buildSignature(params);
    LogMgr.d(TAG, signature);
    return signature;
  }

  /**
   * 对参数列表构造响应签名
   */
  public static String buildSignature(List<String> params) {
    if (params == null || params.isEmpty()) {
      return "";
    }

    // 升序排序参数值
    Collections.sort(params);

    StringBuilder sb = new StringBuilder();
    for (String param : params) {
      sb.append(param == null ? "" : param);
    }

    return getSHA1Digest(sb.toString());
  }

  /**
   * 将字符串进行SHA1获取摘要，摘要为十六进制字符串
   *
   * @throws Exception
   */
  public static String getSHA1Digest(String data) {
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
}
