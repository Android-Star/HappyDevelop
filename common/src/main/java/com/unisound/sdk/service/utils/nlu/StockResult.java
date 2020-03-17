package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class StockResult implements Result, Serializable {
  private String mChartImgUrl;
  private String mName;
  private String mCode;
  private String mCurrentPrice;
  private String mChangeAmount;
  private String mChangeRate;
  private String mHighestPrice;
  private String mLowestPrice;
  private String mtradingVolume;
  private String mUpdateTime;
  private String mYesterdayClosingPrice;
  private String mTodayOpeningPrice;

  public String getmChartImgUrl() {
    return mChartImgUrl;
  }

  public void setmChartImgUrl(String mChartImgUrl) {
    this.mChartImgUrl = mChartImgUrl;
  }

  public String getmName() {
    return mName;
  }

  public void setmName(String mName) {
    this.mName = mName;
  }

  public String getmCode() {
    return mCode;
  }

  public void setmCode(String mCode) {
    this.mCode = mCode;
  }

  public String getmCurrentPrice() {
    return mCurrentPrice;
  }

  public void setmCurrentPrice(String mCurrentPrice) {
    this.mCurrentPrice = mCurrentPrice;
  }

  public String getmChangeAmount() {
    return mChangeAmount;
  }

  public void setmChangeAmount(String mChangeAmount) {
    this.mChangeAmount = mChangeAmount;
  }

  public String getmChangeRate() {
    return mChangeRate;
  }

  public void setmChangeRate(String mChangeRate) {
    this.mChangeRate = mChangeRate;
  }

  public String getmHighestPrice() {
    return mHighestPrice;
  }

  public void setmHighestPrice(String mHighestPrice) {
    this.mHighestPrice = mHighestPrice;
  }

  public String getmLowestPrice() {
    return mLowestPrice;
  }

  public void setmLowestPrice(String mLowestPrice) {
    this.mLowestPrice = mLowestPrice;
  }

  public String getMtradingVolume() {
    return mtradingVolume;
  }

  public void setMtradingVolume(String mtradingVolume) {
    this.mtradingVolume = mtradingVolume;
  }

  public String getmUpdateTime() {
    return mUpdateTime;
  }

  public void setmUpdateTime(String mUpdateTime) {
    this.mUpdateTime = mUpdateTime;
  }

  public String getmYesterdayClosingPrice() {
    return mYesterdayClosingPrice;
  }

  public void setmYesterdayClosingPrice(String mYesterdayClosingPrice) {
    this.mYesterdayClosingPrice = mYesterdayClosingPrice;
  }

  public String getmTodayOpeningPrice() {
    return mTodayOpeningPrice;
  }

  public void setmTodayOpeningPrice(String mTodayOpeningPrice) {
    this.mTodayOpeningPrice = mTodayOpeningPrice;
  }
}
