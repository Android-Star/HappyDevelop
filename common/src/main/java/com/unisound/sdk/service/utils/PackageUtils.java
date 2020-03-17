package com.unisound.sdk.service.utils;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.List;

public class PackageUtils {
  public static final String PACKAGE_SDK = "com.unisound.sdk.service";
  public static final String SDK_SERVICE = "com.unisound.sdk.service.SdkService";
  public static final String PACKAGE_READ = "com.unisound.read";

  private PackageUtils() {

  }

  public static String getTopAppPackageName(Context context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      UsageStatsManager m =
          (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
      if (m != null) {
        long now = System.currentTimeMillis();
        //获取60秒之内的应用数据
        List<UsageStats> stats =
            m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, now - 60 * 1000, now);
        String topActivity = "";
        //取得最近运行的一个app，即当前运行的app
        if ((stats != null) && (!stats.isEmpty())) {
          int j = 0;
          for (int i = 0; i < stats.size(); i++) {
            if (stats.get(i).getLastTimeUsed() > stats.get(j).getLastTimeUsed()) {
              j = i;
            }
          }
          topActivity = stats.get(j).getPackageName();
          return topActivity;
        }
      }
    }
    return null;
  }

  public static String getPackageName(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
      return packageInfo.packageName;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
