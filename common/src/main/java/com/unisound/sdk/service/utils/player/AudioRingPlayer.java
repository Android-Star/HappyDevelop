package com.unisound.sdk.service.utils.player;

import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.InputStream;

public class AudioRingPlayer {
  private static final String TAG = "AudioRingPlayer";
  private AudioTrack audioTrack;
  private static final int STREAM_TYPE = AudioManager.STREAM_MUSIC;
  private static final int ENCODING = AudioFormat.ENCODING_PCM_16BIT;
  private static final int MODE = AudioTrack.MODE_STREAM;
  private int bufferSizeInBytes;
  private PlayThread playThread;
  private String fileName;
  private int totalSize;
  private int simpleRate;
  private int channelOut;

  public static AudioRingPlayer getInstance() {
    return audioRingPlayer;
  }

  public static AudioRingPlayer audioRingPlayer = new AudioRingPlayer(22050);

  private AudioRingPlayer(int simpleRate) {
    this.simpleRate = simpleRate;
    this.channelOut = AudioFormat.CHANNEL_OUT_STEREO;
    initBuffer();
  }

  private void initBuffer() {
    bufferSizeInBytes = AudioTrack.getMinBufferSize(simpleRate, channelOut, ENCODING);
    LogMgr.d(TAG, "bufferSizeInBytes:" + bufferSizeInBytes);
  }

  public void playAssetFile(String fileName) {
    try {
      this.fileName = fileName;
      AssetManager am = ContextUtils.getContext().getAssets();
      InputStream inputStream = am.open(fileName);
      playInputStream(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
      onPlayError();
    }
  }

  private void playInputStream(InputStream inputStream) throws Exception {
    stop();
    audioTrack =
        new AudioTrack(STREAM_TYPE, simpleRate, channelOut, ENCODING, bufferSizeInBytes, MODE);
    totalSize = inputStream.available();
    byte[] bytes = new byte[totalSize];
    inputStream.read(bytes);
    inputStream.close();
    playThread = new PlayThread(bytes, this);
    playThread.start();
    resume();
  }

  public void playAssetFile(String fileName, int streamType) {
    try {
      this.fileName = fileName;
      AssetManager am = ContextUtils.getContext().getAssets();
      InputStream inputStream = am.open(fileName);
      playInputStream(inputStream, streamType);
    } catch (Exception e) {
      e.printStackTrace();
      onPlayError();
    }
  }

  private void playInputStream(InputStream inputStream, int streamType) throws Exception {
    stop();
    audioTrack =
        new AudioTrack(streamType, simpleRate, channelOut, ENCODING, bufferSizeInBytes, MODE);
    totalSize = inputStream.available();
    byte[] bytes = new byte[totalSize];
    inputStream.read(bytes);
    inputStream.close();
    playThread = new PlayThread(bytes, this);
    playThread.start();
    resume();
  }

  public void pause() {
    if (audioTrack != null) {
      audioTrack.pause();
      onPlayPause();
    }
  }

  public void resume() {
    if (audioTrack != null) {
      audioTrack.play();
      onPlayStart();
    }
  }

  public void stop() {
    if (playThread != null) {
      playThread.cancel();
      playThread = null;
    }
    if (audioTrack != null) {
      audioTrack.release();
      audioTrack = null;
    }
  }

  public static class PlayThread extends Thread {
    private boolean isRun = true;
    private byte[] bytes;
    private int currentSize;
    private AudioRingPlayer audioTrackPlayer;

    public PlayThread(byte[] bytes, AudioRingPlayer audioTrackPlayer) {
      this.bytes = bytes;
      this.audioTrackPlayer = audioTrackPlayer;
      this.currentSize = 0;
    }

    @Override public void run() {
      LogMgr.d(TAG, "audioTrack.play");
      while (isRun) {
        if (audioTrackPlayer.audioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
          try {
            int read = Math.min(audioTrackPlayer.bufferSizeInBytes, bytes.length - currentSize);
            if (read > 0) {
              audioTrackPlayer.audioTrack.write(bytes, currentSize, read);
              currentSize += read;
            } else {
              audioTrackPlayer.pause();
              audioTrackPlayer.onPlayEnd();
            }
          } catch (Exception e) {
            LogMgr.d(TAG, "audioTrack.Exception:" + e.toString());
            isRun = false;
            e.printStackTrace();
            audioTrackPlayer.onPlayError();
          }
        } else {
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      audioTrackPlayer.playThread = null;
    }

    private void cancel() {
      try {
        isRun = false;
        join();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void onPlayError() {
    LogMgr.d(TAG, "onPlayError:" + fileName);
  }

  private void onPlayEnd() {
    LogMgr.d(TAG, "onPlayEnd:" + fileName);
  }

  private void onPlayStart() {
    LogMgr.d(TAG, "onPlayStart:" + fileName);
  }

  private void onPlayPause() {
    LogMgr.d(TAG, "onPlayPause:" + fileName);
  }
}
