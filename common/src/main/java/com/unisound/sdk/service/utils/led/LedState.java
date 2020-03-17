package com.unisound.sdk.service.utils.led;

import java.util.HashMap;

public class LedState {
  private LedState() {

  }

  public static final int MESSAGE_NOT_READ = 1;
  public static final int SEARCH = 1;
  public static final int BODY_TOUCH = 2;
  public static final int CALL_IN = 3;
  public static final int DISTANCE_CLOSE = 4;

  private static HashMap<Integer, Integer> stateValue = new HashMap<>();

  static {
    stateValue.put(MESSAGE_NOT_READ, 0);
    stateValue.put(SEARCH, 0);
    stateValue.put(BODY_TOUCH, 0);
    stateValue.put(CALL_IN, Integer.MAX_VALUE);
    stateValue.put(DISTANCE_CLOSE, Integer.MAX_VALUE);
  }

  public static int getPriority(int state) {
    if (stateValue.containsKey(state)) {
      return stateValue.get(state);
    }
    return -1;
  }
}
