package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class ImageResponse implements Serializable {

  /**
   * md5 : string
   * picUrl : string
   */

  private String md5;
  private String picUrl;

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }
}
