package com.example.wilsonhan.happydevelop.utils;

import com.example.wilsonhan.happydevelop.bean.EventBean;
import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {

  private EventBusUtils() {
  }

  public static void register(Object subscriber) {
    EventBus.getDefault().register(subscriber);
  }

  public static void unregister(Object subscriber) {
    EventBus.getDefault().unregister(subscriber);
  }

  public static void sendEvent(EventBean event) {
    EventBus.getDefault().post(event);
  }

  public static void sendStickyEvent(EventBean event) {
    EventBus.getDefault().postSticky(event);
  }
}
