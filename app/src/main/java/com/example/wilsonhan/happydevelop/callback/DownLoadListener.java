package com.example.wilsonhan.happydevelop.callback;

import java.io.File;

public interface DownLoadListener {
  void onDownLoadStart();

  void onDownLoadProgress(long currentLength);

  void onDownLoadFinish(String filePath, File downloadFile);

  void onDownLoadFair();
}
