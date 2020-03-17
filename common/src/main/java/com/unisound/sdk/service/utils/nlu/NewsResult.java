package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsResult implements Result, Serializable {

  private int count;
  private int errorCode;
  private String searchType;
  private String dataSourceName;
  private int totalTime;
  private List<NewsBean> news;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getSearchType() {
    return searchType;
  }

  public void setSearchType(String searchType) {
    this.searchType = searchType;
  }

  public String getDataSourceName() {
    return dataSourceName;
  }

  public void setDataSourceName(String dataSourceName) {
    this.dataSourceName = dataSourceName;
  }

  public int getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(int totalTime) {
    this.totalTime = totalTime;
  }

  public List<NewsBean> getNews() {
    return news;
  }

  public void setNews(List<NewsBean> news) {
    this.news = news;
  }

  public List<String> getAuDioUrls() {
    List<String> newsList = new ArrayList<>();
    if (news != null && news.size() > 0) {
      for (NewsBean newsBean : news) {
        newsList.add(newsBean.getAudioUrl());
      }
    }
    return newsList;
  }

  public static class NewsBean implements Serializable {
    /**
     * id : 20180504064904103018
     * summary :
     * tags : 云端服务器,云计算,芯片,科技,人工智能
     * audioUrl : https://audio.leting.io/lm9wzj1qWiVrv-vr6NYtZAnC41hP.mp3
     * duration : 83
     * title : 中科院 我国首款云端智能芯片发布
     * imageUrl : https://image.leting.io/FvAAHFDUqtGEPDfL-yDsfdzv9XuW
     * humanTime : 3 小时前
     * updatedTime : 2018-05-04 06:31:07
     */

    private String id;
    private String summary;
    private String tags;
    private String audioUrl;
    private int duration;
    private String title;
    private String imageUrl;
    private String humanTime;
    private String updatedTime;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getSummary() {
      return summary;
    }

    public void setSummary(String summary) {
      this.summary = summary;
    }

    public String getTags() {
      return tags;
    }

    public void setTags(String tags) {
      this.tags = tags;
    }

    public String getAudioUrl() {
      return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
      this.audioUrl = audioUrl;
    }

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public String getHumanTime() {
      return humanTime;
    }

    public void setHumanTime(String humanTime) {
      this.humanTime = humanTime;
    }

    public String getUpdatedTime() {
      return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
      this.updatedTime = updatedTime;
    }
  }
}

