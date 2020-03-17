package com.unisound.sdk.service.utils.music;

import android.util.Log;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.util.ArrayList;
import java.util.List;

public class ExoPlayStateListener implements Player.EventListener {
  private static final String TAG = "ExoPlayStateListener";
  private List<PlayStateListener> playStateListenerList = new ArrayList<>();
  private PlayState playState;
  private String playTag;

  public void setPlayTag(String playTag) {
    this.playTag = playTag;
  }

  public void addPlayStateListener(PlayStateListener playStateListener) {
    if (!playStateListenerList.contains(playStateListener)) {
      playStateListenerList.add(playStateListener);
    }
  }

  public void removePlayStateListener(PlayStateListener playStateListener) {
    playStateListenerList.remove(playStateListener);
  }

  @Override public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

  }

  @Override
  public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

  }

  @Override public void onLoadingChanged(boolean isLoading) {

  }

  @Override public void onPlayerStateChanged(boolean playWhenReady, int state) {
    Log.d(TAG, "playWhenReady:" + playWhenReady + "state:" + state);
    switch (state) {
      case MusicExoPlayer.STATE_BUFFERING:
        playState = PlayState.STATE_BUFFERING;
        break;
      case MusicExoPlayer.STATE_READY:
        playState = PlayState.STATE_PREPARED;
        if (playWhenReady) {
          playState = PlayState.STATE_PLAYING;
        }
        break;
      case MusicExoPlayer.STATE_ENDED:
        playState = PlayState.STATE_ENDED;
        break;
      case MusicExoPlayer.STATE_ERROR:
        playState = PlayState.STATE_IDLE;
        break;
      default:
        playState = PlayState.STATE_ERROR;
        break;
    }
    onStateChanged(playState, playTag);
  }

  @Override public void onRepeatModeChanged(int repeatMode) {
    Log.d(TAG, "onRepeatModeChanged");
  }

  @Override public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
    Log.d(TAG, "onShuffleModeEnabledChanged");
  }

  @Override public void onPlayerError(ExoPlaybackException error) {
    Log.d(TAG, "onPlayerError");
  }

  @Override public void onPositionDiscontinuity(int reason) {
    Log.d(TAG, "onPositionDiscontinuity");
  }

  @Override public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    Log.d(TAG, "onPlaybackParametersChanged");
  }

  @Override public void onSeekProcessed() {
    Log.d(TAG, "onSeekProcessed");
  }

  public PlayState getPlayState() {
    return playState;
  }

  public void onStateChanged(PlayState playState, String playTag) {
    this.playState = playState;
    Log.d(TAG, "playState:" + playState);
    if (playStateListenerList != null && playStateListenerList.size() > 0) {
      for (PlayStateListener playStateListener : playStateListenerList) {
        playStateListener.onPlayStateChanged(playState, playTag);
      }
    }
  }

}
