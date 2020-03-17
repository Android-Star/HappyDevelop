package com.unisound.sdk.service.utils.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import com.unisound.sdk.service.ISdkCallBack;
import com.unisound.sdk.service.ISdkService;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.NetUtils;
import com.unisound.sdk.service.utils.PackageUtils;
import com.unisound.sdk.service.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends MultiDexApplication {
  private static final String TAG = "BaseApplication";
  private ISdkService iSdkService;
  private ISdkCallBack.Stub iSdkCallBack;
  private List<ISdkCallBack.Stub> iSdkCallBackList = new ArrayList<>();

  public ISdkService getSdkService() {
    return iSdkService;
  }

  private ServiceConnection conSdk = new ServiceConnection() {
    @Override public void onServiceDisconnected(ComponentName arg0) {
      LogMgr.d(TAG, "conSdk onServiceDisconnected:" + this.getClass().getName());
      iSdkService = null;
      if (iSdkCallBack != null) {
        bindSdkService(iSdkCallBack);
      }
      if (iSdkCallBackList.size() > 0) {
        bindSdkService(iSdkCallBackList);
      }
    }

    @Override public void onServiceConnected(ComponentName arg0, IBinder binder) {
      LogMgr.d(TAG, "conSdk onServiceConnected:" + this.getClass().getName());
      try {
        iSdkService = ISdkService.Stub.asInterface(binder);
        if (iSdkCallBack != null) {
          LogMgr.d(TAG, "registerCallback:" + iSdkCallBack);
          iSdkService.registerCallback(iSdkCallBack);
        }
        for (ISdkCallBack.Stub iSdk : iSdkCallBackList) {
          LogMgr.d(TAG, "registerCallback2:" + iSdk);
          iSdkService.registerCallback(iSdk);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  };

  @Override public void onCreate() {
    super.onCreate();
    ContextUtils.setContext(this);
    NetUtils.init();
    printScreenAndDevice();
  }

  private void printScreenAndDevice() {
    DisplayMetrics dm = this.getResources().getDisplayMetrics();
    int screenWidth = dm.widthPixels;
    int screenHeight = dm.heightPixels;
    ScreenUtils.setWidthAndHeight(screenWidth, screenHeight);
    String model = android.os.Build.MODEL;
    String carrier = android.os.Build.MANUFACTURER;
    LogMgr.d(TAG, carrier + "," + model + ",screen:" + screenWidth + "x" + screenHeight + ","
        + dm.densityDpi);
  }

  public void bindSdkService(ISdkCallBack.Stub iSdkCallBack) {
    this.iSdkCallBack = iSdkCallBack;
    bindService();
  }

  public void bindSdkService(List<ISdkCallBack.Stub> iSdkCallBackList) {
    this.iSdkCallBackList = iSdkCallBackList;
    bindService();
  }

  private void bindService() {
    Intent it = new Intent();
    it.setComponent(new ComponentName(PackageUtils.PACKAGE_SDK, PackageUtils.SDK_SERVICE));
    String packageName = ContextUtils.getContext().getPackageName();
    LogMgr.d(TAG, "packageName:" + packageName);
    it.setPackage(packageName);
    it.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    boolean result = this.getApplicationContext().bindService(it, conSdk, Service.BIND_AUTO_CREATE);
    LogMgr.d(TAG, "bindService:" + result);
  }
}
