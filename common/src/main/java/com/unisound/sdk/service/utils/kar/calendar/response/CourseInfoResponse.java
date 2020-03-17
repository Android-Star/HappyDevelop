package com.unisound.sdk.service.utils.kar.calendar.response;

import com.unisound.sdk.service.utils.nlu.AudioResult;
import java.io.Serializable;

public class CourseInfoResponse implements Serializable {
  private long courseId;
  private int resourceType;
  private long resourceId;
  private String homePageUrl;
  private int sort;
  private long id;

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
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

  public String getHomePageUrl() {
    return homePageUrl;
  }

  public void setHomePageUrl(String homePageUrl) {
    this.homePageUrl = homePageUrl;
  }

  public int getSort() {
    return sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public AudioResult.Audio toAudio() {
    AudioResult.Audio audio = new AudioResult.Audio();
    audio.setId(resourceId);
    audio.setImgUrl(homePageUrl);
    audio.setAudioType(2);
    return audio;
  }
}
