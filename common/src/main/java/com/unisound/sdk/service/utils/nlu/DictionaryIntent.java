package com.unisound.sdk.service.utils.nlu;

import com.unisound.sdk.service.utils.model.DictionaryBean;
import java.io.Serializable;

public class DictionaryIntent implements Intent, Serializable {
  private KarProCallback karProCallback;
  private String term;
  private String word;
  private String strokeNumber;

  public KarProCallback getKarProCallback() {
    return karProCallback;
  }

  public void setKarProCallback(KarProCallback karProCallback) {
    this.karProCallback = karProCallback;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getStrokeNumber() {
    return strokeNumber;
  }

  public void setStrokeNumber(String strokeNumber) {
    this.strokeNumber = strokeNumber;
  }

  public static class KarProCallback {
    private DictionaryBean data;
    private int totalTime;
    private String errorMessage;
    private int errorCode;

    public DictionaryBean getData() {
      return data;
    }

    public void setData(DictionaryBean data) {
      this.data = data;
    }

    public int getTotalTime() {
      return totalTime;
    }

    public void setTotalTime(int totalTime) {
      this.totalTime = totalTime;
    }

    public String getErrorMessage() {
      return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
      return errorCode;
    }

    public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
    }
  }
}
