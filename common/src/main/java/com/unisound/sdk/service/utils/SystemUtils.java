package com.unisound.sdk.service.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.htfyun.hardware.NVRomController;
import com.unisound.sdk.service.utils.account.UserLoginUtils;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.receiver.StatusBarReceiver;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class SystemUtils {

  private SystemUtils() {

  }

  private static final String TAG = "SystemUtils";
  public static final String PREFS_FILE = "gank_device_id.xml";
  public static final String PREFS_DEVICE_ID = "gank_device_id";
  private static boolean isDeviceUse = true;
  private static PowerManager.WakeLock wakeLock;

  public static void setIsDeviceUse(boolean isDeviceUse) {
    SystemUtils.isDeviceUse = isDeviceUse;
  }

  public static boolean isIsDeviceUse() {
    return isDeviceUse;
  }

  public static String getDeviceId() {
    String deviceId =
        SharedPreferencesHelper.getInstance().getStringValue(Constant.DEVICE_UDID, "");
    if (deviceId != null && !TextUtils.isEmpty(deviceId.trim())) {
      LogMgr.d(TAG, "deviceId:" + deviceId);
      return deviceId;
    }
    if (isDeviceUse) {
      deviceId = NVRomController.getInstance().getNVRomString(Constant.DEVICE_UDID);
      if (deviceId != null && !TextUtils.isEmpty(deviceId.trim())) {
        SharedPreferencesHelper.getInstance().saveStringValue(Constant.DEVICE_UDID, deviceId);
      }
      return deviceId;
    } else {
      if (ContextUtils.getContext() != null && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
          && ContextUtils.getContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
          == PackageManager.PERMISSION_GRANTED) || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        TelephonyManager phoneManager = (TelephonyManager) ContextUtils.getContext()
            .getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = phoneManager.getDeviceId();
        if (deviceId == null || TextUtils.isEmpty(deviceId.trim())) {
          deviceId = SystemPropertyUtils.getSystemProperty(("sys.serialno"));
        }
        if (deviceId == null || TextUtils.isEmpty(deviceId.trim())) {
          deviceId = UUID.randomUUID().toString();
        }
        SharedPreferencesHelper.getInstance().saveStringValue(Constant.DEVICE_UDID, deviceId);
        return deviceId;
      } else {
        LogMgr.d(TAG, "no permission read phone state");
        String udid = UUID.randomUUID().toString();
        SharedPreferencesHelper.getInstance().saveStringValue(Constant.DEVICE_UDID, udid);
        return udid;
      }
    }
  }

  /***
   * 获取版本号
   */
  public static String getAppVersion() {
    String versionName = "";
    try {
      PackageInfo info = ContextUtils.getContext()
          .getPackageManager()
          .getPackageInfo(ContextUtils.getContext().getPackageName(), 0);
      versionName = info.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    LogMgr.i(TAG, "getVersionCode and code:" + versionName);
    return versionName;
  }

  public static int getAppVersionCode() {
    int versionCode = 0;
    try {
      PackageInfo info = ContextUtils.getContext()
          .getPackageManager()
          .getPackageInfo(ContextUtils.getContext().getPackageName(), 0);
      versionCode = info.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    LogMgr.i(TAG, "getVersionCode and code:" + versionCode);
    return versionCode;
  }

  /**
   * 获取当前UNIX的时间戳
   */
  public static long getCurrentUnixTimestamp() {
    return System.currentTimeMillis() / 1000;
  }

  /**
   * 获取设备mac地址
   */
  public static String getMac() {
    if (ContextUtils.getContext() != null) {
      String strMac = null;
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        LogMgr.e("=====", "6.0以下");
        strMac = MacUtil.getLocalMacAddressFromWifiInfo(ContextUtils.getContext());
        return strMac;
      } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N
          && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        LogMgr.e("=====", "6.0以上7.0以下");
        strMac = MacUtil.getMacAddress(ContextUtils.getContext());
        return strMac;
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        LogMgr.e("=====", "7.0以上");
        if (!TextUtils.isEmpty(MacUtil.getMacAddress())) {
          LogMgr.e("=====", "7.0以上1");
          strMac = MacUtil.getMacAddress();
          return strMac;
        } else if (!TextUtils.isEmpty(MacUtil.getMachineHardwareAddress())) {
          LogMgr.e("=====", "7.0以上2");
          strMac = MacUtil.getMachineHardwareAddress();
          return strMac;
        } else {
          LogMgr.e("=====", "7.0以上3");
          strMac = MacUtil.getLocalMacAddressFromBusybox();
          return strMac;
        }
      }
    }
    return "00:00:00:00:00:00";
  }

  /**
   * 通过网络接口取mac地址
   */
  public static String getNetMac() {
    try {
      List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface nif : all) {
        if (!"wlan0".equalsIgnoreCase(nif.getName())) continue;

        byte[] macBytes = nif.getHardwareAddress();
        if (macBytes == null) {
          return null;
        }

        StringBuilder res1 = new StringBuilder();
        for (byte b : macBytes) {
          res1.append(String.format("%02X:", b));
        }

        if (res1.length() > 0) {
          res1.deleteCharAt(res1.length() - 1);
        }
        return res1.toString();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static int getSystemVersionInt() {
    int version = (int) (Build.TIME / 1000);
    LogMgr.d(TAG, "getSystemVersion:" + version);
    return version;
  }

  public static String getVersionString() {
    String version = "";
    try {
      Class<?> classType = Class.forName("android.os.SystemProperties");
      Method method = classType.getDeclaredMethod("get", new Class<?>[] { String.class });
      version = (String) method.invoke(classType, "ro.build.unisound");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    if (!TextUtils.isEmpty(version)) {
      version = version + "." + getSystemVersionInt();
    }
    return version;
  }

  /**
   * 获取wifi名字
   */
  public static String getWifiName() {
    if (ContextUtils.getContext() != null) {
      WifiManager wifiMgr =
          (WifiManager) (ContextUtils.getContext()).getSystemService(Context.WIFI_SERVICE);
      int wifiState = wifiMgr.getWifiState();
      WifiInfo info = wifiMgr.getConnectionInfo();
      String wifiId = info != null ? info.getSSID() : null;
      return wifiId;
    } else {
      return "";
    }
  }

  /**
   * 获取当前的运营商
   *
   * @return 运营商名字
   */
  public static String getOperator() {
    if (ContextUtils.getContext() != null) {
      String providersName = "";
      TelephonyManager telephonyManager =
          (TelephonyManager) ContextUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
      String imsi = telephonyManager.getSimOperator();
      LogMgr.i(TAG, "运营商代码" + imsi);
      if (imsi != null) {
        if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
          providersName = "中国移动";
        } else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
          providersName = "中国联通";
        } else if (imsi.startsWith("46003")) {
          providersName = "中国电信";
        }
        return providersName;
      }
    }
    return "没有获取到sim卡信息";
  }

  /**
   * 获取设备名称
   */
  public static String getProductName() {
    return Build.PRODUCT;
  }

  /**
   * 获取设备型号
   */
  public static String getProductModel() {
    return Build.MODEL;
  }

  /**
   * 获取设备制造商名称
   */
  public static String getProductMfr() {
    return Build.MANUFACTURER;
  }

  /**
   * 获取操作系统
   */
  public static String getProductOs() {
    return "ANDROID";
  }

  /**
   * 获取操作系统版本
   */
  public static String getProductOsVersion() {
    return Build.VERSION.RELEASE;
  }

  /**
   * 获取硬件序列号
   */
  public static String getHardwareSn() {
    return Build.SERIAL;
  }

  public static String getPackName() {
    if (ContextUtils.getContext() != null) {
      return ContextUtils.getContext().getPackageName();
    } else {
      return "";
    }
  }

  public synchronized static boolean getInitStatus(String url) {
    try {
      if (isDeviceUse) {
        String urlTemp = NVRomController.getInstance().getNVRomString(Constant.KAR_SERVER_URL);
        if (urlTemp == null || !urlTemp.equals(url)) {
          return false;
        }
        String deviceId = NVRomController.getInstance().getNVRomString(Constant.INIT_DEVICE_ID);
        if (deviceId == null || !deviceId.equals(getDeviceId())) {
          return false;
        }
        String initStatus = NVRomController.getInstance().getNVRomString(Constant.INIT_DEVICE);
        LogMgr.d(TAG, "status read success:" + initStatus);
        if (!TextUtils.isEmpty(initStatus)) {
          return Boolean.parseBoolean(initStatus);
        }
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  public synchronized static void saveInitStatus(boolean value, String url) {
    if (isDeviceUse) {
      NVRomController.getInstance().setNVRomString(Constant.KAR_SERVER_URL, url);
      NVRomController.getInstance().setNVRomString(Constant.INIT_DEVICE_ID, getDeviceId());
      if (NVRomController.getInstance()
          .setNVRomString(Constant.INIT_DEVICE, String.valueOf(value))) {
        LogMgr.d(TAG, "status save success");
      } else {
        LogMgr.d(TAG, "status save fail");
      }
    }
  }

  public synchronized static String getStringFromFile(String key) {
    String value = "";
    if (isDeviceUse) {
      value = NVRomController.getInstance().getNVRomString(key);
      LogMgr.d(TAG, "getStringFromFile success:" + value);
    } else {
      value = SharedPreferencesHelper.getInstance().getStringValue(key, null);
    }
    return value;
  }

  public synchronized static void saveStringToFile(String key, String value) {
    if (isDeviceUse) {
      if (NVRomController.getInstance().setNVRomString(key, value)) {
        LogMgr.d(TAG, "saveStringToFile success");
      } else {
        LogMgr.d(TAG, "saveStringToFile fail");
      }
    } else {
      SharedPreferencesHelper.getInstance().saveStringValue(key, value);
    }
  }

  public synchronized static void saveDeviceAddressId(long deviceId) {
    if (isDeviceUse) {
      if (NVRomController.getInstance()
          .setNVRomString(Constant.ADDRESS_DEVICE_ID, String.valueOf(deviceId))) {
        LogMgr.d(TAG, "saveDeviceAddressId success:" + deviceId);
      } else {
        LogMgr.d(TAG, "saveDeviceAddressId fail:" + deviceId);
      }
    } else {
      SharedPreferencesHelper.getInstance()
          .saveStringValue(Constant.ADDRESS_DEVICE_ID, String.valueOf(deviceId));
    }
  }

  public synchronized static long getDeviceAddressId() {
    try {
      if (isDeviceUse) {
        String deviceAddressStr =
            NVRomController.getInstance().getNVRomString(Constant.ADDRESS_DEVICE_ID);
        LogMgr.d(TAG, "getDeviceAddressId:" + deviceAddressStr);
        if (!TextUtils.isEmpty(deviceAddressStr)) {
          return Long.parseLong(deviceAddressStr);
        }
      } else {
        String deviceAddressStr =
            SharedPreferencesHelper.getInstance().getStringValue(Constant.ADDRESS_DEVICE_ID, "");
        if (!TextUtils.isEmpty(deviceAddressStr)) {
          return Long.parseLong(deviceAddressStr);
        }
      }
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public synchronized static void saveLongToFile(String key, long value) {
    if (isDeviceUse) {
      if (NVRomController.getInstance().setNVRomString(key, String.valueOf(value))) {
        LogMgr.d(TAG, "saveLongToFile success");
      } else {
        LogMgr.d(TAG, "saveLongToFile fail");
      }
    } else {
      SharedPreferencesHelper.getInstance().saveLongValue(key, value);
    }
  }

  public synchronized static long getLongFromFile(String key) {
    long value = 0;
    if (isDeviceUse) {
      String longStr = NVRomController.getInstance().getNVRomString(key);
      LogMgr.d(TAG, "long read success:" + longStr);
      if (!TextUtils.isEmpty(longStr)) {
        value = Long.parseLong(longStr);
      }
    } else {
      value = SharedPreferencesHelper.getInstance().getLongValue(key);
    }
    return value;
  }

  public synchronized static long getLongFromFile(String key, long defaultValue) {
    long value = defaultValue;
    if (isDeviceUse) {
      String longStr = NVRomController.getInstance().getNVRomString(key);
      LogMgr.d(TAG, "long read success:" + longStr);
      if (!TextUtils.isEmpty(longStr)) {
        value = Long.parseLong(longStr);
      }
    } else {
      value = SharedPreferencesHelper.getInstance().getLongValue(key);
    }
    return value;
  }

  public synchronized static void clearNavRom() {
    if (isDeviceUse) {
      NVRomController.getInstance().removeStringFromNVRomData(Constant.KAR_SERVER_URL);
      NVRomController.getInstance().removeStringFromNVRomData(Constant.INIT_DEVICE);
      NVRomController.getInstance().removeStringFromNVRomData(UserPreferenceUtils.FLUSH_TOKEN);
      NVRomController.
          getInstance().removeStringFromNVRomData(Constant.ADDRESS_DEVICE_ID);
      NVRomController.
          getInstance().removeStringFromNVRomData(Constant.VOICE_LIMIT);
      NVRomController.
          getInstance().removeStringFromNVRomData(Constant.PLAY_TIME_LIMIT_IS_OPEN);
      NVRomController.
          getInstance().removeStringFromNVRomData(Constant.PLAY_TIME_LIMIT);
      SharedPreferencesHelper.getInstance().saveStringValue(UserPreferenceUtils.ACCESS_TOKEN, "");
      SharedPreferencesHelper.getInstance().saveLongValue(UserPreferenceUtils.ACCESS_VALID_TIME, 0);
      SharedPreferencesHelper.getInstance()
          .saveLongValue(UserPreferenceUtils.ACCESS_REFRESH_TIME, 0);
    }
  }

  public static String getSHA1(Context context) {
    try {
      PackageInfo info = context.getPackageManager()
          .getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
      byte[] cert = info.signatures[0].toByteArray();
      MessageDigest md = MessageDigest.getInstance("SHA1");
      byte[] publicKey = md.digest(cert);
      StringBuffer hexString = new StringBuffer();
      for (int i = 0; i < publicKey.length; i++) {
        String appendString = Integer.toHexString(0xFF & publicKey[i]).toUpperCase(Locale.US);
        if (appendString.length() == 1) hexString.append("0");
        hexString.append(appendString);
        hexString.append(":");
      }
      String result = hexString.toString();
      return result.substring(0, result.length() - 1);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static String getTopActivity(Context context) {
    try {
      ActivityManager manager =
          (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
      List<ActivityManager.RunningTaskInfo> runningTaskInfoList = manager.getRunningTasks(1);
      if (runningTaskInfoList != null && runningTaskInfoList.size() > 0) {
        String activity = (runningTaskInfoList.get(0).topActivity).toString();
        LogMgr.d(TAG, "getTopActivity:" + activity);
        return activity;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static ComponentName getTopPackage(Context context) {
    try {
      ActivityManager manager =
          (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
      List<ActivityManager.RunningTaskInfo> runningTaskInfoList = manager.getRunningTasks(1);
      if (runningTaskInfoList != null && runningTaskInfoList.size() > 0) {
        ComponentName topPackage = runningTaskInfoList.get(0).topActivity;
        LogMgr.d(TAG, "getTopPackage:" + topPackage);
        return topPackage;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void closeBtDevice() {
    if (StatusBarReceiver.isBtDeviceConnected()) {
      BluetoothAdapter.getDefaultAdapter().disable();
    }
  }

  public static boolean isScreenOn(Context context) {
    PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    boolean isScreenOn = powerManager.isScreenOn();
    LogMgr.d(TAG, "isScreenOn:" + isScreenOn);
    return isScreenOn;
  }

  public static String getTopPackageNameFor21(Context mContext) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      String topPackageName = "";
      android.app.usage.UsageStatsManager usm =
          (android.app.usage.UsageStatsManager) mContext.getSystemService(
              Context.USAGE_STATS_SERVICE);
      long endTime = System.currentTimeMillis();
      long startTime = System.currentTimeMillis() - 60 * 1000 * 60 * 12;
      android.app.usage.UsageEvents uEvents = null;
      uEvents = usm.queryEvents(startTime, endTime);
      while (uEvents.hasNextEvent()) {
        android.app.usage.UsageEvents.Event e = new android.app.usage.UsageEvents.Event();
        uEvents.getNextEvent(e);
        if (e != null) {
          topPackageName = e.getPackageName();
        }
      }
      LogMgr.d(TAG, "getTopPackageNameFor21:" + topPackageName);
      return topPackageName;
    }
    return "";
  }

  public static boolean isAppForeground(Context context, String packageName) {
    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList =
        activityManager.getRunningAppProcesses();
    if (runningAppProcessInfoList == null) {
      LogMgr.d(TAG, "runningAppProcessInfoList is null!");
      return false;
    }
    LogMgr.d(TAG, "RunningAppProcessInfo:" + runningAppProcessInfoList.size());
    for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
      if (processInfo.processName.equals(packageName) && (processInfo.importance
          == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)) {
        return true;
      }
    }
    return false;
  }

  public static void acquireWakeLock() {
    LogMgr.d(TAG, "acquireWakeLock");
    PowerManager pm =
        (PowerManager) ContextUtils.getContext().getSystemService(Context.POWER_SERVICE);
    wakeLock = pm.newWakeLock(PowerManager.ON_AFTER_RELEASE | PowerManager.PARTIAL_WAKE_LOCK,
        "UnisoundUsc");
    wakeLock.acquire();
  }

  public static void release() {
    LogMgr.d(TAG, "release");
    if (wakeLock != null) {
      wakeLock.release();
    }
  }

  public static void updateTime() {
    UserLoginUtils.getServerTime(new ResponseCallBack<Long>() {
      @Override public void onResponse(Long response) {
        if (response > 0) {
          LogMgr.d(TAG, "set time:" + response);
          SystemClock.setCurrentTimeMillis(response * 1000);
        }
      }

      @Override public void onError(String error) {
        LogMgr.d(TAG, "get time error:" + error);
      }
    });
  }
}
