package com.unisound.sdk.service.utils.kar.menu.response;

import java.io.Serializable;
import java.util.List;

public class LauncherResponse implements Serializable {
  private long updateTime;
  private List<MenuBean> menu;

  public long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(long updateTime) {
    this.updateTime = updateTime;
  }

  public List<MenuBean> getMenu() {
    return menu;
  }

  public void setMenu(List<MenuBean> menu) {
    this.menu = menu;
  }

  public static class MenuBean implements Serializable {
    private String columnName;
    private long contentColumnId;
    private String logoUrl;
    private int appLevel;
    private int dataType;
    private long albumId;
    private String target;
    private boolean isNeedAccessToken;
    private int homeLevel;
    private String smallFileLogoUrl;
    private List<MenuBean> list;

    public String getColumnName() {
      return columnName;
    }

    public void setColumnName(String columnName) {
      this.columnName = columnName;
    }

    public long getContentColumnId() {
      return contentColumnId;
    }

    public void setContentColumnId(long contentColumnId) {
      this.contentColumnId = contentColumnId;
    }

    public String getLogoUrl() {
      return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
      this.logoUrl = logoUrl;
    }

    public int getAppLevel() {
      return appLevel;
    }

    public void setAppLevel(int appLevel) {
      this.appLevel = appLevel;
    }

    public int getDataType() {
      return dataType;
    }

    public void setDataType(int dataType) {
      this.dataType = dataType;
    }

    public long getAlbumId() {
      return albumId;
    }

    public void setAlbumId(long albumId) {
      this.albumId = albumId;
    }

    public String getTarget() {
      return target;
    }

    public void setTarget(String target) {
      this.target = target;
    }

    public boolean isNeedAccessToken() {
      return isNeedAccessToken;
    }

    public void setNeedAccessToken(boolean needAccessToken) {
      isNeedAccessToken = needAccessToken;
    }

    public int getHomeLevel() {
      return homeLevel;
    }

    public void setHomeLevel(int homeLevel) {
      this.homeLevel = homeLevel;
    }

    public String getSmallFileLogoUrl() {
      return smallFileLogoUrl;
    }

    public void setSmallFileLogoUrl(String smallFileLogoUrl) {
      this.smallFileLogoUrl = smallFileLogoUrl;
    }

    public List<MenuBean> getList() {
      return list;
    }

    public void setList(List<MenuBean> list) {
      this.list = list;
    }
  }
}
