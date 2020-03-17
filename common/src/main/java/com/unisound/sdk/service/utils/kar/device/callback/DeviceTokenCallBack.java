package com.unisound.sdk.service.utils.kar.device.callback;

public interface DeviceTokenCallBack {
  void getDeviceTokenSuccess(String token);

  void getDeviceTokenFail(String error);
}
