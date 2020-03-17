package com.unisound.sdk.service.utils.music;

import java.io.Serializable;
import java.util.List;

public class ActualAudio implements Serializable {
  private long totalTime;
  private long count;
  private long errorCode;
  private List<AudioListBean> audioList;

  public long getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(long totalTime) {
    this.totalTime = totalTime;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public long getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(long errorCode) {
    this.errorCode = errorCode;
  }

  public List<AudioListBean> getAudioList() {
    return audioList;
  }

  public void setAudioList(List<AudioListBean> audioList) {
    this.audioList = audioList;
  }

  public static class AudioListBean implements Serializable {
    private long code;
    private long listenFlag;
    private String artist;
    private String displayName;
    private long audioType;
    private String description;
    private long bitrate;
    private long episode;
    private String language;
    private long section;
    private String title;
    private long frequency;
    private long feeFlag;
    private long dataOrigin;
    private long buyedFlag;
    private long id;
    private String author;
    private long totalContentPlayTime;
    private String url;
    private String imgUrl;
    private long playCount;
    private String dataOriginName;
    private long contentStartTime;
    private String domainName;
    private String category;
    private VideoInfo videoInfo;

    public long getCode() {
      return code;
    }

    public void setCode(long code) {
      this.code = code;
    }

    public long getListenFlag() {
      return listenFlag;
    }

    public void setListenFlag(long listenFlag) {
      this.listenFlag = listenFlag;
    }

    public String getArtist() {
      return artist;
    }

    public void setArtist(String artist) {
      this.artist = artist;
    }

    public String getDisplayName() {
      return displayName;
    }

    public void setDisplayName(String displayName) {
      this.displayName = displayName;
    }

    public long getAudioType() {
      return audioType;
    }

    public void setAudioType(long audioType) {
      this.audioType = audioType;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public long getBitrate() {
      return bitrate;
    }

    public void setBitrate(long bitrate) {
      this.bitrate = bitrate;
    }

    public long getEpisode() {
      return episode;
    }

    public void setEpisode(long episode) {
      this.episode = episode;
    }

    public String getLanguage() {
      return language;
    }

    public void setLanguage(String language) {
      this.language = language;
    }

    public long getSection() {
      return section;
    }

    public void setSection(long section) {
      this.section = section;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public long getFrequency() {
      return frequency;
    }

    public void setFrequency(long frequency) {
      this.frequency = frequency;
    }

    public long getFeeFlag() {
      return feeFlag;
    }

    public void setFeeFlag(long feeFlag) {
      this.feeFlag = feeFlag;
    }

    public long getDataOrigin() {
      return dataOrigin;
    }

    public void setDataOrigin(long dataOrigin) {
      this.dataOrigin = dataOrigin;
    }

    public long getBuyedFlag() {
      return buyedFlag;
    }

    public void setBuyedFlag(long buyedFlag) {
      this.buyedFlag = buyedFlag;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public long getTotalContentPlayTime() {
      return totalContentPlayTime;
    }

    public void setTotalContentPlayTime(long totalContentPlayTime) {
      this.totalContentPlayTime = totalContentPlayTime;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getImgUrl() {
      return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
    }

    public long getPlayCount() {
      return playCount;
    }

    public void setPlayCount(long playCount) {
      this.playCount = playCount;
    }

    public String getDataOriginName() {
      return dataOriginName;
    }

    public void setDataOriginName(String dataOriginName) {
      this.dataOriginName = dataOriginName;
    }

    public long getContentStartTime() {
      return contentStartTime;
    }

    public void setContentStartTime(long contentStartTime) {
      this.contentStartTime = contentStartTime;
    }

    public String getDomainName() {
      return domainName;
    }

    public void setDomainName(String domainName) {
      this.domainName = domainName;
    }

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public VideoInfo getVideoInfo() {
      return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
      this.videoInfo = videoInfo;
    }
  }

  public static class VideoInfo implements Serializable {
    private String url;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }
}
