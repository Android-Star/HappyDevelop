package com.unisound.sdk.service.utils.player;

import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class AudioTrackPlayer {
  private static final String TAG = "AudioTrackPlayer";
  private AudioTrack audioTrack;
  private static final int STREAM_TYPE = AudioManager.STREAM_MUSIC;
  private static final int ENCODING = AudioFormat.ENCODING_PCM_16BIT;
  private static final int MODE = AudioTrack.MODE_STREAM;
  private int bufferSizeInBytes;
  private PlayThread playThread;
  private String fileName;
  private AudioTrackPlayCallBack audioTrackPlayCallBack;
  private Handler handler = new Handler(Looper.getMainLooper());
  private int totalSize;
  private int totalTime;
  private int simpleRate;
  private int channelOut;
  private int multiple;
  private boolean playAssets = false;

  public void setAudioTrackPlayCallBack(AudioTrackPlayCallBack audioTrackPlayCallBack) {
    this.audioTrackPlayCallBack = audioTrackPlayCallBack;
  }

  public AudioTrackPlayer(int simpleRate) {
    this.simpleRate = simpleRate;
    this.channelOut = AudioFormat.CHANNEL_OUT_MONO;
    this.multiple = 1;
    initBuffer();
  }

  public AudioTrackPlayer(int simpleRate, int channel) {
    this.simpleRate = simpleRate;
    this.channelOut = channel;
    this.multiple = 1;
    initBuffer();
  }

  public AudioTrackPlayer(int simpleRate, boolean useMultiple) {
    this.simpleRate = simpleRate;
    this.channelOut = AudioFormat.CHANNEL_OUT_MONO;
    if (useMultiple) {
      this.multiple = 4;
    } else {
      this.multiple = 1;
    }
    initBuffer();
  }

  public AudioTrackPlayer(int simpleRate, int channel, boolean useMultiple) {
    this.simpleRate = simpleRate;
    this.channelOut = channel;
    if (useMultiple) {
      this.multiple = 4;
    } else {
      this.multiple = 1;
    }
    initBuffer();
  }

  private void initBuffer() {
    bufferSizeInBytes = AudioTrack.getMinBufferSize(simpleRate, channelOut, ENCODING);
    LogMgr.d(TAG, "bufferSizeInBytes:" + bufferSizeInBytes);
  }

  public void playAssetFile(String fileName) {
    try {
      this.playAssets = true;
      this.fileName = fileName;
      AssetManager am = ContextUtils.getContext().getAssets();
      InputStream inputStream = am.open("pcm/" + fileName);
      playInputStream(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
      onPlayError();
    }
  }

  public void playSdcardFile(String path) {
    try {
      this.playAssets = false;
      this.fileName = path.substring(0, path.lastIndexOf(".") - 1);
      File file = new File(path);
      InputStream inputStream = new FileInputStream(file);
      playInputStream(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setMultiple(int multiple) {
    this.multiple = multiple;
  }

  private void playInputStream(InputStream inputStream) throws Exception {
    stop();
    audioTrack =
        new AudioTrack(STREAM_TYPE, simpleRate, channelOut, ENCODING, bufferSizeInBytes, MODE);
    totalSize = inputStream.available();
    totalTime = calcTime(totalSize, simpleRate);
    byte[] bytes = new byte[totalSize];
    inputStream.read(bytes);
    inputStream.close();
    playThread = new PlayThread(bytes, this);
    playThread.start();
    resume();
  }

  private void audioTrackPause() {
    if (audioTrack != null) {
      audioTrack.pause();
    }
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
    onStopPlay();
    if (audioTrack != null) {
      audioTrack.release();
      audioTrack = null;
    }
  }

  public static class PlayThread extends Thread {
    private boolean isRun = true;
    private float seekValue = -1.0f;
    private byte[] bytes;
    private int currentSize;
    private AudioTrackPlayer audioTrackPlayer;
    private short value;
    private int data;

    public PlayThread(byte[] bytes2, AudioTrackPlayer audioTrackPlayer) {
      this.bytes = bytes2;
      this.audioTrackPlayer = audioTrackPlayer;
      this.currentSize = 0;
      if (audioTrackPlayer.multiple != 1 && !audioTrackPlayer.playAssets) {
        for (int i = 0; i < bytes.length; i += 2) {
          value = (short) (((bytes[i + 1] & 0xff) << 8) | (bytes[i] & 0xff));
          data = value * audioTrackPlayer.multiple;
          if (data > Short.MAX_VALUE) {
            data = Short.MAX_VALUE;
          }
          if (data < Short.MIN_VALUE) {
            data = Short.MIN_VALUE;
          }
          value = (short) data;
          bytes[i + 1] = (byte) ((value & 0xff00) >> 8);
          bytes[i] = (byte) (value & 0xff);
        }
      }
    }

    public void seek(float value) {
      seekValue = value;
    }

    @Override public void run() {
      LogMgr.d(TAG, "audioTrack.play");
      while (isRun) {
        if (audioTrackPlayer.audioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
          try {
            if (seekValue >= 0) {
              currentSize = (int) (bytes.length * seekValue);
              seekValue = -1.0f;
              currentSize = currentSize - currentSize % 2;
            }
            int read = Math.min(audioTrackPlayer.bufferSizeInBytes, bytes.length - currentSize);
            if (read > 0) {
              audioTrackPlayer.audioTrack.write(bytes, currentSize, read);
              currentSize += read;
              audioTrackPlayer.onPlayDuration(currentSize, audioTrackPlayer.totalSize);
            } else {
              audioTrackPlayer.audioTrackPause();
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
    handler.post(new Runnable() {
      @Override public void run() {
        if (audioTrackPlayCallBack != null) {
          audioTrackPlayCallBack.playError(fileName);
        }
      }
    });
  }

  private void onPlayEnd() {
    LogMgr.d(TAG, "onPlayEnd:" + fileName);
    handler.post(new Runnable() {
      @Override public void run() {
        if (audioTrackPlayCallBack != null) {
          audioTrackPlayCallBack.playEnd(fileName);
        }
      }
    });
  }

  private void onPlayStart() {
    LogMgr.d(TAG, "onPlayStart:" + fileName);
    handler.post(new Runnable() {
      @Override public void run() {
        if (audioTrackPlayCallBack != null) {
          audioTrackPlayCallBack.playStart(fileName);
        }
      }
    });
  }

  private void onPlayPause() {
    LogMgr.d(TAG, "onPlayPause:" + fileName);
    handler.post(new Runnable() {
      @Override public void run() {
        if (audioTrackPlayCallBack != null) {
          audioTrackPlayCallBack.playPause(fileName);
        }
      }
    });
  }

  private void onPlayDuration(final int duration, final int total) {
    final int currentTime = calcTime(duration, simpleRate);
    handler.post(new Runnable() {
      @Override public void run() {
        if (audioTrackPlayCallBack != null) {
          audioTrackPlayCallBack.playDuration(currentTime, totalTime, fileName);
        }
      }
    });
  }

  private void onStopPlay() {
    LogMgr.d(TAG, "onStopPlay:" + fileName);
    handler.post(new Runnable() {
      @Override public void run() {
        if (audioTrackPlayCallBack != null) {
          audioTrackPlayCallBack.stopPlay(fileName);
          handler.removeCallbacksAndMessages(null);
        }
      }
    });
  }

  public static int calcTime(long size, int simpleRate) {
    return (int) ((1000 * size) / (simpleRate * 1 * (16 / 8)));
  }

  public void seek(float value) {
    if (value < 0) {
      value = 0;
    }
    if (value > 1.0f) {
      value = 1.0f;
    }
    if (playThread != null) {
      playThread.seek(value);
      resume();
    }
  }

  public static int calcTime(String fileName, int simpleRate) {
    try {
      File file = new File(fileName);
      InputStream inputStream = new FileInputStream(file);
      int size = inputStream.available();
      inputStream.close();
      return calcTime(size, simpleRate);
    } catch (Exception e) {
      return 0;
    }
  }
}
