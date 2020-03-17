package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MusicResult implements Result, Serializable {
  private int count;

  private List<Music> musicinfo;

  private String updateTime;

  private int errorCode;

  private String dataSourceName;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<Music> getMusicinfo() {
    return musicinfo;
  }

  public void setMusicinfo(List<Music> musicinfo) {
    this.musicinfo = musicinfo;
  }

  public List<String> getMusicUrls() {
    List<String> musicUrls = new ArrayList<>();
    if (musicinfo != null && musicinfo.size() > 0) {
      for (Music music : musicinfo) {
        musicUrls.add(music.getUrl());
      }
    }
    return musicUrls;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getDataSourceName() {
    return dataSourceName;
  }

  public void setDataSourceName(String dataSourceName) {
    this.dataSourceName = dataSourceName;
  }

  public static class Music implements Serializable {

    boolean isCollected;
    private String id;
    private String title;
    private String artist;
    private String album;
    private String url;
    private int duration;
    private int errorCode;
    private String imgUrl;
    private String hdImgUrl;
    private String mLyric;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getArtist() {
      return artist;
    }

    public void setArtist(String artist) {
      this.artist = artist;
    }

    public String getAlbum() {
      return album;
    }

    public void setAlbum(String album) {
      this.album = album;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
    }

    public int getErrorCode() {
      return errorCode;
    }

    public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
    }

    public String getImgUrl() {
      return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
    }

    public String getHdImgUrl() {
      return hdImgUrl;
    }

    public void setHdImgUrl(String hdImgUrl) {
      this.hdImgUrl = hdImgUrl;
    }

    public String getmLyric() {
      return mLyric;
    }

    public void setmLyric(String mLyric) {
      this.mLyric = mLyric;
    }

    public boolean isCollected() {
      return isCollected;
    }

    public void setCollected(boolean isCollected) {
      this.isCollected = isCollected;
    }
  }
}

