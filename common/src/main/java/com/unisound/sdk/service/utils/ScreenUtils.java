package com.unisound.sdk.service.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;

public class ScreenUtils {

  private static final String TAG = "ScreenUtils";

  private Context mContext;
  private ScreenBroadcastReceiver mScreenReceiver;
  private static int width;
  private static int height;

  public static void setWidthAndHeight(int w, int h) {
    if (w > h) {
      width = w;
      height = h;
    } else {
      width = h;
      height = w;
    }
  }

  public static int getWidth() {
    return width;
  }

  public static int getHeight() {
    return height;
  }

  public ScreenUtils(Context context) {
    mContext = context;
    mScreenReceiver = new ScreenBroadcastReceiver();
  }

  /**
   * screen状态广播接收者
   */
  private class ScreenBroadcastReceiver extends BroadcastReceiver {
    private String action = null;

    @Override public void onReceive(Context context, Intent intent) {
      action = intent.getAction();
      Log.d(TAG, action);
      sendBroadcast(context, action);
    }
  }

  public static void sendBroadcast(Context context, String action) {
    if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
      action = "SCREEN_ON";
    } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
      action = "SCREEN_OFF";
    } else {
      return;
    }
    Intent intent = new Intent(action);
    intent.addCategory("receiver");
    intent.setPackage("com.unisound.kar.launcher");
    context.sendBroadcast(intent);
  }

  /**
   * 获取screen状态
   */
  private boolean getScreenState() {
    PowerManager manager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
    return manager.isScreenOn();
  }

  /**
   * 停止screen状态监听
   */
  public void unregisterListener() {
    mContext.unregisterReceiver(mScreenReceiver);
    getScreenState();
  }

  /**
   * 启动screen状态广播接收器
   */
  public void registerListener() {
    IntentFilter filter = new IntentFilter();
    filter.addAction(Intent.ACTION_SCREEN_ON);
    filter.addAction(Intent.ACTION_SCREEN_OFF);
    mContext.registerReceiver(mScreenReceiver, filter);
  }
}
