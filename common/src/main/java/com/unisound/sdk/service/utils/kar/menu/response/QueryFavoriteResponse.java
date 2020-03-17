package com.unisound.sdk.service.utils.kar.menu.response;

import com.unisound.sdk.service.utils.model.AlbumDetailData;
import com.unisound.sdk.service.utils.nlu.AudioResult;
import java.io.Serializable;

public class QueryFavoriteResponse implements Serializable {

  private long id;
  private int resourceType;
  private long resourceId;
  private String resourceName;
  private String imgUrl;
  private String createTime;
  private long deviceId;
  private long uid;
  private boolean exist = true;
  private String dataType;
  private String dataSourceCode;
  private long dataResourceType;

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public String getDataSourceCode() {
    return dataSourceCode;
  }

  public void setDataSourceCode(String dataSourceCode) {
    this.dataSourceCode = dataSourceCode;
  }

  public long getDataResourceType() {
    return dataResourceType;
  }

  public void setDataResourceType(long dataResourceType) {
    this.dataResourceType = dataResourceType;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getResourceType() {
    return resourceType;
  }

  public void setResourceType(int resourceType) {
    this.resourceType = resourceType;
  }

  public long getResourceId() {
    return resourceId;
  }

  public void setResourceId(long resourceId) {
    this.resourceId = resourceId;
  }

  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  public boolean isExist() {
    return exist;
  }

  public void setExist(boolean exist) {
    this.exist = exist;
  }

  public AudioResult.Audio toAudio() {
    AudioResult.Audio audio = new AudioResult.Audio();
    audio.setId(resourceId);
    audio.setTitle(resourceName);
    audio.setImgUrl(imgUrl);
    audio.setAudioType(2);
    audio.setDataSourceCode(dataSourceCode);
    audio.setDataType(dataType);
    return audio;
  }

  public AlbumDetailData toVideo() {
    AlbumDetailData video = new AlbumDetailData();
    video.setId(resourceId);
    video.setTitle(resourceName);
    video.setImgUrl(imgUrl);
    video.setAudioType(1);
    return video;
  }
}
