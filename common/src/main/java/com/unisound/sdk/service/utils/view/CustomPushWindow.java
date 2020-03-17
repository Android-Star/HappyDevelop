package com.unisound.sdk.service.utils.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.unisound.sdk.service.utils.R;

public class CustomPushWindow {

  private CustomPushWindow() {

  }

  public static void showDialog(final Context context, final String text, int duration) {
    final WindowManager windowManager =
        (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    final View view = LayoutInflater.from(context).inflate(R.layout.layout_push, null);
    TextView txtContent = view.findViewById(R.id.txtContent);
    txtContent.setText(text);
    WindowManager.LayoutParams params = new WindowManager.LayoutParams();
    params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
    int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    params.flags = flags;
    params.format = PixelFormat.TRANSLUCENT;
    params.width = WindowManager.LayoutParams.MATCH_PARENT;
    params.height = 124;
    params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
    windowManager.addView(view, params);
    if (duration > 0) {
      view.postDelayed(new Runnable() {
        @Override public void run() {
          windowManager.removeViewImmediate(view);
        }
      }, duration);
    }
  }
}
