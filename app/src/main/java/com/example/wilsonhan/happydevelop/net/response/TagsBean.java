package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;
import java.util.List;

public class TagsBean implements Serializable {
  /**
   * isRequired : true
   * values : [{"name":"女"},{"name":"男"}]
   * name : 性别
   * index : 1
   * modifiable : false
   * isRadio : true
   * key : 1002
   */

  private boolean isRequired;
  private String name;
  private int index;
  private boolean modifiable;
  private boolean isRadio;
  private String key;
  private List<ValuesBean1> values;

  public boolean isIsRequired() {
    return isRequired;
  }

  public void setIsRequired(boolean isRequired) {
    this.isRequired = isRequired;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public boolean isModifiable() {
    return modifiable;
  }

  public void setModifiable(boolean modifiable) {
    this.modifiable = modifiable;
  }

  public boolean isIsRadio() {
    return isRadio;
  }

  public void setIsRadio(boolean isRadio) {
    this.isRadio = isRadio;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public List<ValuesBean1> getValues() {
    return values;
  }

  public void setValues(List<ValuesBean1> values) {
    this.values = values;
  }

  public static class ValuesBean1 {
    private String name;
    private boolean isRequired;
    private int index;
    private boolean modifiable;
    private boolean isRadio;
    private String key;
    private List<ValuesBean> values;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public boolean isRequired() {
      return isRequired;
    }

    public void setRequired(boolean required) {
      isRequired = required;
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public boolean isModifiable() {
      return modifiable;
    }

    public void setModifiable(boolean modifiable) {
      this.modifiable = modifiable;
    }

    public boolean isRadio() {
      return isRadio;
    }

    public void setRadio(boolean radio) {
      isRadio = radio;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public List<ValuesBean> getValues() {
      return values;
    }

    public void setValues(List<ValuesBean> values) {
      this.values = values;
    }
  }

  public static class ValuesBean {

    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
