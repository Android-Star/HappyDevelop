package com.unisound.sdk.service.utils;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemPropertyUtils {

  private final static String TAG = "SystemPropertyUtil";

  private SystemPropertyUtils() {

  }

  public static String getSystemProperty(String propName) {
    String line = "";
    try {
      Process process = Runtime.getRuntime().exec("getprop " + propName);
      InputStreamReader ir = new InputStreamReader(process.getInputStream());
      BufferedReader input = new BufferedReader(ir);
      line = input.readLine();
      ir.close();
      input.close();
      if (line == null || line.length() == 0) {
        line = "";
      }
    } catch (Exception ex) {
      Log.e(TAG, "Unable to read sysprop " + propName);
      return "";
    }
    return line;
  }

  public static void setSystemProperty(String propName, String value) {
    boolean setOK = false;
    int tryCount = 5;
    while (!setOK && (tryCount-- > 0)) {
      setOK = doSetSystemProperty(propName, value);
    }
  }

  private static boolean doSetSystemProperty(String propName, String value) {
    try {
      Runtime.getRuntime().exec("setprop " + propName + " " + value);
      String readbackString = getSystemProperty(propName);
      if (TextUtils.isEmpty(readbackString)) {
        return false;
      }
      if (readbackString.equals(value)) {
        return true;
      }
    } catch (Exception ex) {
      Log.e(TAG, "Unable to set sysprop " + propName);
      return false;
    }
    return false;
  }
}
