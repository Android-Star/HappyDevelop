package com.unisound.sdk.service.utils.callback;

import android.bluetooth.BluetoothDevice;

public interface BlueDeviceConnectCallback {
  void blueDeviceConnected(boolean connected, BluetoothDevice device);
}
