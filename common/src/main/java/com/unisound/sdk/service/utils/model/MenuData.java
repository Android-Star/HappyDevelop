package com.unisound.sdk.service.utils.model;

import java.io.Serializable;

public class MenuData implements Serializable {
  private long menuId;
  private String menuName;
  private String logoUrl;
  private int menuType; //1-菜单级，2-应用，3-视频，4-音频，5-通讯录、照相机等设备自身应用
  private String appKey;

  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public int getMenuType() {
    return menuType;
  }

  public void setMenuType(int menuType) {
    this.menuType = menuType;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }
}
