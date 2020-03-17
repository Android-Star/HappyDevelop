package com.unisound.sdk.service.utils.callback;

import com.unisound.sdk.asr.EngineState;

public interface EngineStateCallBack {
  void engineStateChange(EngineState state);
}
