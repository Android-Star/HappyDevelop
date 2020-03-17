package com.unisound.sdk.service.utils.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unisound.sdk.service.utils.R;

public class CustomPopupWindow {

  private TextView txtContent;
  private TextView tvLeft;
  private TextView tvRight;
  private LinearLayout layoutRoot;
  private RelativeLayout layoutBg;
  private PopupWindow popupWindow;
  private Context context;
  private Handler handler = new Handler(Looper.getMainLooper()) {
    @Override public void handleMessage(Message msg) {
      dismiss();
    }
  };

  public PopupWindow getPopupWindow() {
    return popupWindow;
  }

  public RelativeLayout getLayoutBg() {
    return layoutBg;
  }

  public CustomPopupWindow(Context context) {
    this.context = context;
    View view = LayoutInflater.from(context).inflate(R.layout.layout_custom_pop, null);
    txtContent = view.findViewById(R.id.txtContent);
    tvLeft = view.findViewById(R.id.tvLeft);
    tvRight = view.findViewById(R.id.tvRight);
    layoutRoot = view.findViewById(R.id.layoutRoot);
    layoutBg = view.findViewById(R.id.layoutBg);
    if (popupWindow != null) {
      if (popupWindow.isShowing()) {
        popupWindow.dismiss();
      }
      popupWindow = null;
    }
    popupWindow = new PopupWindow(view);
    popupWindow.setTouchable(true);
    popupWindow.setFocusable(true);
    popupWindow.setOutsideTouchable(true);
    popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
    popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
  }

  public void dismiss() {
    if (popupWindow != null && popupWindow.isShowing()) {
      handler.removeMessages(0);
      popupWindow.dismiss();
    }
  }

  public void show() {
    popupWindow.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.CENTER, 0,
        0);
  }

  public void show(long time) {
    popupWindow.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.CENTER, 0,
        0);
    handler.sendEmptyMessageDelayed(0, time);
  }

  public TextView getTxtContent() {
    return txtContent;
  }

  public TextView getTvLeft() {
    return tvLeft;
  }

  public TextView getTvRight() {
    return tvRight;
  }

  public CustomPopupWindow setLeftText(String text) {
    tvLeft.setText(text);
    return this;
  }

  public CustomPopupWindow setRightText(String text) {
    tvRight.setText(text);
    return this;
  }

  public CustomPopupWindow setLeftOnClick(View.OnClickListener onClick) {
    tvLeft.setOnClickListener(onClick);
    return this;
  }

  public CustomPopupWindow setRightOnClick(View.OnClickListener onClick) {
    tvRight.setOnClickListener(onClick);
    return this;
  }

  public CustomPopupWindow setContent(String content) {
    txtContent.setVisibility(View.VISIBLE);
    txtContent.setText(content);
    layoutRoot.setVisibility(View.GONE);
    return this;
  }

  public CustomPopupWindow addView(View view) {
    txtContent.setVisibility(View.GONE);
    layoutRoot.removeAllViews();
    layoutRoot.addView(view);
    return this;
  }

  public LinearLayout getLayoutRoot() {
    return layoutRoot;
  }

  public CustomPopupWindow addView(int layout) {
    View view = LayoutInflater.from(context).inflate(layout, layoutRoot, false);
    txtContent.setVisibility(View.GONE);
    layoutRoot.removeAllViews();
    layoutRoot.addView(view);
    return this;
  }
}
