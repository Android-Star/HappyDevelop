package com.unisound.sdk.service.utils.player;

import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;
import com.unisound.sdk.service.utils.ContextUtils;
import java.io.InputStream;
import java.util.HashMap;

public class AudioTrackPlayerModeStatic {
  private static final String TAG = "EngineImpl";
  private AudioTrack audioTrack;
  private static final int STREAM_TYPE = AudioManager.STREAM_MUSIC;
  private static final int ENCODING = AudioFormat.ENCODING_PCM_16BIT;
  private static final int MODE = AudioTrack.MODE_STATIC;
  private int simpleRate;
  private int channelOut;
  private HashMap<String, byte[]> pcmDataMap = new HashMap<>();

  public AudioTrackPlayerModeStatic(int simpleRate) {
    this.simpleRate = simpleRate;
    this.channelOut = AudioFormat.CHANNEL_OUT_STEREO;
    audioTrack = new AudioTrack(STREAM_TYPE, simpleRate, channelOut, ENCODING, 300 * 1000, MODE);
  }

  public AudioTrackPlayerModeStatic(int simpleRate, int channel) {
    this.simpleRate = simpleRate;
    this.channelOut = channel;
    audioTrack = new AudioTrack(STREAM_TYPE, simpleRate, channelOut, ENCODING, 300 * 1000, MODE);
  }

  public void playAssetFile(String fileName) {
    try {
      if (pcmDataMap.get(fileName) == null) {
        AssetManager am = ContextUtils.getContext().getAssets();
        InputStream inputStream = am.open(fileName);
        int totalSize = inputStream.available();
        byte[] bytes = new byte[totalSize];
        inputStream.read(bytes);
        inputStream.close();
        pcmDataMap.put(fileName, bytes);
      }
      playBytes(pcmDataMap.get(fileName));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void playBytes(byte[] bytes) {
    if (bytes != null && bytes.length > 0) {
      stop();
      audioTrack.write(bytes, 0, bytes.length);
      if (audioTrack != null && audioTrack.getState() == AudioTrack.STATE_INITIALIZED) {
        Log.d(TAG, "play start");
        audioTrack.play();
      }
    }
  }

  public void stop() {
    if (audioTrack != null && audioTrack.getState() == AudioTrack.STATE_INITIALIZED) {
      audioTrack.stop();
    }
  }
}
