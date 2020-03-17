package com.unisound.sdk.service.utils.kar.idiom.response;

import java.io.Serializable;

public class HintBean implements Serializable {
  private String word;
  private int score;
  private boolean dataCenterEmpty;

  public boolean isDataCenterEmpty() {
    return dataCenterEmpty;
  }

  public void setDataCenterEmpty(boolean dataCenterEmpty) {
    this.dataCenterEmpty = dataCenterEmpty;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
