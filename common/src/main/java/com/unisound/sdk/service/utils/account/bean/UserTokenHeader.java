package com.unisound.sdk.service.utils.account.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UserTokenHeader extends RequestHeader {

  private String flushToken;
  private String accessToken;

  public void setFlushToken(String flushToken) {
    try {
      builder.append("&flushToken=").append(URLEncoder.encode(flushToken, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    list.add(flushToken);
    this.flushToken = flushToken;
  }

  public void setAccessToken(String accessToken) {
    try {
      builder.append("&accessToken=").append(URLEncoder.encode(accessToken, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    list.add(accessToken);
    this.accessToken = accessToken;
  }

  @Override public String generateSignature() {
    return buildSignature(list);
  }

  @Override public String formatParam() {
    return builder.toString();
  }
}
