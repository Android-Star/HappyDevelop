package com.unisound.sdk.service.utils.view;

import android.view.View;
import com.unisound.sdk.service.utils.LogMgr;
import java.util.HashMap;
import java.util.UUID;

public abstract class OnValidClickListener implements View.OnClickListener {
  private static final String TAG = "OnValidClickListener";
  private static final HashMap<Object, Long> VIEW_CLICK_TIME = new HashMap<>();
  private static final int MIN_TIME_DELTA = 1000;

  public void setDelta(int delta) {
    this.delta = delta;
  }

  private int delta = 0;

  public OnValidClickListener(int delta) {
    this.delta = delta;
  }

  public OnValidClickListener() {

  }

  @Override public void onClick(View view) {
    Object tag = view.getTag();
    if (tag == null) {
      tag = UUID.randomUUID().toString();
      view.setTag(tag);
    }
    long time = 0;
    if (VIEW_CLICK_TIME.containsKey(tag)) {
      time = VIEW_CLICK_TIME.get(tag);
    }
    if (delta == 0) {
      delta = MIN_TIME_DELTA;
    }
    LogMgr.d(TAG, "onClick:" + delta);
    if (System.currentTimeMillis() - time > delta) {
      VIEW_CLICK_TIME.put(tag, System.currentTimeMillis());
      onValidClick(view);
    } else {
      LogMgr.d(TAG, "click too quick");
    }
  }

  public abstract void onValidClick(View v);
}
