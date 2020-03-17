package com.unisound.sdk.service.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.unisound.sdk.service.utils.callback.NetWorkChangeCallBack;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetUtils {

  private static final String TAG = "NetUtils";
  private static List<NetWorkChangeCallBack> netWorkChangeCallBackList = new ArrayList<>();
  private static WifiManager wifiManager = (WifiManager) ContextUtils.getContext()
      .getApplicationContext()
      .getSystemService(Context.WIFI_SERVICE);
  private static ConnectivityManager connectivityManager =
      (ConnectivityManager) ContextUtils.getContext()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
  private static boolean isAllLevelConnected = false;
  private static Handler mainHandler = new Handler(Looper.getMainLooper());
  private static ConnectivityManager.NetworkCallback networkCallback;

  private NetUtils() {

  }

  public static boolean isNetworkAvailable() {
    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
    if (info != null) {
      for (int i = 0; i < info.length; i++) {
        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
          LogMgr.d(TAG, "connect wifi:" + JsonTool.toJson(info[i]));
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isNetworkOnline() {
    Runtime runtime = Runtime.getRuntime();
    try {
      Process ipProcess = runtime.exec("ping -c 1 -w 2 www.baidu.com");
      int exitValue = ipProcess.waitFor();
      LogMgr.d("Avalible", "Process:" + exitValue);
      return (exitValue == 0);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean isAllLevelConnected() {
    return isAllLevelConnected;
  }

  public static String getWifiName() {
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    if (wifiInfo != null) {
      LogMgr.d(TAG, "wifi info:" + wifiInfo.toString());
      LogMgr.d(TAG, "ssid:" + wifiInfo.getSSID());
      return wifiInfo.getSSID().replaceAll("^\"|\"$", "");
    }
    return "";
  }

  public static String getWifiSsid() {
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    if (wifiInfo != null) {
      LogMgr.d(TAG, "wifi info:" + wifiInfo.toString());
      LogMgr.d(TAG, "ssid:" + wifiInfo.getSSID());
      return wifiInfo.getSSID();
    }
    return "";
  }

  public static String getWifiIp() {
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    int i = wifiInfo.getIpAddress();
    String ip =
        (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    return ip;
  }

  public static int getWifiLinkSpeed() {
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    return wifiInfo.getLinkSpeed();
  }

  public static String getWifiMac() {
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    return wifiInfo.getMacAddress();
  }

  public static String getWifiBSSID() {
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    return wifiInfo.getBSSID();
  }

  public static List<ScanResult> getWifiList() {
    List<ScanResult> scanResultList = new ArrayList<>();
    List<String> ssidList = new ArrayList<>();
    if (wifiManager.isWifiEnabled()) {
      List<ScanResult> allScanList = wifiManager.getScanResults();
      if (allScanList != null && allScanList.size() > 0) {
        for (ScanResult scanResult : allScanList) {
          if (!TextUtils.isEmpty(scanResult.SSID)) {
            int level = WifiManager.calculateSignalLevel(scanResult.level, 4);
            if (level > 0) {
              if (!ssidList.contains(scanResult.SSID)) {
                ssidList.add(scanResult.SSID);
                scanResultList.add(scanResult);
              }
            }
          }
        }
      }
    }
    return scanResultList;
  }

  public static void openWifi() {
    if (!wifiManager.isWifiEnabled()) {
      wifiManager.setWifiEnabled(true);
    }
  }

  public static boolean wifiIsOpen() {
    if (wifiManager.isWifiEnabled()) {
      return true;
    }
    return false;
  }

  public static void closeWifi() {
    if (wifiManager != null) {
      wifiManager.setWifiEnabled(false);
    }
  }

  public static void removeNetwork(String ssid) {
    WifiConfiguration config = new WifiConfiguration();
    config.allowedAuthAlgorithms.clear();
    config.allowedGroupCiphers.clear();
    config.allowedKeyManagement.clear();
    config.allowedPairwiseCiphers.clear();
    config.allowedProtocols.clear();
    config.SSID = "\"" + ssid + "\"";
    WifiConfiguration tempConfig = isExist(ssid);
    if (tempConfig != null) {
      wifiManager.removeNetwork(tempConfig.networkId);
    }
  }

  public static WifiConfiguration createWifiInfo(String ssid, String password, int type) {
    WifiConfiguration config = new WifiConfiguration();
    config.allowedAuthAlgorithms.clear();
    config.allowedGroupCiphers.clear();
    config.allowedKeyManagement.clear();
    config.allowedPairwiseCiphers.clear();
    config.allowedProtocols.clear();
    config.SSID = "\"" + ssid + "\"";
    WifiConfiguration tempConfig = isExist(ssid);
    if (tempConfig != null) {
      wifiManager.removeNetwork(tempConfig.networkId);
    }
    if (type == 1) {
      config.wepKeys[0] = "\"" + "\"";
      config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
      config.wepTxKeyIndex = 0;
    }
    if (type == 2) {
      config.hiddenSSID = true;
      config.wepKeys[0] = "\"" + password + "\"";
      config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
      config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
      config.wepTxKeyIndex = 0;
    }
    if (type == 3) {
      config.preSharedKey = "\"" + password + "\"";
      config.hiddenSSID = true;
      config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
      config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
      config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
      config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
      config.status = WifiConfiguration.Status.ENABLED;
    }
    return config;
  }

  public static WifiConfiguration isExist(String ssid) {
    List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
    if (existingConfigs != null) {
      for (WifiConfiguration existingConfig : existingConfigs) {
        if (existingConfig.SSID.equals("\"" + ssid + "\"")) {
          return existingConfig;
        }
      }
    }
    return null;
  }

  public static void init() {
    registerNetworkCallBack();
  }

  public static void onNetWorkAvailable() {
    List<NetWorkChangeCallBack> netWorkChangeCallBackList2 = new ArrayList<>();
    netWorkChangeCallBackList2.addAll(netWorkChangeCallBackList);
    for (NetWorkChangeCallBack netWorkChangeCallBack : netWorkChangeCallBackList2) {
      netWorkChangeCallBack.onNetWorkAvailable();
    }
  }

  public static void onNetWorkUnAvailable() {
    List<NetWorkChangeCallBack> netWorkChangeCallBackList2 = new ArrayList<>();
    netWorkChangeCallBackList2.addAll(netWorkChangeCallBackList);
    for (NetWorkChangeCallBack netWorkChangeCallBack : netWorkChangeCallBackList2) {
      netWorkChangeCallBack.onNetWorkUnAvailable();
    }
  }

  public static void addNetWorkChangeCallBack(NetWorkChangeCallBack netWorkChangeCallBack) {
    netWorkChangeCallBackList.add(netWorkChangeCallBack);
  }

  public static void removeNetWorkChangeCallBack(NetWorkChangeCallBack netWorkChangeCallBack) {
    netWorkChangeCallBackList.remove(netWorkChangeCallBack);
  }

  public static WifiInfo getConnectWifiInfo() {
    if (isWifiAvailable()) {
      WifiInfo wifiInfo = wifiManager.getConnectionInfo();
      LogMgr.d(TAG, "wifi info:" + wifiInfo.toString());
      LogMgr.d(TAG, "ssid:" + wifiInfo.getSSID());
      return wifiInfo;
    }
    return null;
  }

  public static boolean isWifiAvailable() {
    ConnectivityManager mgr = (ConnectivityManager) ContextUtils.getContext()
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo[] info = mgr.getAllNetworkInfo();
    if (info != null) {
      for (int i = 0; i < info.length; i++) {
        if (info[i].getState() == NetworkInfo.State.CONNECTED
            && info[i].getType() == ConnectivityManager.TYPE_WIFI) {
          return true;
        }
      }
    }
    return false;
  }

  public static String getWifiInfo() {
    if (isWifiAvailable()) {
      WifiInfo wifiInfo = wifiManager.getConnectionInfo();
      LogMgr.d(TAG, "wifi info:" + wifiInfo.toString());
      LogMgr.d(TAG, "ssid:" + wifiInfo.getSSID());
      return wifiInfo.getSSID().replaceAll("^\"|\"$", "");
    }
    return "";
  }

  public static HashMap<String, ScanResult> getWifiMap() {
    HashMap<String, ScanResult> hashScanResult = new HashMap<>();
    if (wifiManager.isWifiEnabled()) {
      List<ScanResult> wifiManagerScanResults = wifiManager.getScanResults();
      if (wifiManagerScanResults != null && wifiManagerScanResults.size() > 0) {
        for (ScanResult scanResult : wifiManagerScanResults) {
          if (!TextUtils.isEmpty(scanResult.SSID)) {
            hashScanResult.put(scanResult.SSID, scanResult);
          }
        }
      }
    }
    return hashScanResult;
  }

  private static void registerNetworkCallBack() {
    if (networkCallback == null) {
      getNetworkCallback();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        connectivityManager.requestNetwork(new NetworkRequest.Builder().build(), networkCallback);
      } else {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
        ContextUtils.getContext().registerReceiver(networkChangeReceiver, intentFilter);
      }
    }
  }

  private static void getNetworkCallback() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      if (networkCallback == null) {
        networkCallback = new ConnectivityManager.NetworkCallback() {
          @Override public void onAvailable(Network network) {
            super.onAvailable(network);
            LogMgr.d(TAG, "onAvailable:" + getWifiName());
            isAllLevelConnected = true;
            mainHandler.removeCallbacksAndMessages(null);
            mainHandler.post(new Runnable() {
              @Override public void run() {
                onNetWorkAvailable();
              }
            });
          }

          @Override public void onLost(Network network) {
            super.onLost(network);
            LogMgr.d(TAG, "onLost");
            isAllLevelConnected = false;
            mainHandler.removeCallbacksAndMessages(null);
            mainHandler.post(new Runnable() {
              @Override public void run() {
                onNetWorkUnAvailable();
              }
            });
          }
        };
      }
    }
  }

  static class NetworkChangeReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
      if (NetUtils.isNetworkAvailable()) {
        LogMgr.d(TAG, "networkAvailable3" + getWifiName());
        isAllLevelConnected = true;
        onNetWorkAvailable();
      } else {
        LogMgr.d(TAG, "networkUnAvailable");
        isAllLevelConnected = false;
        onNetWorkUnAvailable();
      }
    }
  }

  public static boolean networkCapabilities(Context context) {
    try {
      ConnectivityManager connectivityManager =
          (ConnectivityManager) context.getApplicationContext()
              .getSystemService(Context.CONNECTIVITY_SERVICE);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        NetworkCapabilities networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        boolean isEnable =
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        LogMgr.d(TAG, "networkCapabilities:" + isEnable);
        return isEnable;
      } else {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  private static Network getCurrentNetwork(Context context) {
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    Method method = null;
    Network network = null;
    try {
      method = wifiManager.getClass().getMethod("getCurrentNetwork");
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    try {
      network = (Network) method.invoke(wifiManager);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return network;
  }
}
