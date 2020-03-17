package com.example.wilsonhan.happydevelop.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.unisound.sdk.service.utils.ContextUtils;
import java.lang.reflect.Method;

public class AppUtils {
  private AppUtils() {
  }

  public static int getVersionCode() {
    int versionCode = 1;
    try {
      versionCode = ContextUtils.getContext()
          .getPackageManager()
          .getPackageInfo(ContextUtils.getContext().getPackageName(), 0).versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return versionCode;
  }

  public static String getVersionName() {
    String versionName = "";
    try {
      versionName = ContextUtils.getContext()
          .getPackageManager()
          .getPackageInfo(ContextUtils.getContext().getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return versionName;
  }

  public static String getMeid() {
    TelephonyManager telephonyManager =
        (TelephonyManager) ContextUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
    @SuppressLint("MissingPermission") String imei = telephonyManager.getDeviceId();
    return imei;
  }

  public static String getUpdateTime() {
    long time = 0;
    try {
      time = ContextUtils.getContext()
          .getPackageManager()
          .getPackageInfo(ContextUtils.getContext().getPackageName(), 0).lastUpdateTime;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return TimeUtils.getStringByFormat(time, TimeUtils.NORMAL_FORMAT);
  }

  public static String getNetType() {
    ConnectivityManager connectionManager = (ConnectivityManager) ContextUtils.getContext()
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
    if (networkInfo.isConnected()) {
      return networkInfo.getTypeName();
    } else {
      return "未连接";
    }
  }

  public static String getSystemVersion() {
    return Build.VERSION.RELEASE;
  }

  public static String getSystemHardVersion() {
    return Build.DISPLAY;
  }

  /**
   * @param slotId slotId为卡槽Id，它的值为 0、1；
   */
  public static String getIMEI(Context context, int slotId) {
    try {
      TelephonyManager manager =
          (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
      Method method = manager.getClass().getMethod("getImei", int.class);
      String imei = (String) method.invoke(manager, slotId);
      return imei;
    } catch (Exception e) {
      return "";
    }
  }

  public static String getDeviceSN() {

    String serialNumber = Build.SERIAL;

    return serialNumber;
  }
  //public static String getDeviceSN() {
  //  String serial = null;
  //  try {
  //    Class<?> c = Class.forName("android.os.SystemProperties");
  //    Method get = c.getMethod("get", String.class);
  //    serial = (String) get.invoke(c, "ro.serialno");
  //  } catch (Exception e) {
  //    e.printStackTrace();
  //  }
  //  return serial;
  //}
}
