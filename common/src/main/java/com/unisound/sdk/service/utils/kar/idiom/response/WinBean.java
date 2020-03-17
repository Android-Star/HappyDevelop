package com.unisound.sdk.service.utils.kar.idiom.response;

import java.io.Serializable;

public class WinBean implements Serializable {
  //是否成功解锁新关卡，玩重复的关卡返回false
  private boolean unlockLevel;
  //过关奖励分数
  private int winScore;
  //用户当前分数
  private int userScore;

  public boolean isUnlockLevel() {
    return unlockLevel;
  }

  public void setUnlockLevel(boolean unlockLevel) {
    this.unlockLevel = unlockLevel;
  }

  public int getWinScore() {
    return winScore;
  }

  public void setWinScore(int winScore) {
    this.winScore = winScore;
  }

  public int getUserScore() {
    return userScore;
  }

  public void setUserScore(int userScore) {
    this.userScore = userScore;
  }
}
