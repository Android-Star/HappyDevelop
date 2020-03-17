package com.unisound.sdk.service.utils.kar.translate.response;

import java.util.List;

public class WordListInfoBean {
  private boolean lastPage;
  private List<WordInfoBean> wordList;

  public boolean isLastPage() {
    return lastPage;
  }

  public void setLastPage(boolean lastPage) {
    this.lastPage = lastPage;
  }

  public List<WordInfoBean> getWordList() {
    return wordList;
  }

  public void setWordList(List<WordInfoBean> wordList) {
    this.wordList = wordList;
  }
}
