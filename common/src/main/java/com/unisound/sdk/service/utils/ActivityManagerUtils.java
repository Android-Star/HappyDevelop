package com.unisound.sdk.service.utils;

import android.app.Activity;
import java.util.HashSet;

public class ActivityManagerUtils {
  private static final String TAG = "ActivityManagerUtils";
  private HashSet<Activity> activityList = new HashSet<>();
  private static ActivityManagerUtils activityManagerUtils = new ActivityManagerUtils();

  private ActivityManagerUtils() {

  }

  public static ActivityManagerUtils getInstance() {
    return activityManagerUtils;
  }

  public void addActivity(Activity activity) {
    activityList.add(activity);
  }

  public void removeActivity(Activity activity) {
    activityList.remove(activity);
  }

  public void finishAllActivity() {
    for (Activity activity : activityList) {
      activity.finish();
    }
  }
}
