package com.unisound.sdk.service.utils.kar.menu.response;

public enum LauncherEnum {
  COLLECTION(1), ALBUM(2), WORKS(3), H5(4), OTHER(5), ACTIVITY(6), APP(7);
  private int dataType;

  LauncherEnum(int dataType) {
    this.dataType = dataType;
  }

  public int getDataType() {
    return dataType;
  }

  public void setDataType(int dataType) {
    this.dataType = dataType;
  }
}
