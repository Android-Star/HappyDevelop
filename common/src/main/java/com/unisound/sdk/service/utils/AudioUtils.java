package com.unisound.sdk.service.utils;

import android.content.Context;
import android.media.AudioManager;
import com.unisound.sdk.service.utils.music.MusicExoPlayer;
import com.unisound.sdk.service.utils.player.AudioTrackPlayer;
import java.util.ArrayList;
import java.util.List;

public class AudioUtils {
  private static final String TAG = "AudioUtils";
  private static boolean required = false;
  private static List<AudioTrackPlayer> audioTrackPlayerList = new ArrayList<>();

  private AudioUtils() {

  }

  public static void addAudioTrackPlayer(AudioTrackPlayer audioTrackPlayer) {
    if (audioTrackPlayerList != null && !audioTrackPlayerList.contains(audioTrackPlayer)) {
      audioTrackPlayerList.add(audioTrackPlayer);
    }
  }

  public static void removeAudioTrackPlayer(AudioTrackPlayer audioTrackPlayer) {
    if (audioTrackPlayerList != null && audioTrackPlayerList.contains(audioTrackPlayer)) {
      audioTrackPlayerList.remove(audioTrackPlayer);
    }
  }

  public static int requestAudioFocus() {
    int result = AudioManager.AUDIOFOCUS_REQUEST_FAILED;
    LogMgr.d(TAG, "requestAudioFocus required:" + required);
    if (!required) {
      AudioManager audioManager =
          (AudioManager) ContextUtils.getContext().getSystemService(Context.AUDIO_SERVICE);

      result = audioManager.requestAudioFocus(audioFocusChange, AudioManager.STREAM_MUSIC,
          AudioManager.AUDIOFOCUS_GAIN);
      if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
        required = true;
      }
    }
    LogMgr.d(TAG, "requestAudioFocus:" + result);
    return result;
  }

  public static int abandonAudioFocus() {
    int result = AudioManager.AUDIOFOCUS_REQUEST_FAILED;
    LogMgr.d(TAG, "abandonAudioFocus required:" + required);
    if (required) {
      AudioManager audioManager =
          (AudioManager) ContextUtils.getContext().getSystemService(Context.AUDIO_SERVICE);
      result = audioManager.abandonAudioFocus(audioFocusChange);
      if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
        required = false;
      }
      LogMgr.d(TAG, "abandonAudioFocus:" + result);
    }
    return result;
  }

  private static AudioManager.OnAudioFocusChangeListener audioFocusChange =
      new AudioManager.OnAudioFocusChangeListener() {
        @Override public void onAudioFocusChange(int focusChange) {
          switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
              LogMgr.d(TAG, "AUDIOFOCUS_LOSS");
              required = false;
              if (MusicExoPlayer.getInstance().isPlaying()) {
                MusicExoPlayer.getInstance().handPause();
              }
              if (audioTrackPlayerList != null) {
                for (AudioTrackPlayer audioTrackPlayer : audioTrackPlayerList) {
                  audioTrackPlayer.pause();
                }
              }
              break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
              LogMgr.d(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
              required = false;
              break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
              LogMgr.d(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
              required = false;
              break;
            case AudioManager.AUDIOFOCUS_GAIN:
              LogMgr.d(TAG, "AUDIOFOCUS_GAIN");
              required = true;
              break;
            default:
              break;
          }
        }
      };
}
