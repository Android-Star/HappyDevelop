package com.unisound.sdk.service.utils.kar.oral.response;

import java.io.Serializable;

public class SaveResult implements Serializable {
  private boolean save;

  public boolean isSave() {
    return save;
  }

  public void setSave(boolean save) {
    this.save = save;
  }
}
