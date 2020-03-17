package com.unisound.sdk.service.utils;

import android.content.Context;
import android.media.AudioManager;

public class DefaultVolumeOperator {
  private static final String TAG = "DefaultVolumeOperator";
  private AudioManager audioManager;
  private int maxVolume = 0;
  private static DefaultVolumeOperator defaultVolumeOperator;

  public static DefaultVolumeOperator getInstance() {
    if (defaultVolumeOperator == null) {
      defaultVolumeOperator = new DefaultVolumeOperator(ContextUtils.getContext());
    }
    return defaultVolumeOperator;
  }

  private DefaultVolumeOperator(Context context) {
    audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
  }

  public void setVoiceVolume(final int volume) {
    LogMgr.d(TAG, "setVoiceVolume:" + volume);
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FX_KEY_CLICK);
  }

  public int getCurrentVolume() {
    return getStreamVolume(AudioManager.STREAM_MUSIC);
  }

  public int getMaxVolume() {
    return maxVolume;
  }

  public int getMaxVolume(int streamType) {
    return audioManager.getStreamMaxVolume(streamType);
  }

  public int getStreamVolume(int streamType) {
    int volume = audioManager.getStreamVolume(streamType);
    LogMgr.d(TAG, "streamType:" + streamType + ",volume:" + volume);
    return volume;
  }

  public void setMusicStream(int volume) {
    LogMgr.d(TAG, "setMusicStream:" + volume);
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FX_KEY_CLICK);
  }

  public void setAlarmStream(int volume) {
    LogMgr.d(TAG, "setAlarmStream:" + volume);
    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume, AudioManager.FX_KEY_CLICK);
  }
}
