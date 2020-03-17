package com.unisound.sdk.service.utils.kar.oral.request;

import java.io.Serializable;

public class ClassRequest implements Serializable {
  private int textBookGradeId;

  public int getTextBookGradeId() {
    return textBookGradeId;
  }

  public void setTextBookGradeId(int textBookGradeId) {
    this.textBookGradeId = textBookGradeId;
  }
}
