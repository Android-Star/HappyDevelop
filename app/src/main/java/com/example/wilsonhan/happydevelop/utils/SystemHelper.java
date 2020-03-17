package com.example.wilsonhan.happydevelop.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.unisound.sdk.service.utils.ContextUtils;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

public class SystemHelper {

  private SystemHelper() {
  }

  /**
   * 判断本地是否已经安装好了指定的应用程序包
   *
   * @param packageNameTarget ：待判断的 App 包名，如 微博 com.sina.weibo
   * @return 已安装时返回 true,不存在时返回 false
   */
  public static boolean appIsExist(Context context, String packageNameTarget) {
    if (!"".equals(packageNameTarget.trim())) {
      PackageManager packageManager = context.getPackageManager();
      List<PackageInfo> packageInfoList =
          packageManager.getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);
      for (PackageInfo packageInfo : packageInfoList) {
        String packageNameSource = packageInfo.packageName;
        if (packageNameSource.equals(packageNameTarget)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 将本应用置顶到最前端
   * 当本应用位于后台时，则将它切换到最前端
   */
  public static void setTopApp(Context context) {
    //if (!isRunningForeground(context)) {
    /**获取ActivityManager*/
    ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

    /**获得当前运行的task(任务)*/
    List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(100);
    for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
      /**找到本应用的 task，并将它切换到前台*/
      if (taskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
        activityManager.moveTaskToFront(taskInfo.id, 0);
        break;
      }
    }
    //}
  }

  /**
   * 判断本应用是否已经位于最前端
   *
   * @return 本应用已经位于最前端时，返回 true；否则返回 false
   */
  public static boolean isRunningForeground(Context context) {
    ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> appProcessInfoList =
        activityManager.getRunningAppProcesses();
    /**枚举进程*/
    for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
      if (appProcessInfo.importance
          == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
        if (appProcessInfo.processName.equals(context.getApplicationInfo().processName)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isRunningBackground(Context context) {
    ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses =
        activityManager.getRunningAppProcesses();
    for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcesses) {
      if (processInfo.processName.equals(context.getPackageName())) {
        return processInfo.importance
            == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
      }
    }
    return false;
  }

  public static void silentInstallApkByReflect(String apkPath) {
    PackageManager packageManager = ContextUtils.getContext().getPackageManager();
    Class<?> pmClz = packageManager.getClass();
    try {
      if (Build.VERSION.SDK_INT >= 21) {
        Class<?> aClass = Class.forName("android.app.PackageInstallObserver");
        Constructor<?> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object installObserver = constructor.newInstance();
        Method method =
            pmClz.getDeclaredMethod("installPackage", Uri.class, aClass, int.class, String.class);
        method.setAccessible(true);
        method.invoke(packageManager, Uri.fromFile(new File(apkPath)), installObserver, 2, null);
      } else {
        Method method = pmClz.getDeclaredMethod("installPackage", Uri.class,
            Class.forName("android.content.pm.IPackageInstallObserver"), int.class, String.class);
        method.setAccessible(true);
        method.invoke(packageManager, Uri.fromFile(new File(apkPath)), null, 2, null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
