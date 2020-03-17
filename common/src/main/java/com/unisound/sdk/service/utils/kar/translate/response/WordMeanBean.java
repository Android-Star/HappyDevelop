package com.unisound.sdk.service.utils.kar.translate.response;

import java.util.List;

public class WordMeanBean {

  private String query;
  private String translation;
  private String origin;
  private String target;
  private boolean collected;
  private BasicBean basic;

  public boolean isCollected() {
    return collected;
  }

  public void setCollected(boolean collected) {
    this.collected = collected;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getTranslation() {
    return translation;
  }

  public void setTranslation(String translation) {
    this.translation = translation;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public BasicBean getBasic() {
    return basic;
  }

  public void setBasic(BasicBean basic) {
    this.basic = basic;
  }

  public static class BasicBean {

    private String phonetic;
    private String ukPhonetic;
    private String usPhonetic;
    private String ukSpeech;
    private String usSpeech;
    private List<String> explains;

    public String getPhonetic() {
      return phonetic;
    }

    public void setPhonetic(String phonetic) {
      this.phonetic = phonetic;
    }

    public String getUkPhonetic() {
      return ukPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
      this.ukPhonetic = ukPhonetic;
    }

    public String getUsPhonetic() {
      return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
      this.usPhonetic = usPhonetic;
    }

    public String getUkSpeech() {
      return ukSpeech;
    }

    public void setUkSpeech(String ukSpeech) {
      this.ukSpeech = ukSpeech;
    }

    public String getUsSpeech() {
      return usSpeech;
    }

    public void setUsSpeech(String usSpeech) {
      this.usSpeech = usSpeech;
    }

    public List<String> getExplains() {
      return explains;
    }

    public void setExplains(List<String> explains) {
      this.explains = explains;
    }
  }
}
