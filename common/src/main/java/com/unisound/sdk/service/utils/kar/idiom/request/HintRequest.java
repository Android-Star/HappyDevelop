package com.unisound.sdk.service.utils.kar.idiom.request;

import java.io.Serializable;

public class HintRequest implements Serializable {
  private long gameStateId;
  private String lastWord;
  private int currentLevel;

  public long getGameStateId() {
    return gameStateId;
  }

  public void setGameStateId(long gameStateId) {
    this.gameStateId = gameStateId;
  }

  public String getLastWord() {
    return lastWord;
  }

  public void setLastWord(String lastWord) {
    this.lastWord = lastWord;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(int currentLevel) {
    this.currentLevel = currentLevel;
  }
}
