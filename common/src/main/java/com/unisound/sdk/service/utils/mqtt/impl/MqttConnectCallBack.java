package com.unisound.sdk.service.utils.mqtt.impl;

public interface MqttConnectCallBack {
  void onConnectSuccess();

  void onConnectFail();
}
