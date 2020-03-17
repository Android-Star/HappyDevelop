package com.unisound.sdk.service.utils.account.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PhoneCodeHeader extends RequestHeader {

  private String userCell;

  private String phoneCode;

  private String password;

  public PhoneCodeHeader() {
    super();
  }

  public void setUserCell(String userCell) {
    try {
      builder.append("&userCell=").append(URLEncoder.encode(userCell, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    list.add(userCell);
    this.userCell = userCell;
  }

  public void setPhoneCode(String phoneCode) {
    try {
      builder.append("&phoneCode=").append(URLEncoder.encode(phoneCode, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    list.add(phoneCode);
    this.phoneCode = phoneCode;
  }

  public void setPassword(String password) {
    try {
      password = getMD5Digest(password);
      builder.append("&password=").append(URLEncoder.encode(password, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    list.add(password);
    this.password = password;
  }

  @Override public String generateSignature() {
    return buildSignature(list);
  }

  @Override public String formatParam() {
    return builder.toString();
  }
}
