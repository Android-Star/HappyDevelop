package com.example.wilsonhan.happydevelop.callback;

public interface ProgressListener {
  void update(String url, long bytesRead, long contentLength, boolean done);
}
