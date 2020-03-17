package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class GetApkVersionResponse implements Serializable {

  /**
   * apkUrl : string
   * md5 : string
   * versionCode : string
   * versionName : string
   */

  private String apkUrl;
  private String md5;
  private String versionCode;
  private String versionName;

  public String getApkUrl() {
    return apkUrl;
  }

  public void setApkUrl(String apkUrl) {
    this.apkUrl = apkUrl;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public String getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(String versionCode) {
    this.versionCode = versionCode;
  }

  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }
}
