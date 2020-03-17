package com.unisound.sdk.service.utils.model;

public enum MenuEnum {
  MENU(1), APP(2), VIDEO(3), AUDIO(4), OTHER(5);
  private int menuType;

  MenuEnum(int menuType) {
    this.menuType = menuType;
  }

  public int getMenuType() {
    return menuType;
  }

  public void setMenuType(int menuType) {
    this.menuType = menuType;
  }
}
