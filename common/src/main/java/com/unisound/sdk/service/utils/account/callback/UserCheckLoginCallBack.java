package com.unisound.sdk.service.utils.account.callback;

public interface UserCheckLoginCallBack {
  void checkLoginSuccess(String accessToken);
  void checkLoginFail(String ret);
}
