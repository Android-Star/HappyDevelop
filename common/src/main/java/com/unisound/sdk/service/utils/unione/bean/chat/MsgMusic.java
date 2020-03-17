package com.unisound.sdk.service.utils.unione.bean.chat;

import java.io.Serializable;

public class MsgMusic implements Serializable {

  private String id;
  private String artist;
  private String title;
  private String album;
  private String url;
  private String imgUrl;
  private boolean isCollected;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public boolean isCollected() {
    return isCollected;
  }

  public void setCollected(boolean isCollected) {
    this.isCollected = isCollected;
  }
}
