package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class MusicIntent implements Intent, Serializable {
  private String song;
  private String artist;
  private String genre;
  private String musicTag;
  private String mood;
  private String scene;
  private String billboard;
  private String language;
  private String lyrics;
  private String songlist;
  private String keyword;
  private String album;
  private String artistType;
  private String episode;

  public String getSong() {
    return song;
  }

  public void setSong(String song) {
    this.song = song;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getMusicTag() {
    return musicTag;
  }

  public void setMusicTag(String musicTag) {
    this.musicTag = musicTag;
  }

  public String getMood() {
    return mood;
  }

  public void setMood(String mood) {
    this.mood = mood;
  }

  public String getScene() {
    return scene;
  }

  public void setScene(String scene) {
    this.scene = scene;
  }

  public String getBillboard() {
    return billboard;
  }

  public void setBillboard(String billboard) {
    this.billboard = billboard;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getLyrics() {
    return lyrics;
  }

  public void setLyrics(String lyrics) {
    this.lyrics = lyrics;
  }

  public String getSonglist() {
    return songlist;
  }

  public void setSonglist(String songlist) {
    this.songlist = songlist;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public String getArtistType() {
    return artistType;
  }

  public void setArtistType(String artistType) {
    this.artistType = artistType;
  }

  public String getEpisode() {
    return episode;
  }

  public void setEpisode(String episode) {
    this.episode = episode;
  }
}
