package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class General implements Serializable {
  private String type;
  private String text;
  private String textTts;
  private String imgUrl;
  private String imgAlt;
  private String url;
  private String urlAlt;
  private String style;
  private String code;
  private String emoji;
  private String mood;
  private String audio;

  public String getEmoji() {
    return emoji;
  }

  public void setEmoji(String emoji) {
    this.emoji = emoji;
  }

  public String getMood() {
    return mood;
  }

  public void setMood(String mood) {
    this.mood = mood;
  }

  public String getCode() {
    return code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTextTts() {
    return textTts;
  }

  public void setTextTts(String textTts) {
    this.textTts = textTts;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getImgAlt() {
    return imgAlt;
  }

  public void setImgAlt(String imgAlt) {
    this.imgAlt = imgAlt;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUrlAlt() {
    return urlAlt;
  }

  public void setUrlAlt(String urlAlt) {
    this.urlAlt = urlAlt;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getAudio() {
    return audio;
  }

  public void setAudio(String audio) {
    this.audio = audio;
  }
}
