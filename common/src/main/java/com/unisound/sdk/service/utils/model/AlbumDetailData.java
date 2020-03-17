package com.unisound.sdk.service.utils.model;

import com.unisound.sdk.service.utils.nlu.AudioResult;
import java.io.Serializable;

public class AlbumDetailData implements Serializable {
  private long buyCount;
  private long code;
  private int listenFlag;
  private int audioType;
  private long episode;
  private String language;
  private long section;
  private String title;
  private long totalContentPlayTime;
  private String[] tags;
  private long playCount;
  private String dataOriginName;
  private long contentStartTime;
  private String domainName;
  private int feeFlag;
  private long dataOrigin;
  private int buyedFlag;
  private long id;
  private String category;
  private String imgUrl;
  private String[] imgUrls;
  private int resourceType; //1 视频 2 音频 3 视频和音频

  public long getBuyCount() {
    return buyCount;
  }

  public void setBuyCount(long buyCount) {
    this.buyCount = buyCount;
  }

  public long getCode() {
    return code;
  }

  public void setCode(long code) {
    this.code = code;
  }

  public int getListenFlag() {
    return listenFlag;
  }

  public void setListenFlag(int listenFlag) {
    this.listenFlag = listenFlag;
  }

  public int getAudioType() {
    return audioType;
  }

  public void setAudioType(int audioType) {
    this.audioType = audioType;
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

  public long getTotalContentPlayTime() {
    return totalContentPlayTime;
  }

  public void setTotalContentPlayTime(long totalContentPlayTime) {
    this.totalContentPlayTime = totalContentPlayTime;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
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

  public int getFeeFlag() {
    return feeFlag;
  }

  public void setFeeFlag(int feeFlag) {
    this.feeFlag = feeFlag;
  }

  public long getDataOrigin() {
    return dataOrigin;
  }

  public void setDataOrigin(long dataOrigin) {
    this.dataOrigin = dataOrigin;
  }

  public int getBuyedFlag() {
    return buyedFlag;
  }

  public void setBuyedFlag(int buyedFlag) {
    this.buyedFlag = buyedFlag;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String[] getImgUrls() {
    return imgUrls;
  }

  public void setImgUrls(String[] imgUrls) {
    this.imgUrls = imgUrls;
  }

  public int getResourceType() {
    return resourceType;
  }

  public void setResourceType(int resourceType) {
    this.resourceType = resourceType;
  }

  public AudioResult.Audio toAudio() {
    AudioResult.Audio audio = new AudioResult.Audio();
    audio.setId(id);
    audio.setTitle(title);
    audio.setImgUrl(imgUrl);
    audio.setAudioType(resourceType);
    return audio;
  }
}
