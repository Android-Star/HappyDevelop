package com.unisound.sdk.service.utils.account.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UserAccountHeader extends RequestHeader {

  private String account;

  private String accessToken;

  private String password;

  public UserAccountHeader() {
    super();
  }

  public UserAccountHeader(String clientId) {
    super(clientId);
  }

  public String getAccount() {
    return account;
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

  public void setAccount(String account) {
    try {
      builder.append("&account=").append(URLEncoder.encode(account, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    list.add(account);
    this.account = account;
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

  public String getClientId() {
    return clientId;
  }
}
