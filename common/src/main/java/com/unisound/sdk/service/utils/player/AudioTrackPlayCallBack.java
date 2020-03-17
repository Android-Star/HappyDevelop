package com.unisound.sdk.service.utils.player;

public interface AudioTrackPlayCallBack {
  void playError(String fileName);

  void playStart(String fileName);

  void playPause(String fileName);

  void playEnd(String fileName);

  void stopPlay(String fileName);

  void playDuration(int duration, int total, String file);
}
