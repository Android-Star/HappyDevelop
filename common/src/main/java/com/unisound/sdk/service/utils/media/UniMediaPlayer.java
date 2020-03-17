package com.unisound.sdk.service.utils.media;

import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.text.TextUtils;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.NetUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UniMediaPlayer implements OnCompletionListener, OnErrorListener, OnPreparedListener {
  private static final String TAG = "UniMediaPlayer";
  private MediaPlayer mediaPlayer;
  private UniMediaPlayerState state = UniMediaPlayerState.MPS_IDLE;
  private List<IUniMediaPlayerStateListener> mediaPlayerStateListenerList = new ArrayList<>();
  public static UniMediaPlayer uniMediaPlayer = new UniMediaPlayer(ContextUtils.getApplication());
  private Application application;
  private String playTag;
  private boolean playAfterPrepared = false;
  private float volume = 1.0f;

  private UniMediaPlayer(Application application) {
    mediaPlayer = new MediaPlayer();
    mediaPlayer.setOnCompletionListener(this);
    mediaPlayer.setOnErrorListener(this);
    mediaPlayer.setOnPreparedListener(this);
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    mediaPlayer.setVolume(1.0f, 1.0f);
    this.application = application;
  }

  public static UniMediaPlayer getInstance() {
    return uniMediaPlayer;
  }

  public void addIMediaPlayerStateListener(IUniMediaPlayerStateListener listener) {
    if (listener != null) {
      if (!mediaPlayerStateListenerList.contains(listener)) {
        mediaPlayerStateListenerList.add(listener);
      }
    }
  }

  public void removeIMediaPlayerStateListener(IUniMediaPlayerStateListener listener) {
    if (listener != null) {
      if (mediaPlayerStateListenerList.contains(listener)) {
        mediaPlayerStateListenerList.remove(listener);
      }
    }
  }

  public void setVolume(float volume) {
    LogMgr.d(TAG, "setVolume:" + volume);
    if (mediaPlayer != null) {
      mediaPlayer.setVolume(volume, volume);
      this.volume = volume;
    }
  }

  public float getVolume() {
    LogMgr.d(TAG, "getVolume:" + volume);
    return volume;
  }

  public void play(String url, boolean playAfterPrepared, IUniMediaPlayerStateListener listener) {
    play(url, url, AudioManager.STREAM_MUSIC, playAfterPrepared, listener);
  }

  public void play(String url) {
    play(url, url, AudioManager.STREAM_MUSIC, true, null);
  }

  public void play(String url, IUniMediaPlayerStateListener listener) {
    play(url, url, AudioManager.STREAM_MUSIC, true, listener);
  }

  public void play(String url, String tag, int streamType, boolean playAfterPrepared,
      IUniMediaPlayerStateListener listener) {
    LogMgr.d(TAG, "play url:" + url);
    stop();
    if (TextUtils.isEmpty(url)) {
      return;
    }
    addIMediaPlayerStateListener(listener);
    playTag = tag;
    try {
      this.playAfterPrepared = playAfterPrepared;
      LogMgr.d(TAG, "reset");
      mediaPlayer.reset();
      LogMgr.d(TAG, "setLooping");
      mediaPlayer.setLooping(false);
      LogMgr.d(TAG, "setDataSource");
      mediaPlayer.setDataSource(url);
      LogMgr.d(TAG, "setAudioStreamType:" + streamType);
      mediaPlayer.setAudioStreamType(streamType);
      LogMgr.d(TAG, "prepareAsync");
      mediaPlayer.prepareAsync();
      LogMgr.d(TAG, "prepareAsync end");
      callBackPlayerState(UniMediaPlayerState.MPS_BUFFERING);
    } catch (Exception e) {
      LogMgr.e(TAG, "play url exception:" + e.toString());
      callBackPlayerState(UniMediaPlayerState.MPS_ERROR);
    }
  }

  public void play(int rawId, boolean loop, int streamType) {
    LogMgr.d(TAG, "play rawId:" + rawId);
    stop();
    playTag = String.valueOf(rawId);
    AssetFileDescriptor file =
        application.getApplicationContext().getResources().openRawResourceFd(rawId);
    try {
      this.playAfterPrepared = true;
      LogMgr.d(TAG, "reset");
      mediaPlayer.reset();
      LogMgr.d(TAG, "setLooping");
      mediaPlayer.setLooping(loop);
      LogMgr.d(TAG, "setDataSource");
      mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
      LogMgr.d(TAG, "setAudioStreamType:" + streamType);
      mediaPlayer.setAudioStreamType(streamType);
      LogMgr.d(TAG, "prepareAsync");
      mediaPlayer.prepareAsync();
      LogMgr.d(TAG, "prepareAsync end");
      callBackPlayerState(UniMediaPlayerState.MPS_BUFFERING);
    } catch (Exception e) {
      LogMgr.e(TAG, "play raw exception:" + e.toString());
      callBackPlayerState(UniMediaPlayerState.MPS_ERROR);
    } finally {
      try {
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void play(int rawId) {
    play(rawId, false, AudioManager.STREAM_MUSIC);
  }

  public void play(int rawId, IUniMediaPlayerStateListener mediaPlayerStateListener) {
    addIMediaPlayerStateListener(mediaPlayerStateListener);
    play(rawId, false, AudioManager.STREAM_MUSIC);
  }

  public void start() {
    LogMgr.d(TAG, "start state:" + state + "playTag:" + playTag);
    if (mediaPlayer != null && state == UniMediaPlayerState.MPS_PREPARED) {
      LogMgr.d(TAG, "start");
      mediaPlayer.start();
      LogMgr.d(TAG, "start end");
      callBackPlayerState(UniMediaPlayerState.MPS_PLAYING);
    }
  }

  public void stop() {
    LogMgr.d(TAG, "stop state:" + state + "playTag:" + playTag);
    this.playAfterPrepared = false;
    if (state == UniMediaPlayerState.MPS_PLAYING) {
      LogMgr.d(TAG, "pause");
      mediaPlayer.pause();
      LogMgr.d(TAG, "pause end");
    }
    if (state != UniMediaPlayerState.MPS_BUFFERING && state != UniMediaPlayerState.MPS_ERROR
        && state != UniMediaPlayerState.MPS_IDLE && state != UniMediaPlayerState.MPS_STOP) {
      LogMgr.d(TAG, "stop");
      mediaPlayer.stop();
      LogMgr.d(TAG, "stop end");
      callBackPlayerState(UniMediaPlayerState.MPS_STOP);
    }
  }

  public void stop(String tag) {
    LogMgr.d(TAG, "stop tag state:" + state + "nowTag:" + playTag + ",stopTag:" + tag);
    if (mediaPlayer != null && playTag != null && playTag.equals(tag)) {
      stop();
    }
  }

  public void stop(int rawId) {
    stop(String.valueOf(rawId));
  }

  private void callBackPlayerState(UniMediaPlayerState state) {
    LogMgr.d(TAG, "callBackPlayerState state : " + state + "tag:" + playTag);
    this.state = state;
    if (mediaPlayerStateListenerList != null) {
      for (IUniMediaPlayerStateListener mediaPlayerStateListener : mediaPlayerStateListenerList) {
        mediaPlayerStateListener.onPlayerState(state, playTag);
      }
      if (state == UniMediaPlayerState.MPS_ERROR || state == UniMediaPlayerState.MPS_STOP
          || state == UniMediaPlayerState.MPS_COMPLETE) {
        playTag = null;
      }
    }
  }

  public boolean isPlaying(String tag) {
    return this.playTag != null && this.playTag.equals(tag)
        && state == UniMediaPlayerState.MPS_PLAYING;
  }

  @Override public void onCompletion(MediaPlayer mp) {
    LogMgr.d(TAG, "onCompletion:" + playTag);
    callBackPlayerState(UniMediaPlayerState.MPS_COMPLETE);
  }

  @Override public boolean onError(MediaPlayer mp, int what, int extra) {
    LogMgr.d(TAG, "onError:" + playTag + ",what:" + what + ",extra" + extra);
    LogMgr.d(TAG, "network:" + NetUtils.isNetworkAvailable());
    callBackPlayerState(UniMediaPlayerState.MPS_ERROR);
    return false;
  }

  @Override public void onPrepared(MediaPlayer mp) {
    LogMgr.d(TAG, "onPrepared:" + playAfterPrepared);
    if (playAfterPrepared) {
      state = UniMediaPlayerState.MPS_PREPARED;
      start();
    } else {
      callBackPlayerState(UniMediaPlayerState.MPS_PREPARED);
    }
  }
}
