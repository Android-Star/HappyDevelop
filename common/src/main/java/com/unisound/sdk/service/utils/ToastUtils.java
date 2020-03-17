package com.unisound.sdk.service.utils;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {

  private static Toast toast;

  private ToastUtils() {

  }

  private static void prepare() {
    if (!isInUiThread()) {
      Looper.prepare();
    }
  }

  private static void loop() {
    if (!isInUiThread()) {
      Looper.loop();
    }
  }

  public static void showLongToast(Context context, String text) {
    prepare();
    if (context == null) {
      context = ContextUtils.getContext();
    }
    if (toast != null) {
      toast.cancel();
      toast = null;
    }
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.layout_toast, (ViewGroup) null);
    TextView title = (TextView) view.findViewById(R.id.tvTitle);
    title.setText(text);
    toast = new Toast(context);
    toast.setView(view);
    //toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.show();
    loop();
  }

  public static void showShortToast(Context context, String text) {
    prepare();
    if (context == null) {
      context = ContextUtils.getContext();
    }
    if (toast != null) {
      toast.cancel();
      toast = null;
    }

    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.layout_toast, (ViewGroup) null);
    TextView title = (TextView) view.findViewById(R.id.tvTitle);
    title.setText(text);
    toast = new Toast(context);
    toast.setView(view);
    //toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
    toast.show();
    loop();
  }

  public static void showShortToast(Context context, int resId) {
    showShortToast(context, context.getResources().getString(resId));
  }

  public static void showLongToast(Context context, int resId) {
    showLongToast(context, context.getResources().getString(resId));
  }

  public static void showShortToast(int resId) {
    showShortToast(ContextUtils.getContext(),
        ContextUtils.getContext().getResources().getString(resId));
  }

  public static void showLongToast(int resId) {
    showLongToast(ContextUtils.getContext(),
        ContextUtils.getContext().getResources().getString(resId));
  }

  public static void showShortToast(String text) {
    showShortToast(ContextUtils.getContext(), text);
  }

  public static void showLongToast(String text) {
    showLongToast(ContextUtils.getContext(), text);
  }

  public static void cancelToast() {
    prepare();
    try {
      if (toast != null) {
        toast.cancel();
        toast = null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    loop();
  }

  private static boolean isInUiThread() {
    return Looper.myLooper() == Looper.getMainLooper();
  }
}
