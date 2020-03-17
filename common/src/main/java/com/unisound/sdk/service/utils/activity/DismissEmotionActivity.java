package com.unisound.sdk.service.utils.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.LogMgr;

public class DismissEmotionActivity extends BaseActivity {
  protected static final int DELAY_CLOSE_ACTIVITY = 1000;
  protected static final int DELAY_CLOSE_EMOJI = 1001;
  private static final String TAG = "DismissEmotionActivity";
  protected boolean broadCastResume = false;

  protected Handler handler = new Handler(Looper.getMainLooper()) {
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case DELAY_CLOSE_ACTIVITY:
          broadCastResume = true;
          ContextUtils.sendCloseAppBroadCast();
          LogMgr.d(TAG, "send closeApp broadCast:" + broadCastResume);
          break;
        case DELAY_CLOSE_EMOJI:
          dismissEmotion();
          break;
        default:
          break;
      }
    }
  };

  public void delayCloseActivity(long time, boolean isTtsEnd) {
    if (isTtsEnd) {
      if (handler.hasMessages(DELAY_CLOSE_ACTIVITY)) {
        handler.removeMessages(DELAY_CLOSE_ACTIVITY);
      }
      handler.sendEmptyMessageDelayed(DELAY_CLOSE_ACTIVITY, time);
      LogMgr.d(TAG, "delay time :" + time);
    } else {
      if (handler.hasMessages(DELAY_CLOSE_ACTIVITY)) {
        handler.removeMessages(DELAY_CLOSE_ACTIVITY);
        handler.sendEmptyMessageDelayed(DELAY_CLOSE_ACTIVITY, time);
        LogMgr.d(TAG, "delay time :" + time);
      }
    }
  }

  public void delayCloseActivity(long time) {
    if (handler.hasMessages(DELAY_CLOSE_ACTIVITY)) {
      handler.removeMessages(DELAY_CLOSE_ACTIVITY);
    }
    handler.sendEmptyMessageDelayed(DELAY_CLOSE_ACTIVITY, time);
    LogMgr.d(TAG, "delay time :" + time);
  }

  protected void removeTimeoutMessage() {
    if (handler.hasMessages(DELAY_CLOSE_ACTIVITY)) {
      handler.removeMessages(DELAY_CLOSE_ACTIVITY);
      LogMgr.d(TAG, "remove timeout message");
    }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogMgr.d(TAG, "onCreate");
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    LogMgr.d(TAG, "onNewIntent");
  }

  private void dismissEmotion() {
    LogMgr.d(TAG, "dismissEmotion");
    ContextUtils.sendDismissEmotion();
  }

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    LogMgr.d(TAG, "onWindowFocusChanged:" + hasFocus);
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) {
      dismissEmotion();
    }
  }

  private void delayDismissEmotion(int time) {
    handler.removeMessages(DELAY_CLOSE_EMOJI);
    handler.sendEmptyMessageDelayed(DELAY_CLOSE_EMOJI, time);
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override public void receiveDismissMessage() {
    super.receiveDismissMessage();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    handler.removeCallbacksAndMessages(null);
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      LogMgr.d(TAG, "click back");
      return false;
    }
    return super.onKeyDown(keyCode, event);
  }
}
