package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;
import java.util.List;

public class IntentsBean implements Serializable {
  /**
   * values : [{"level":"A","name":"强","id":"11"},{"level":"B","name":"中","id":"12"},{"level":"C","name":"一般","id":"13"},{"level":"D","name":"无","id":"14"}]
   * isRequire : false
   */

  private boolean isRequire;
  private List<ValuesBean> values;

  public boolean isIsRequire() {
    return isRequire;
  }

  public void setIsRequire(boolean isRequire) {
    this.isRequire = isRequire;
  }

  public List<ValuesBean> getValues() {
    return values;
  }

  public void setValues(List<ValuesBean> values) {
    this.values = values;
  }

  public static class ValuesBean implements Serializable {
    /**
     * level : A
     * name : 强
     * id : 11
     */

    private String level;
    private String name;
    private String id;

    public String getLevel() {
      return level;
    }

    public void setLevel(String level) {
      this.level = level;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }
}
