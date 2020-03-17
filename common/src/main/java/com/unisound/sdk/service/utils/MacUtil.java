package com.unisound.sdk.service.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MacUtil {
  private MacUtil() {

  }

  /**
   * 根据wifi信息获取本地mac
   */
  public static String getLocalMacAddressFromWifiInfo(Context context) {
    WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    WifiInfo winfo = wifi.getConnectionInfo();
    String mac = winfo.getMacAddress();
    return mac;
  }

  /**
   * android 6.0及以上、7.0以下 获取mac地址
   */
  public static String getMacAddress(Context context) {

    // 如果是6.0以下，直接通过wifimanager获取
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      String macAddress0 = getMacAddress0(context);
      if (!TextUtils.isEmpty(macAddress0)) {
        return macAddress0;
      }
    }
    String str = "";
    String macSerial = "";
    try {
      Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
      InputStreamReader ir = new InputStreamReader(pp.getInputStream());
      LineNumberReader input = new LineNumberReader(ir);
      for (; null != str;) {
        str = input.readLine();
        if (str != null) {
          macSerial = str.trim(); // 去空格
          break;
        }
      }
    } catch (Exception ex) {
      LogMgr.e("----->" + "NetInfoManager", "getMacAddress:" + ex.toString());
    }
    if (macSerial == null || "".equals(macSerial)) {
      try {
        return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
      } catch (Exception e) {
        e.printStackTrace();
        LogMgr.e("----->" + "NetInfoManager", "getMacAddress:" + e.toString());
      }
    }
    return macSerial;
  }

  private static String getMacAddress0(Context context) {
    if (isAccessWifiStateAuthorized(context)) {
      WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
      WifiInfo wifiInfo = null;
      try {
        wifiInfo = wifiMgr.getConnectionInfo();
        return wifiInfo.getMacAddress();
      } catch (Exception e) {
        LogMgr.e("----->" + "NetInfoManager", "getMacAddress0:" + e.toString());
      }
    }
    return "";
  }

  /**
   * Check whether accessing wifi state is permitted
   */
  private static boolean isAccessWifiStateAuthorized(Context context) {
    if (PackageManager.PERMISSION_GRANTED == context.checkCallingOrSelfPermission(
        "android.permission.ACCESS_WIFI_STATE")) {
      LogMgr.e("----->" + "NetInfoManager",
          "isAccessWifiStateAuthorized:" + "access wifi state is enabled");
      return true;
    } else {
      return false;
    }
  }

  private static String loadFileAsString(String fileName) throws Exception {
    FileReader reader = new FileReader(fileName);
    String text = loadReaderAsString(reader);
    reader.close();
    return text;
  }

  private static String loadReaderAsString(Reader reader) throws Exception {
    StringBuilder builder = new StringBuilder();
    char[] buffer = new char[4096];
    int readLength = reader.read(buffer);
    while (readLength >= 0) {
      builder.append(buffer, 0, readLength);
      readLength = reader.read(buffer);
    }
    return builder.toString();
  }

  /**
   * 根据IP地址获取MAC地址
   */
  public static String getMacAddress() {
    String strMacAddr = null;
    try {
      // 获得IpD地址
      InetAddress ip = getLocalInternetAddress();
      byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < b.length; i++) {
        if (i != 0) {
          buffer.append(':');
        }
        String str = Integer.toHexString(b[i] & 0xFF);
        buffer.append(str.length() == 1 ? 0 + str : str);
      }
      strMacAddr = buffer.toString().toUpperCase();
    } catch (Exception e) {
    }
    return strMacAddr;
  }

  /**
   * 获取移动设备本地IP
   */
  private static InetAddress getLocalInternetAddress() {
    InetAddress ip = null;
    try {
      // 列举
      Enumeration<NetworkInterface> enNetInterface = NetworkInterface.getNetworkInterfaces();
      while (enNetInterface.hasMoreElements()) { // 是否还有元素
        NetworkInterface ni = (NetworkInterface) enNetInterface.nextElement(); // 得到下一个元素
        Enumeration<InetAddress> enIp = ni.getInetAddresses(); // 得到一个ip地址的列举
        while (enIp.hasMoreElements()) {
          ip = enIp.nextElement();
          if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
            break;
          } else {
            ip = null;
          }
        }

        if (ip != null) {
          break;
        }
      }
    } catch (SocketException e) {

      e.printStackTrace();
    }
    return ip;
  }

  /**
   * 获取本地IP
   */
  private static String getLocalIpAddress() {
    try {
      for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
          en.hasMoreElements();) {
        NetworkInterface intf = en.nextElement();
        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
            enumIpAddr.hasMoreElements();) {
          InetAddress inetAddress = enumIpAddr.nextElement();
          if (!inetAddress.isLoopbackAddress()) {
            return inetAddress.getHostAddress().toString();
          }
        }
      }
    } catch (SocketException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * android 7.0及以上 （2）扫描各个网络接口获取mac地址
   *
   */
  /**
   * 获取设备HardwareAddress地址
   */
  public static String getMachineHardwareAddress() {
    Enumeration<NetworkInterface> interfaces = null;
    try {
      interfaces = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException e) {
      e.printStackTrace();
    }
    String hardWareAddress = null;
    NetworkInterface iF = null;
    if (interfaces == null) {
      return null;
    }
    while (interfaces.hasMoreElements()) {
      iF = interfaces.nextElement();
      try {
        hardWareAddress = bytesToString(iF.getHardwareAddress());
        if (hardWareAddress != null) break;
      } catch (SocketException e) {
        e.printStackTrace();
      }
    }
    return hardWareAddress;
  }

  /***
   * byte转为String
   *
   * @param bytes
   * @return
   */
  private static String bytesToString(byte[] bytes) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }
    StringBuilder buf = new StringBuilder();
    for (byte b : bytes) {
      buf.append(String.format("%02X:", b));
    }
    if (buf.length() > 0) {
      buf.deleteCharAt(buf.length() - 1);
    }
    return buf.toString();
  }

  /**
   * android 7.0及以上 （3）通过busybox获取本地存储的mac地址
   *
   */

  /**
   * 根据busybox获取本地Mac
   */
  public static String getLocalMacAddressFromBusybox() {
    String result = "";
    String mac = "";
    result = callCmd("busybox ifconfig", "HWaddr");
    // 如果返回的result == null，则说明网络不可取
    if (result == null) {
      return "网络异常";
    }
    // 对该行数据进行解析
    // 例如：eth0 Link encap:Ethernet HWaddr 00:16:E8:3E:DF:67
    if (result.length() > 0 && result.contains("HWaddr")) {
      mac = result.substring(result.indexOf("HWaddr") + 6, result.length() - 1);
      result = mac;
    }
    return result;
  }

  private static String callCmd(String cmd, String filter) {
    String result = "";
    String line = "";
    try {
      Process proc = Runtime.getRuntime().exec(cmd);
      InputStreamReader is = new InputStreamReader(proc.getInputStream());
      BufferedReader br = new BufferedReader(is);
      line = br.readLine();
      while (line != null && !line.contains(filter)) {
        result += line;
        line = br.readLine();
      }

      result = line;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
