package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class LocalSearchIntent implements Intent, Serializable {
  public static final String CURRENT_CITY = "CURRENT_CITY";
  public static final String CURRENT_LOC = "CURRENT_LOC";
  public static final int BUSINESS_SEARCH_SORT_DEFAULT = 1;
  public static final int BUSINESS_SEARCH_SORT_STAR_DESC = 2;
  public static final int BUSINESS_SEARCH_SORT_PRODUCT_DESC = 3;
  public static final int BUSINESS_SEARCH_SORT_ENVIRONMENT_DESC = 4;
  public static final int BUSINESS_SEARCH_SORT_SERVE_DESC = 5;
  public static final int BUSINESS_SEARCH_SORT_REVIEWS_DESC = 6;
  public static final int BUSINESS_SEARCH_SORT_DISTANCE_ASC = 7;
  public static final int DEAL_SEARCH_SORT_DEFAULT = 1;
  public static final int DEAL_SEARCH_SORT_PRICE_ASC = 2;
  public static final int DEAL_SEARCH_SORT_PRICE_DESC = 3;
  public static final int DEAL_SEARCH_SORT_PURCHASE_DESC = 4;
  public static final int DEAL_SEARCH_SORT_LASTEST_ASC = 5;
  public static final int DEAL_SEARCH_SORT_DEADLINE_ASC = 6;
  public static final int DEAL_SEARCH_SORT_DISTANCE_ASC = 7;

  private String city;
  private String region;
  private String poi;
  private int radius;
  private String category;
  private String keyword;
  private int priceLow;
  private int priceHigh;
  private int hasCoupon;
  private int hasDeal;
  private int sort;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPoi() {
    return poi;
  }

  public void setPoi(String poi) {
    this.poi = poi;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public int getPriceLow() {
    return priceLow;
  }

  public void setPriceLow(int priceLow) {
    this.priceLow = priceLow;
  }

  public int getPriceHigh() {
    return priceHigh;
  }

  public void setPriceHigh(int priceHigh) {
    this.priceHigh = priceHigh;
  }

  public int getHasCoupon() {
    return hasCoupon;
  }

  public void setHasCoupon(int hasCoupon) {
    this.hasCoupon = hasCoupon;
  }

  public int getHasDeal() {
    return hasDeal;
  }

  public void setHasDeal(int hasDeal) {
    this.hasDeal = hasDeal;
  }

  public int getSort() {
    return sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }
}
