package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class CookBookIntent implements Intent, Serializable {

  private String dish;

  public String getDish() {
    return dish;
  }

  public void setDish(String dish) {
    this.dish = dish;
  }
}
