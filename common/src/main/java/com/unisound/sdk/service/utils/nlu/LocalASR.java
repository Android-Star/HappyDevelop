package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class LocalASR implements Serializable {

  private double score;
  private double vprScore;
  private String wakeUpResult;
  private String vprName;

  public double getVprScore() {
    return vprScore;
  }

  public void setVprScore(double vprScore) {
    this.vprScore = vprScore;
  }

  public String getVprName() {
    return vprName;
  }

  public void setVprName(String vprName) {
    this.vprName = vprName;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public String getWakeUpResult() {
    return wakeUpResult;
  }

  public void setWakeUpResult(String wakeUpResult) {
    this.wakeUpResult = wakeUpResult;
  }
}