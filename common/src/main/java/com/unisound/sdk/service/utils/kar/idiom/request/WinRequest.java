package com.unisound.sdk.service.utils.kar.idiom.request;

import java.io.Serializable;

public class WinRequest implements Serializable {
  private long gameStateId;
  private int level;

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
}
