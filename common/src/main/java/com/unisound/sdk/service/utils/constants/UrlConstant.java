package com.unisound.sdk.service.utils.constants;

import android.os.Environment;
import com.unisound.sdk.service.utils.FileUtils;
import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.File;

public class UrlConstant {
  private static final String TAG = "UrlConstant";
  private static final String CONFIG_FILE_PATH =
      Environment.getExternalStorageDirectory() + File.separator + "unisound" + File.separator
          + "url.ini";

  private UrlConstant() {
    setOuter(isOuter);
  }

  private static UrlConstant build() {
    UrlConstant urlConstant = new UrlConstant();
    LogMgr.d(TAG, "file path:" + CONFIG_FILE_PATH);
    File file = new File(CONFIG_FILE_PATH);
    if (file.isFile() && file.exists()) {
      LogMgr.d(TAG, "config file exist");
      String value = FileUtils.readTxtFile(CONFIG_FILE_PATH);
      UrlConstant urlConstant2 = JsonTool.fromJson(value, UrlConstant.class);
      LogMgr.d(TAG, "url2:" + JsonTool.toJson(urlConstant2));
      if (urlConstant2 != null) {
        urlConstant = urlConstant2;
      }
    }
    LogMgr.d(TAG, "url:" + JsonTool.toJson(urlConstant));
    return urlConstant;
  }

  public static UrlConstant urlConstant = build();

  public static UrlConstant getInstance() {
    return urlConstant;
  }

  private boolean isOuter = true;

  private String userCenterUrl;
  private String unioneAppServerUrl;
  private String msgCenterUrl;
  private String otaServerUrl;
  private String chipOtaServerUrl;
  private String karAppServerUrl;
  private String deviceCenterUrl;
  private String cscUrl;
  private String suggestUrl;
  private String rongAppKey;
  private String casrUrl;
  private String appUrl;

  public String getSuggestUrl() {
    return suggestUrl;
  }

  public String getCscUrl() {
    return cscUrl;
  }

  public String getAppServerUrl() {
    return unioneAppServerUrl;
  }

  public String getMsgCenterUrl() {
    return msgCenterUrl;
  }

  public String getOtaServerUrl() {
    return otaServerUrl;
  }

  public String getChipOtaServerUrl() {
    return chipOtaServerUrl;
  }

  public String getUserCenterUrl() {
    return userCenterUrl;
  }

  public String getKarAppServerUrl() {
    return karAppServerUrl;
  }

  public String getDeviceCenterUrl() {
    return deviceCenterUrl;
  }

  public String getRongAppKey() {
    return rongAppKey;
  }

  public String getCasrUrl() {
    return casrUrl;
  }

  public String getAppUrl() {
    return appUrl;
  }

  public void setAppUrl(String appUrl) {
    this.appUrl = appUrl;
  }

  public boolean isOuter() {
    return isOuter;
  }

  public void setOuter(boolean isOut) {
    this.isOuter = isOut;
    if (isOut) {
      userCenterUrl = "http://uc.hivoice.cn/rest/v2/";
      unioneAppServerUrl = "http://unione.hivoice.cn/";
      msgCenterUrl = "http://msg.hivoice.cn/rest/v1/client/";
      otaServerUrl = "http://otav2.hivoice.cn/rest/v1/data/check_update";
      chipOtaServerUrl = "http://otav2.hivoice.cn/rest/v1/data/check_update_chip";
      karAppServerUrl = "https://unitoy.hivoice.cn/";
      deviceCenterUrl = "http://dc.hivoice.cn/";
      cscUrl = "http://kar-csc.hivoice.cn/";
      suggestUrl = "http://feedback.hivoice.cn/";
      rongAppKey = "4z3hlwrv4g8qt";
      casrUrl = "http://v2.hivoice.cn:8081/casr/upload";
      appUrl = "http://sphone.yunmaolink.com:9000";
    } else {
      userCenterUrl = "http://10.30.10.32:10080/rest/v2/";
      unioneAppServerUrl = "http://10.20.11.18:19999/";
      msgCenterUrl = "http://10.20.222.60:8080/rest/v1/client/";
      otaServerUrl = "http://10.30.11.15:8080/rest/v1/data/check_update";
      chipOtaServerUrl = "http://10.30.11.15:8080/rest/v1/data/check_update_chip";
      karAppServerUrl = "http://10.20.222.171:9001/";
      deviceCenterUrl = "http://10.30.10.21:8180/";
      cscUrl = "http://10.20.11.17:8180/";
      suggestUrl = "http://10.20.222.137:9099/";
      rongAppKey = "pwe86ga5p4ky6";
      casrUrl = "http://10.10.13.12:8082/casr/pu";
      appUrl = "http://114.67.123.30:9000/";
    }
  }
}
