package com.unisound.sdk.service.utils.location.entity;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationInfo implements Serializable {
  public static final int GPS_ORIGIN = 0;
  public static final int GPS_GOOGLE = 2;
  public static final int GPS_BAIDU = 4;
  public static final int GPS_GAODE = 5;

  public int type = 4;
  public double latitude = 0.0;
  public double mLongitude = 0.0;

  public String mName;
  public String mProvince;
  public String mCity;
  public String mCityCode;
  public String mDistrict;
  public String mStreet;
  public String mAddress;

  public String mProvider;
  public long mTime = 0;
  public String mAddressDetail;
  public boolean mHasAltitude = false;
  public double mAltitude = 0.0f;
  public boolean mHasSpeed = false;
  public float mSpeed = 0.0f;
  public boolean mHasBearing = false;
  public float mBearing = 0.0f;
  public boolean mHasAccuracy = false;
  public float mAccuracy = 0.0f;

  public String mCondition;
  public String mPathPoints;

  public String getmPathPoints() {
    return mPathPoints;
  }

  public void setmPathPoints(String mPathPoints) {
    this.mPathPoints = mPathPoints;
  }

  public String getmCondition() {
    return mCondition;
  }

  public void setmCondition(String mCondition) {
    this.mCondition = mCondition;
  }

  public LocationInfo() {
  }

  public LocationInfo(LocationInfo l) {
    if (l == null) {
      return;
    }
    this.type = l.type;
    this.latitude = l.latitude;
    this.mLongitude = l.mLongitude;
    this.mName = l.mName;
    this.mProvince = l.mProvince;
    this.mCity = l.mCity;
    this.mCityCode = l.mCityCode;
    this.mDistrict = l.mDistrict;
    this.mStreet = l.mStreet;
    this.mAddress = l.mAddress;
    this.mAddressDetail = l.mAddressDetail;
    this.mCondition = l.mCondition;
    this.mPathPoints = l.mPathPoints;
  }

  public String getName() {
    return mName;
  }

  public void setName(String mName) {
    this.mName = mName;
  }

  public String getProvince() {
    return mProvince;
  }

  public void setProvince(String province) {
    mProvince = province;
  }

  public String getCityCode() {
    return mCityCode;
  }

  public void setCityCode(String mCityCode) {
    this.mCityCode = mCityCode;
  }

  public String getDistrict() {
    return mDistrict;
  }

  public void setDistrict(String mDistrict) {
    this.mDistrict = mDistrict;
  }

  public String getStreet() {
    return mStreet;
  }

  public void setStreet(String mStreet) {
    this.mStreet = mStreet;
  }

  public String getAddressDetail() {
    return mAddressDetail;
  }

  public void setAddressDetail(String address) {
    mAddressDetail = address;
  }

  public String getProvider() {
    return mProvider;
  }

  public void setProvider(String provider) {
    mProvider = provider;
  }

  public long getTime() {
    return mTime;
  }

  public boolean hasAccuracy() {
    return mHasAccuracy;
  }

  public void setTime(long time) {
    mTime = time;
  }

  public float getAccuracy() {
    return mAccuracy;
  }

  public void setAccuracy(float accuracy) {
    mAccuracy = accuracy;
    mHasAccuracy = true;
  }

  public boolean hasAltitude() {
    return mHasAltitude;
  }

  public double getAltitude() {
    return mAltitude;
  }

  public void setAltitude(double altitude) {
    mAltitude = altitude;
    mHasAltitude = true;
  }

  public void removeAltitude() {
    mAltitude = 0.0f;
    mHasAltitude = false;
  }

  public boolean hasSpeed() {
    return mHasSpeed;
  }

  public float getSpeed() {
    return mSpeed;
  }

  public void setSpeed(float speed) {
    mSpeed = speed;
    mHasSpeed = true;
  }

  public void removeSpeed() {
    mSpeed = 0.0f;
    mHasSpeed = false;
  }

  public boolean hasBearing() {
    return mHasBearing;
  }

  public float getBearing() {
    return mBearing;
  }

  public void setBearing(float bearing) {
    while (bearing < 0.0f) {
      bearing += 360.0f;
    }
    while (bearing >= 360.0f) {
      bearing -= 360.0f;
    }
    mBearing = bearing;
    mHasBearing = true;
  }

  public void removeBearing() {
    mBearing = 0.0f;
    mHasBearing = false;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return mLongitude;
  }

  public void setLongitude(double mLongitude) {
    this.mLongitude = mLongitude;
  }

  public String getCity() {
    return mCity;
  }

  public void setCity(String city) {
    if (city != null && city.lastIndexOf("市") > 0) {
      city = city.substring(0, city.lastIndexOf("市"));
    }
    this.mCity = city;
  }

  public String getAddress() {
    return mAddress;
  }

  public void setAddress(String address) {
    mAddress = address;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    type = type;
  }

  public JSONObject parse2JSONObj() {
    JSONObject jsonObj = new JSONObject();
    try {
      jsonObj.put("type", type);
      jsonObj.put("name", mName);
      jsonObj.put("province", mProvince);
      jsonObj.put("city", mCity);
      jsonObj.put("cityCode", mCityCode);
      jsonObj.put("destrict", mDistrict);
      jsonObj.put("street", mStreet);
      jsonObj.put("address", mAddress);
      jsonObj.put("addressDetail", mAddressDetail);
      jsonObj.put("condition", mCondition);
      jsonObj.put("pathPoints", mPathPoints);
      jsonObj.put("lat", getLatitude());
      jsonObj.put("lng", getLongitude());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return jsonObj;
  }

  @Override public String toString() {
    return parse2JSONObj().toString();
  }
}
