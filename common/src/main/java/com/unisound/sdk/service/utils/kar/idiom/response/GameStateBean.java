package com.unisound.sdk.service.utils.kar.idiom.response;

import java.io.Serializable;

public class GameStateBean implements Serializable {
  private String uicon;
  private int uscore;
  private long gameStateId;
  private int unlockLevel;
  private String updateTime;
  private int levelCount;
  private String nickName;
  private int ranking;

  public String getUicon() {
    return uicon;
  }

  public void setUicon(String uicon) {
    this.uicon = uicon;
  }

  public int getUscore() {
    return uscore;
  }

  public void setUscore(int uscore) {
    this.uscore = uscore;
  }

  public long getGameStateId() {
    return gameStateId;
  }

  public void setGameStateId(long gameStateId) {
    this.gameStateId = gameStateId;
  }

  public int getUnlockLevel() {
    return unlockLevel;
  }

  public void setUnlockLevel(int unlockLevel) {
    this.unlockLevel = unlockLevel;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public int getLevelCount() {
    return levelCount;
  }

  public void setLevelCount(int levelCount) {
    this.levelCount = levelCount;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public int getRanking() {
    return ranking;
  }

  public void setRanking(int ranking) {
    this.ranking = ranking;
  }
}
