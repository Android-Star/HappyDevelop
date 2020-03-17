package com.unisound.sdk.service.utils.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.unisound.sdk.service.utils.ExoConstants;
import com.unisound.sdk.service.utils.LogMgr;

public class BaseActivity extends Activity {
  private static final String TAG = "BaseActivity";

  private CloseAppBroadcastReceiver closeAppBroadcastReceiver;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    closeAppBroadcastReceiver = new CloseAppBroadcastReceiver();
    registerListener();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unregisterListener();
  }

  private class CloseAppBroadcastReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
      LogMgr.d(TAG, "CloseAppBroadcastReceiver");
      receiveDismissMessage();
      LogMgr.d(TAG, "finish activity");
      finish();
    }
  }

  public void unregisterListener() {
    try {
      unregisterReceiver(closeAppBroadcastReceiver);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void registerListener() {
    IntentFilter filter = new IntentFilter();
    filter.addAction(ExoConstants.CLOSE_APP);
    registerReceiver(closeAppBroadcastReceiver, filter);
  }

  public void receiveDismissMessage() {

  }
}
