package com.unisound.sdk.service.utils;

import android.os.Handler;
import android.os.Looper;

public class HandlerUtils {
  public static Handler mainHandler = new Handler(Looper.getMainLooper());

  private HandlerUtils() {

  }

  public static Handler getMainHandler() {
    return mainHandler;
  }
}
