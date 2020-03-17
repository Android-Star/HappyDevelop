package com.unisound.sdk.service.utils;

import android.provider.Settings;

public class DefaultBrightOperator {

  private static final String TAG = "DefaultBrightOperator";
  public final static int MAX_BRIGHT = 255;
  private int brightDelta = (int) Math.ceil(1.0f * MAX_BRIGHT / 4);

  private static DefaultBrightOperator defaultBrightOperator = new DefaultBrightOperator();

  public static DefaultBrightOperator getInstance() {
    return defaultBrightOperator;
  }

  private DefaultBrightOperator() {

  }

  public boolean setBrightRaise() {
    int currentBright = getCurrentBright();
    if (currentBright == MAX_BRIGHT) return false;
    int adjustBright =
        currentBright + brightDelta > MAX_BRIGHT ? MAX_BRIGHT : currentBright + brightDelta;
    setBright(adjustBright);
    return true;
  }

  public boolean setBrightLower() {
    int currentBright = getCurrentBright();
    if (currentBright == 0) return false;
    int adjustBright = currentBright - brightDelta <= 0 ? 0 : currentBright - brightDelta;
    setBright(adjustBright);
    return true;
  }

  public boolean setBrightMax() {
    int currentBright = getCurrentBright();
    if (currentBright == MAX_BRIGHT) return false;
    setBright(MAX_BRIGHT);
    return true;
  }

  public boolean setBrightMin() {
    int currentBright = getCurrentBright();
    if (currentBright == 0) return false;
    setBright(0);
    return true;
  }

  public int getCurrentBright() {
    int screenBrightness = -1;
    try {
      screenBrightness = Settings.System.getInt(ContextUtils.getContext().getContentResolver(),
          Settings.System.SCREEN_BRIGHTNESS);
    } catch (Exception e) {
      e.printStackTrace();
    }
    LogMgr.d(TAG, "screenBrightness:" + screenBrightness);
    return screenBrightness;
  }

  public void setBright(int value) {
    LogMgr.d(TAG, "setBright:" + value);
    Settings.System.putInt(ContextUtils.getContext().getContentResolver(),
        Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
    Settings.System.putInt(ContextUtils.getContext().getContentResolver(),
        Settings.System.SCREEN_BRIGHTNESS, value);
  }
}
