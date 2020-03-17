package com.unisound.sdk.service.utils.mqtt.bean;

import android.text.TextUtils;
import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.mqtt.MqttSignUtils;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RegisterParam implements Serializable {
  public static final String ENCODING_UTF8 = "UTF-8";
  public static final int SUBSYSTEM_ID = Constant.getSystemId();
  public static final String DATA_VERSION = "v1";
  private String appKey = "";
  private String appSecret = "";
  private String tcDeviceId = "";
  private String udid = "";
  private String token = "";
  private String signature = "";
  private int appOsType = 0;
  private long timestamp = MqttSignUtils.getCurrentUnixTimestamp();
  private String extras = "extras params";

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getSubsystemId() {
    try {
      return URLEncoder.encode(SUBSYSTEM_ID + "", ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getAppOsType() {
    try {
      return URLEncoder.encode(appOsType + "", ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setAppOsType(int appOsType) {
    this.appOsType = appOsType;
  }

  public String getDataVersion() {
    try {
      return URLEncoder.encode(DATA_VERSION, ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getAppKey() {
    try {
      return URLEncoder.encode(appKey, ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getAppSecret() {
    try {
      return URLEncoder.encode(appSecret, ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public String getTcDeviceId() {
    try {
      return URLEncoder.encode(tcDeviceId, ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setTcDeviceId(String tcDeviceId) {
    this.tcDeviceId = tcDeviceId;
  }

  public String getUdid() {
    try {
      return URLEncoder.encode(udid, ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setUdid(String udid) {
    this.udid = udid;
  }

  public String getToken() {
    if (TextUtils.isEmpty(token)) {
      return "";
    }
    try {
      return URLEncoder.encode(token, ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public String getOriginalToken() {
    if (TextUtils.isEmpty(token)) {
      return "";
    }
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getExtras() {
    try {
      return URLEncoder.encode(extras, ENCODING_UTF8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setExtras(String extras) {
    this.extras = extras;
  }

  public String getSignature() {
    List<String> params = new ArrayList<String>();
    params.add(getAppKey());
    params.add(getAppSecret());
    params.add(getUdid());
    if (SUBSYSTEM_ID > 0) {
      params.add(SUBSYSTEM_ID + "");
    }
    params.add(getToken());
    params.add(getTcDeviceId());
    params.add(getDataVersion());
    if (timestamp > 0) {
      params.add(timestamp + "");
    }
    if (appOsType > 0) {
      params.add(appOsType + "");
    }
    params.add(getExtras());
    LogMgr.d("MqttRegister", "params:" + params);
    signature = MqttSignUtils.buildSignature(params);
    return signature;
  }

  public String formatParam() {
    StringBuffer urlParams = new StringBuffer();
    urlParams.append("appKey=")
        .append(getAppKey())
        .append("&udid=")
        .append(getUdid())
        .append("&subsystemId=")
        .append(SUBSYSTEM_ID)
        .append("&token=")
        .append(getToken())
        .append("&tcDeviceId=")
        .append(getTcDeviceId())
        .append("&timestamp=")
        .append(timestamp)
        .append("&dataVersion=")
        .append(getDataVersion())
        .append("&signature=")
        .append(getSignature())
        .append("&appOsType=")
        .append(appOsType)
        .append("&extras=")
        .append(getExtras());
    return urlParams.toString();
  }

  @Override public String toString() {
    return JsonTool.toJson(this);
  }
}
