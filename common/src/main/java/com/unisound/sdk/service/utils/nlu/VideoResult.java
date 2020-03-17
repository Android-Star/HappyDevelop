package com.unisound.sdk.service.utils.nlu;

import com.unisound.sdk.service.utils.model.MangoAsset;
import java.io.Serializable;
import java.util.List;

public class VideoResult implements Result, Serializable {
  private String searchType;
  private long count;
  private String dataType;
  private List<MangoAsset> audioList;

  public String getSearchType() {
    return searchType;
  }

  public void setSearchType(String searchType) {
    this.searchType = searchType;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public List<MangoAsset> getAudioList() {
    return audioList;
  }

  public void setAudioList(List<MangoAsset> audioList) {
    this.audioList = audioList;
  }
}
