package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.List;

public class CookBookResult implements Result, Serializable {
  private int count;
  private int source;
  private int errorCode;
  private String dataSourceName;
  private int totalTime;
  private List<CookBooksBean> cookBooks;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getSource() {
    return source;
  }

  public void setSource(int source) {
    this.source = source;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getDataSourceName() {
    return dataSourceName;
  }

  public void setDataSourceName(String dataSourceName) {
    this.dataSourceName = dataSourceName;
  }

  public int getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(int totalTime) {
    this.totalTime = totalTime;
  }

  public List<CookBooksBean> getCookBooks() {
    return cookBooks;
  }

  public void setCookBooks(List<CookBooksBean> cookBooks) {
    this.cookBooks = cookBooks;
  }

  public static class CookBooksBean implements Serializable {

    private String webAddress;
    private String difficulty;
    private String mixedIngredients;
    private String timeConsume;
    private String imgAddress;
    private int id;
    private String dishName;
    private String foodMaterial;
    private String prodProcess;
    private String describe;
    private String suitCrowds;
    private String tips;
    private String prodImage;
    private String ediblePeopleCount;
    private List<StepsBean> steps;

    public String getWebAddress() {
      return webAddress;
    }

    public void setWebAddress(String webAddress) {
      this.webAddress = webAddress;
    }

    public String getDifficulty() {
      return difficulty;
    }

    public void setDifficulty(String difficulty) {
      this.difficulty = difficulty;
    }

    public String getMixedIngredients() {
      return mixedIngredients;
    }

    public void setMixedIngredients(String mixedIngredients) {
      this.mixedIngredients = mixedIngredients;
    }

    public String getTimeConsume() {
      return timeConsume;
    }

    public void setTimeConsume(String timeConsume) {
      this.timeConsume = timeConsume;
    }

    public String getImgAddress() {
      return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
      this.imgAddress = imgAddress;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getDishName() {
      return dishName;
    }

    public void setDishName(String dishName) {
      this.dishName = dishName;
    }

    public String getFoodMaterial() {
      return foodMaterial;
    }

    public void setFoodMaterial(String foodMaterial) {
      this.foodMaterial = foodMaterial;
    }

    public String getProdProcess() {
      return prodProcess;
    }

    public void setProdProcess(String prodProcess) {
      this.prodProcess = prodProcess;
    }

    public String getDescribe() {
      return describe;
    }

    public void setDescribe(String describe) {
      this.describe = describe;
    }

    public String getSuitCrowds() {
      return suitCrowds;
    }

    public void setSuitCrowds(String suitCrowds) {
      this.suitCrowds = suitCrowds;
    }

    public String getTips() {
      return tips;
    }

    public void setTips(String tips) {
      this.tips = tips;
    }

    public String getProdImage() {
      return prodImage;
    }

    public void setProdImage(String prodImage) {
      this.prodImage = prodImage;
    }

    public String getEdiblePeopleCount() {
      return ediblePeopleCount;
    }

    public void setEdiblePeopleCount(String ediblePeopleCount) {
      this.ediblePeopleCount = ediblePeopleCount;
    }

    public List<StepsBean> getSteps() {
      return steps;
    }

    public void setSteps(List<StepsBean> steps) {
      this.steps = steps;
    }

    public static class StepsBean implements Serializable {

      private int id;
      private String imgUrl;
      private int stepNum;
      private String describe;
      private int dishId;
      private String stepName;

      public int getId() {
        return id;
      }

      public void setId(int id) {
        this.id = id;
      }

      public String getImgUrl() {
        return imgUrl;
      }

      public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
      }

      public int getStepNum() {
        return stepNum;
      }

      public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
      }

      public String getDescribe() {
        return describe;
      }

      public void setDescribe(String describe) {
        this.describe = describe;
      }

      public int getDishId() {
        return dishId;
      }

      public void setDishId(int dishId) {
        this.dishId = dishId;
      }

      public String getStepName() {
        return stepName;
      }

      public void setStepName(String stepName) {
        this.stepName = stepName;
      }
    }
  }
}
