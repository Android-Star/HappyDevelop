package com.unisound.sdk.service.utils.led;

import com.htfyun.hardware.LedController;
import com.unisound.sdk.service.utils.LogMgr;

public class Led {
  private static final String TAG = "Led";
  private static final int MAX_BRIGHT = 255;
  private static int ledPriority = -1;
  private static int ledState = -1;
  private static boolean ledOn = false;

  private Led() {

  }

  public static void setLedState(int state, boolean on) {
    LogMgr.d(TAG, "setLedState:" + state + ",oldState:" + ledState + ",on:" + on);
    int priority = LedState.getPriority(state);
    if (ledState == state && ledOn == on) {
      return;
    }
    if (on) {
      if (priority >= ledPriority) {
        ledState = state;
        ledOn = true;
        ledPriority = priority;
        if (ledState == LedState.MESSAGE_NOT_READ) {
          LedController.LedBreathModeAttr attr = LedController.LedBreathModeAttr.toMode(1);
          LedController.INSTANCE.setLedBreath(attr, 3);
        } else if (ledState == LedState.SEARCH) {
          LedController.LedBreathModeAttr attr = LedController.LedBreathModeAttr.toMode(1);
          LedController.INSTANCE.setLedBreath(attr, 0);
        } else if (ledState == LedState.BODY_TOUCH) {
          LedController.LedBreathModeAttr attr = LedController.LedBreathModeAttr.toMode(1);
          LedController.INSTANCE.setLedBreath(attr, 3);
        } else if (ledState == LedState.CALL_IN) {
          LedController.INSTANCE.setMarquee(100, 0, 8);
        } else if (ledState == LedState.DISTANCE_CLOSE) {
          LedController.INSTANCE.setAllLedBrightness(MAX_BRIGHT);
        }
      }
    } else {
      if (state == ledState) {
        LedController.INSTANCE.setAllLedBrightness(0);
        ledOn = false;
        ledPriority = -1;
        ledState = -1;
      }
    }
  }
}
