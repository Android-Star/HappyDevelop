package com.unisound.sdk.service.utils.receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.ExoConstants;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.callback.BlueDeviceConnectCallback;
import com.unisound.sdk.service.utils.callback.NetErrorPassWordCallBack;
import com.unisound.sdk.service.utils.callback.TimeChangeCallback;
import com.unisound.sdk.service.utils.callback.UpdateBluetoothCallBack;
import com.unisound.sdk.service.utils.callback.UpdateScanResultsCallBack;
import com.unisound.sdk.service.utils.callback.UpdateStatusBarCallBack;
import java.util.ArrayList;
import java.util.List;

public class StatusBarReceiver extends BroadcastReceiver {

  private static final String TAG = "StatusBarReceiver";
  private static List<UpdateStatusBarCallBack> updateStatusBarCallBackList = new ArrayList<>();
  private static UpdateScanResultsCallBack updateScanResultsCallBack;
  private static BlueDeviceConnectCallback blueDeviceConnectCallback;
  public static int batteryLevel;
  public static int batteryScale;
  public static int batteryStatus;
  public static int plugType;
  public static int bluetooth = BluetoothAdapter.STATE_OFF;
  public static int oldBluetooth = BluetoothAdapter.STATE_OFF;
  public static int wifiStrength = 0;
  public static boolean wifiConnected = false;
  private static boolean inVideoCall = false;

  private static NetErrorPassWordCallBack netErrorPassWordCallBack;
  private static UpdateBluetoothCallBack updateBluetoothCallBack;
  private static TimeChangeCallback timeChangeCallback;

  public static boolean isBtDeviceConnected() {
    return btDeviceConnected && bluetooth == BluetoothAdapter.STATE_ON;
  }

  private static boolean btDeviceConnected = false;

  public static void setNetErrorPassWordCallBack(NetErrorPassWordCallBack callBack) {
    netErrorPassWordCallBack = callBack;
  }

  public static void setTimeChangeCallBack(TimeChangeCallback callBack) {
    timeChangeCallback = callBack;
  }

  public static boolean isInVideoCall() {
    return inVideoCall;
  }

  public static void setUpdateBluetoothCallBack(UpdateBluetoothCallBack callBack) {
    updateBluetoothCallBack = callBack;
  }

  public static void setBlueDeviceConnectCallback(BlueDeviceConnectCallback callback) {
    blueDeviceConnectCallback = callback;
  }

  public static void init() {
    bluetooth = BluetoothAdapter.getDefaultAdapter().getState();
    IntentFilter filter = new IntentFilter();
    filter.addAction(Intent.ACTION_BATTERY_CHANGED);
    filter.addAction(Intent.ACTION_BATTERY_OKAY);
    filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
    filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
    filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
    filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
    filter.addAction(Intent.ACTION_TIME_TICK);
    filter.addAction(Intent.ACTION_TIME_CHANGED);
    filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
    filter.addAction(ExoConstants.VIDEO_CALL_SHOW_OR_DISMISS);
    ContextUtils.getContext().registerReceiver(STATUS_RECEIVER, filter);
  }

  @Override public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    if (Intent.ACTION_POWER_CONNECTED.equals(action) || Intent.ACTION_POWER_DISCONNECTED.equals(
        action)) {
      LogMgr.d(TAG, "batteryStatus2:" + batteryStatus + ",action:" + action);
    }
  }

  public static void addUpdateStatusBarCallBack(UpdateStatusBarCallBack updateStatusBarCallBack) {
    if (!updateStatusBarCallBackList.contains(updateStatusBarCallBack)) {
      updateStatusBarCallBackList.add(updateStatusBarCallBack);
    }
  }

  public static void removeUpdateStatusBarCallBack(
      UpdateStatusBarCallBack updateStatusBarCallBack) {
    if (updateStatusBarCallBackList.contains(updateStatusBarCallBack)) {
      updateStatusBarCallBackList.remove(updateStatusBarCallBack);
    }
  }

  public static void setUpdateScanResultsCallBack(UpdateScanResultsCallBack callBack) {
    updateScanResultsCallBack = callBack;
  }

  public static final BroadcastReceiver STATUS_RECEIVER = new BroadcastReceiver() {
    @Override public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      if (Intent.ACTION_TIME_TICK.equals(action) || Intent.ACTION_TIME_CHANGED.equals(action)) {
        if (timeChangeCallback != null) {
          timeChangeCallback.timeChangeState();
        }
      }
      if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
        StatusBarReceiver.batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        StatusBarReceiver.batteryScale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        StatusBarReceiver.batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        StatusBarReceiver.plugType = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        LogMgr.d(TAG,
            "batteryStatus:" + batteryStatus + ",action:" + action + ",plugType:" + plugType);
      }
      if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {
        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
          wifiConnected = false;
        } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
          wifiConnected = true;
        }
      }
      if (WifiManager.RSSI_CHANGED_ACTION.equals(action)) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getBSSID() != null) {
          wifiStrength = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 4);
        }
      }
      if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
        LogMgr.d(TAG, "ACTION_ACL_CONNECTED");
        btDeviceConnected = true;
        if (blueDeviceConnectCallback != null) {
          BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
          blueDeviceConnectCallback.blueDeviceConnected(true, device);
        }
      }
      if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
        LogMgr.d(TAG, "ACTION_ACL_DISCONNECTED");
        btDeviceConnected = false;
        if (blueDeviceConnectCallback != null) {
          BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
          blueDeviceConnectCallback.blueDeviceConnected(false, device);
        }
      }
      if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
        bluetooth = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);
        LogMgr.d(TAG, "now blue:" + bluetooth + ",old blue:" + oldBluetooth);
        oldBluetooth = bluetooth;
        if (updateBluetoothCallBack != null) {
          if (bluetooth == BluetoothAdapter.STATE_ON) {
            updateBluetoothCallBack.updateBluetoothStatus(true);
          } else if (bluetooth == BluetoothAdapter.STATE_OFF) {
            updateBluetoothCallBack.updateBluetoothStatus(false);
          }
        }
        if (blueDeviceConnectCallback != null) {
          if (bluetooth != BluetoothAdapter.STATE_ON) {
            blueDeviceConnectCallback.blueDeviceConnected(false, null);
          }
        }
      }
      if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) {
        int state = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 0);
        LogMgr.d(TAG, "wifi error state:" + state);
        if (state == WifiManager.ERROR_AUTHENTICATING) {
          if (netErrorPassWordCallBack != null) {
            netErrorPassWordCallBack.netErrorPassWord();
          }
        }
      }
      if (ExoConstants.VIDEO_CALL_SHOW_OR_DISMISS.equals(action)) {
        inVideoCall = intent.getBooleanExtra(ExoConstants.VIDEO_CALL_SHOW_OR_DISMISS, false);
        LogMgr.d(TAG, "isInCall:" + inVideoCall);
      }
      if (updateStatusBarCallBackList != null && updateStatusBarCallBackList.size() > 0) {
        for (UpdateStatusBarCallBack updateStatusBarCallBack : updateStatusBarCallBackList) {
          updateStatusBarCallBack.updateStatusBar();
        }
      }
    }
  };
}
