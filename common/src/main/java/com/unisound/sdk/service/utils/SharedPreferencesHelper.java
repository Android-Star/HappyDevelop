package com.unisound.sdk.service.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Set;

public class SharedPreferencesHelper {

  private static final String TAG = "SharedPreferencesHelper";

  private SharedPreferencesHelper() {

  }

  private static SharedPreferencesHelper instance = new SharedPreferencesHelper();
  private SharedPreferences.Editor editor;
  private SharedPreferences sp;

  public static SharedPreferencesHelper getInstance() {
    LogMgr.d(TAG, "get instance");
    return instance;
  }

  private void initEditor(String key) {
    sp = ContextUtils.getContext().getSharedPreferences(key, Context.MODE_PRIVATE);
    editor = sp.edit();
  }

  public boolean saveStringValue(String key, String value) {
    initEditor(key);
    if (!TextUtils.isEmpty(key)) {
      editor.putString(key, value);
      return editor.commit();
    } else {
      return false;
    }
  }

  public String getStringValue(String key, String defaultValue) {
    initEditor(key);
    return sp.getString(key, defaultValue);
  }

  public boolean saveBooleanValue(String key, boolean booleanValue) {
    initEditor(key);
    if (!TextUtils.isEmpty(key)) {
      editor.putBoolean(key, booleanValue);
      return editor.commit();
    } else {
      return false;
    }
  }

  public boolean getBooleanValue(String key, boolean bool) {
    initEditor(key);
    return sp.getBoolean(key, bool);
  }

  public boolean saveIntValue(String key, int intValue) {
    initEditor(key);
    if (!TextUtils.isEmpty(key)) {
      editor.putInt(key, intValue);
      return editor.commit();
    } else {
      return false;
    }
  }

  public int getIntValue(String key) {
    initEditor(key);
    return sp.getInt(key, 0);
  }

  public int getIntValue(String key, int defaultValue) {
    initEditor(key);
    return sp.getInt(key, defaultValue);
  }

  public boolean saveLongValue(String key, long longValue) {
    initEditor(key);
    if (!TextUtils.isEmpty(key)) {
      editor.putLong(key, longValue);
      return editor.commit();
    } else {
      return false;
    }
  }

  public long getLongValue(String key) {
    initEditor(key);
    return sp.getLong(key, 0);
  }

  public boolean saveStringSetValue(String key, Set<String> setValue) {
    initEditor(key);
    if (!TextUtils.isEmpty(key)) {
      editor.putStringSet(key, setValue);
      return editor.commit();
    } else {
      return false;
    }
  }

  public Set<String> getStringSetValue(String key) {
    initEditor(key);
    return sp.getStringSet(key, null);
  }
}
