package com.unisound.sdk.service.utils.model;

import android.graphics.Bitmap;
import java.io.Serializable;

public class Media implements Serializable {
  private String title;
  private String modifyTime;
  private Bitmap thumbnail;
  private String filePath;
  private boolean isChecked;
  private long fileSize;
  private long fileId;
  private int fileType; //0 图片 1 视频
  private String timeStamps; //时间戳
  private long duration;

  public static final int MEDIA_TYPE_PHOTO = 0;

  public static final int MEDIA_TYPE_VIDEO = 1;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(String modifyTime) {
    this.modifyTime = modifyTime;
  }

  public Bitmap getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Bitmap thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public boolean isChecked() {
    return isChecked;
  }

  public void setChecked(boolean checked) {
    isChecked = checked;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public long getFileId() {
    return fileId;
  }

  public void setFileId(long fileId) {
    this.fileId = fileId;
  }

  public int getFileType() {
    return fileType;
  }

  public void setFileType(int fileType) {
    this.fileType = fileType;
  }

  public String getTimeStamps() {
    return timeStamps;
  }

  public void setTimeStamps(String timeStamps) {
    this.timeStamps = timeStamps;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }
}
