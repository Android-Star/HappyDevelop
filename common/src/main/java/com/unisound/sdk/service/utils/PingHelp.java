package com.unisound.sdk.service.utils;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PingHelp {
  private static final String TAG = "PingHelp";
  private static PingHelp instance = new PingHelp();
  public Thread thread;
  private List<NetCanUseCallback> callbackList = new CopyOnWriteArrayList<>();
  private boolean isCanUsed = false;
  private int netExceptionCount = 0;
  public Handler handler = new Handler() {
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      synchronized (TAG) {
        if (callbackList.size() > 0) {
          LogMgr.d(TAG, "call back:" + msg.what);
          List<NetCanUseCallback> callbackList2 = new CopyOnWriteArrayList<>();
          callbackList2.addAll(callbackList);
          for (NetCanUseCallback netCanUseCallback : callbackList2) {
            isCanUsed = msg.what == 0;
            if (isCanUsed) {
              netExceptionCount = 0;
              netCanUseCallback.netCanUse(true);
            } else {
              if (netExceptionCount >= 5) {
                netCanUseCallback.netCanUse(false);
                netExceptionCount = 0;
              } else {
                netExceptionCount++;
              }
            }
          }
        } else {
          LogMgr.d(TAG, "call back null");
        }
        //callbackList.clear();
        //thread = null;
        //handler.removeCallbacksAndMessages(null);
      }
    }
  };

  private PingHelp() {

  }

  public static PingHelp getInstance() {
    return instance;
  }

  public boolean isCanUsed() {
    return isCanUsed;
  }

  public void netCanUse(NetCanUseCallback callback) {
    synchronized (TAG) {
      LogMgr.d(TAG, "netCanUse:" + thread);
      if (!this.callbackList.contains(callback)) {
        this.callbackList.add(callback);
      }
      if (thread == null) {
        thread = new Thread(new PingRunnable());
        thread.start();
      }
    }
  }

  public void startPinServer() {
    synchronized (TAG) {
      LogMgr.d(TAG, "netCanUse:" + thread);
      if (thread == null) {
        thread = new Thread(new PingRunnable());
        thread.start();
      }
    }
  }

  public void addCallback(NetCanUseCallback callback) {
    synchronized (TAG) {
      LogMgr.d(TAG, "addCallback");
      if (!this.callbackList.contains(callback)) {
        this.callbackList.add(callback);
      }
    }
  }

  public void cancel(NetCanUseCallback callback) {
    synchronized (TAG) {
      LogMgr.d(TAG, "cancel");
      this.callbackList.remove(callback);
    }
  }

  private void pingWork() {
    try {
      Runtime runtime = Runtime.getRuntime();
      Process ipProcess = runtime.exec("ping -c 1 -w 2 www.baidu.com");
      int exitValue = ipProcess.waitFor();
      LogMgr.d(TAG, "exitValue:" + exitValue);
      handler.sendEmptyMessage(exitValue);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      LogMgr.d(TAG, "exitValue2");
      handler.sendEmptyMessage(1);
    }
  }

  class PingRunnable implements Runnable {
    @Override public void run() {
      while (true) {
        pingWork();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  interface NetCanUseCallback {
    void netCanUse(boolean canUse);
  }
}