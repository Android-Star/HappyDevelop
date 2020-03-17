package com.unisound.sdk.service.utils.kar.idiom.response;

import java.io.Serializable;

public class PlayGameBean implements Serializable {
  private String audioWord;
  private boolean result;
  private String nextWord;
  private boolean dataCenterEmpty;

  public String getAudioWord() {
    return audioWord;
  }

  public void setAudioWord(String audioWord) {
    this.audioWord = audioWord;
  }

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public String getNextWord() {
    return nextWord;
  }

  public void setNextWord(String nextWord) {
    this.nextWord = nextWord;
  }

  public boolean isDataCenterEmpty() {
    return dataCenterEmpty;
  }

  public void setDataCenterEmpty(boolean dataCenterEmpty) {
    this.dataCenterEmpty = dataCenterEmpty;
  }
}
