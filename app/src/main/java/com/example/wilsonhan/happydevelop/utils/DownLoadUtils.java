package com.example.wilsonhan.happydevelop.utils;

import android.text.TextUtils;
import com.example.wilsonhan.happydevelop.callback.DownLoadListener;
import com.example.wilsonhan.happydevelop.callback.ProgressListener;
import com.example.wilsonhan.happydevelop.net.BaseRequest;
import com.example.wilsonhan.happydevelop.net.SwitchSchedulers;
import com.unisound.sdk.service.utils.HandlerUtils;
import com.unisound.sdk.service.utils.LogMgr;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.ResponseBody;

public class DownLoadUtils {
  private static final String TAG = "DownLoadUtils";
  private static DownLoadUtils instance;
  private String filePath;
  private File downloadFile;

  public static DownLoadUtils getInstance() {
    if (instance == null) {
      synchronized (DownLoadUtils.class) {
        if (instance == null) {
          instance = new DownLoadUtils();
        }
      }
    }
    return instance;
  }

  private DownLoadUtils() {
  }

  public void downloadFile(String fileMd5, String url, DownLoadListener listener) {
    LogMgr.d(TAG, "download file url:" + url);
    if (TextUtils.isEmpty(url)) {
      return;
    }
    int index = url.lastIndexOf('/');
    if (index != -1) {
      String name = url.substring(index);
      filePath = FileUtils.DATA_APK_URL + name;
    }
    if (TextUtils.isEmpty(filePath)) {
      LogMgr.d(TAG, "download file path is null");
    }
    downloadFile = new File(filePath);
    try {
      String localFileMd5 = FileUtils.generateFileMD5(filePath);
      if (downloadFile.isFile() && downloadFile.exists() && fileMd5.equals(localFileMd5)) {
        listener.onDownLoadFinish(filePath, downloadFile);
        return;
      }
      if (!downloadFile.exists()) {
        FileUtils.createNewFile(filePath);
      }
      final long[] times = { 0 };
      BaseRequest request = new BaseRequest(new ProgressListener() {
        @Override public void update(String url, long bytesRead, long contentLength, boolean done) {
          LogMgr.d(TAG, "current progress " + bytesRead + " totalLength:" + contentLength);
          if (times[0] == 0) {
            HandlerUtils.getMainHandler().post(new Runnable() {
              @Override public void run() {
                listener.onDownLoadStart();
              }
            });
          }
          times[0]++;
          if (times[0] % 100 == 0 || bytesRead == contentLength) {
            HandlerUtils.getMainHandler().post(new Runnable() {
              @Override public void run() {
                listener.onDownLoadProgress((long) (100f * bytesRead / contentLength));
                //if (contentLength == bytesRead) {
                //listener.onDownLoadFinish(filePath,downloadFile);
                //}
              }
            });
          }
        }
      });
      request.getApiService()
          .downLoadFile(url, "bytes=" + downloadFile.length() + "-")
          .compose(SwitchSchedulers.applySchedulers())
          .subscribe(new Observer<ResponseBody>() {

            @Override public void onError(Throwable throwable) {
              if (listener != null) {
                listener.onDownLoadFair();
              }
            }

            @Override public void onComplete() {

            }

            @Override public void onSubscribe(Disposable disposable) {

            }

            @Override public void onNext(ResponseBody responseBody) {
              writeToDisk(responseBody, listener);
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void writeToDisk(ResponseBody responseBody, DownLoadListener listener) {
    OutputStream outputStream = null;
    InputStream inputStream = responseBody.byteStream();

    try {
      outputStream = new FileOutputStream(downloadFile, true);
      int len;
      byte[] buff = new byte[4096];
      while ((len = inputStream.read(buff)) != -1) {
        outputStream.write(buff, 0, len);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      listener.onDownLoadFinish(filePath, downloadFile);
    }
  }
}
