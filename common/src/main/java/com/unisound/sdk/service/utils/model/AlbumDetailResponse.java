package com.unisound.sdk.service.utils.model;

import java.io.Serializable;
import java.util.List;

public class AlbumDetailResponse implements Serializable {

  private int totalPage;
  private long totalTime;
  private List<AlbumDetailData> audioList;
  private long count;
  private int errorCode;
  private int currentPage;
  private String keyWord;

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public long getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(long totalTime) {
    this.totalTime = totalTime;
  }

  public List<AlbumDetailData> getAudioList() {
    return audioList;
  }

  public void setAudioList(List<AlbumDetailData> audioList) {
    this.audioList = audioList;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public String getKeyWord() {
    return keyWord;
  }

  public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
  }
}
