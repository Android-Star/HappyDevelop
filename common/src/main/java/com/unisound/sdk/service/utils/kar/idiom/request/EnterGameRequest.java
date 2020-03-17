package com.unisound.sdk.service.utils.kar.idiom.request;

import java.io.Serializable;

public class EnterGameRequest implements Serializable {
  private long gameStateId;
  private int level;
  private long randomSeed;

  public long getGameStateId() {
    return gameStateId;
  }

  public void setGameStateId(long gameStateId) {
    this.gameStateId = gameStateId;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public long getRandomSeed() {
    return randomSeed;
  }

  public void setRandomSeed(long randomSeed) {
    this.randomSeed = randomSeed;
  }
}
