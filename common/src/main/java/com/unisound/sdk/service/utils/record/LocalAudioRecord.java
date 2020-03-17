package com.unisound.sdk.service.utils.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class LocalAudioRecord {
  private static final String TAG = "LocalAudioRecord";
  public static final String TEMP_SD_CARD_PATH =
      Environment.getExternalStorageDirectory() + File.separator + "TempRecord" + File.separator;
  private AudioRecord audioRecord;
  private int simpleRate;
  private static final int CHANNEL = AudioFormat.CHANNEL_IN_MONO;
  private static final int ENCODING = AudioFormat.ENCODING_PCM_16BIT;
  private int multiple = 1;
  private static int bufferSizeInBytes;
  private String filePath;
  private RecordThread recordThread;
  private RecordCallBack recordCallBack;
  private Handler handler = new Handler(Looper.getMainLooper());
  private int data;
  private short value;
  private String fileName = "";

  public LocalAudioRecord(int simpleRate, int multiple) {
    this.simpleRate = simpleRate;
    this.multiple = multiple;
  }

  public LocalAudioRecord(int simpleRate) {
    this.simpleRate = simpleRate;
  }

  public void setRecordCallBack(RecordCallBack recordCallBack) {
    this.recordCallBack = recordCallBack;
  }

  static {
    File file = new File(TEMP_SD_CARD_PATH + "index");
    if (!file.exists()) {
      boolean result = file.mkdirs();
      if (!result) {
        throw new RuntimeException("mkdir exception");
      }
    }
  }

  public void stopRecord() {
    LogMgr.d(TAG, "stopRecord");
    if (recordThread != null) {
      recordThread.cancel();
    }
    handler.removeCallbacksAndMessages(null);
    if (audioRecord != null) {
      if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {
        audioRecord.stop();
      }
      audioRecord.release();
      audioRecord = null;
    }
  }

  public void pauseRecord() {
    LogMgr.d(TAG, "pauseRecord");
    stopRecord();
  }

  public boolean isPause() {
    return recordThread == null;
  }

  public void resumeRecord() {
    LogMgr.d(TAG, "resumeRecord");
    startRecord(fileName, true);
  }

  public void deleteAllFiles() {
    File file = new File(TEMP_SD_CARD_PATH);
    File[] files = file.listFiles();
    if (files != null) {
      for (File file1 : files) {
        if (file1.isFile()) {
          file1.delete();
        }
      }
    }
  }

  public boolean startRecord(String fileName) {
    LogMgr.d(TAG, "startRecord:" + fileName);
    return startRecord(fileName, false);
  }

  public boolean startRecord(String fileName, boolean append) {
    LogMgr.d(TAG, "startRecord:" + append + ",fileName:" + fileName);
    this.fileName = fileName;
    try {
      stopRecord();
      if (audioRecord == null) {
        bufferSizeInBytes = AudioRecord.getMinBufferSize(this.simpleRate, CHANNEL, ENCODING);
        LogMgr.d(TAG, "bufferSizeInBytes:" + bufferSizeInBytes);
        audioRecord =
            new AudioRecord(MediaRecorder.AudioSource.DEFAULT, simpleRate, CHANNEL, ENCODING,
                bufferSizeInBytes);
      }
      filePath = TEMP_SD_CARD_PATH + fileName;
      File file = new File(filePath);
      if (file.exists() && !append) {
        file.delete();
        file.createNewFile();
      }
      if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {
        audioRecord.startRecording();
        recordThread = new RecordThread(filePath, append);
        recordThread.start();
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  public class RecordThread extends Thread {
    private boolean isRun = true;
    private OutputStream outputStream;
    private long size;
    private byte[] buffer = new byte[1200];

    public RecordThread(String path, boolean append) {
      try {
        File file = new File(path);
        outputStream = new FileOutputStream(path, append);
        if (append && file.exists() && file.isFile()) {
          size = file.length();
        } else {
          size = 0;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Override public void run() {
      while (isRun) {
        int read = LocalAudioRecord.this.audioRecord.read(buffer, 0, buffer.length);
        if (read > 0) {
          try {
            if (multiple != 1) {
              for (int i = 0; i < read; i += 2) {
                value = (short) (((buffer[i + 1] & 0xff) << 8) | (buffer[i] & 0xff));
                data = value * multiple;
                if (data > Short.MAX_VALUE) {
                  data = Short.MAX_VALUE;
                }
                if (data < Short.MIN_VALUE) {
                  data = Short.MIN_VALUE;
                }
                value = (short) data;
                buffer[i + 1] = (byte) ((value & 0xff00) >> 8);
                buffer[i] = (byte) (value & 0xff);
              }
            }
            outputStream.write(buffer, 0, read);
            size += buffer.length;
            float volume = calcVolume(buffer, read);
            recordCallBack(size, volume);
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          LogMgr.e(TAG, "read error:" + read);
          try {
            Thread.sleep(20);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      if (outputStream != null) {
        try {
          outputStream.flush();
          outputStream.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      LocalAudioRecord.this.recordThread = null;
    }

    public void cancel() {
      try {
        isRun = false;
        join();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private float calcVolume(byte[] buffer, int length) {
    if (length % 2 != 0) {
      return 0;
    }
    long v = 0;
    for (int i = 0; i < length; i += 2) {
      short data = (short) (((buffer[i + 1] & 0xff) << 8) | (buffer[i] & 0xff));
      v += data * data;
    }
    float mean = (1.0f * v) / (length / 2);
    float volume = (float) (10 * Math.log10(mean));
    return volume;
  }

  public int getSimpleRate() {
    return simpleRate;
  }

  public void recordCallBack(final long size, final float volume) {
    handler.post(new Runnable() {
      @Override public void run() {
        if (recordCallBack != null) {
          recordCallBack.recordCallBack(size, simpleRate, volume);
        }
      }
    });
  }
}
