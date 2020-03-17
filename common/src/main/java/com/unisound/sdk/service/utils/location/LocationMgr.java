package com.unisound.sdk.service.utils.location;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.location.entity.LocationInfo;
import com.unisound.sdk.service.utils.location.impl.LocationCallBack;
import java.util.ArrayList;
import java.util.List;

public class LocationMgr {
  private static final String TAG = "LocationMgr";
  private List<LocationCallBack> callBackList = new ArrayList<>();
  private AMapLocationClient mLocationClient = null;
  private AMapLocationClientOption mLocationOption = null;
  private static LocationMgr locationMgr = new LocationMgr();

  public static LocationMgr getInstance() {
    return locationMgr;
  }

  public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
    float[] results = new float[1];
    Location.distanceBetween(lat1, lon1, lat2, lon2, results);
    return results[0];
  }

  public AMapLocationListener mLocationListener = new AMapLocationListener() {
    @Override public void onLocationChanged(AMapLocation aMapLocation) {
      LogMgr.d(TAG, "mapLocation:" + aMapLocation);
      if (aMapLocation != null) {
        if (aMapLocation.getErrorCode() == 0) {
          //可在其中解析amapLocation获取相应内容。
          if (!TextUtils.isEmpty(aMapLocation.getCity())) {
            LocationInfo info = new LocationInfo();
            info.type = aMapLocation.getLocationType();
            info.mName = aMapLocation.getPoiName();
            info.mProvider = aMapLocation.getProvince();
            info.mCity = aMapLocation.getCity();
            info.mDistrict = aMapLocation.getDistrict();
            info.mAddress = aMapLocation.getAddress();
            info.latitude = aMapLocation.getLatitude();
            info.mLongitude = aMapLocation.getLongitude();
            locationChange(info);
          }
        } else {
          LogMgr.e(TAG,
              "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation
                  .getErrorInfo());
          locationFail(aMapLocation.getErrorInfo());
        }
      }
    }
  };

  public void start(Context context) {
    LogMgr.e(TAG, "start location");
    mLocationClient = new AMapLocationClient(context.getApplicationContext());
    mLocationClient.setLocationListener(mLocationListener);
    mLocationOption = new AMapLocationClientOption();
    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    mLocationOption.setInterval(10000);
    mLocationOption.setNeedAddress(true);
    mLocationOption.setWifiActiveScan(false);
    //给定位客户端对象设置定位参数
    mLocationClient.setLocationOption(mLocationOption);
    //启动定位
    mLocationClient.startLocation();
  }

  public void addLocationCallBack(LocationCallBack callBack) {
    if (!callBackList.contains(callBack)) {
      callBackList.add(callBack);
    }
  }

  public void removeLocationCallBack(LocationCallBack callBack) {
    if (callBackList.contains(callBack)) {
      callBackList.remove(callBack);
    }
  }

  private void locationChange(LocationInfo locationInfo) {
    for (LocationCallBack callBack : callBackList) {
      callBack.locationChanged(locationInfo);
    }
  }

  private void locationFail(String error) {
    for (LocationCallBack callBack : callBackList) {
      callBack.locationFailed(error);
    }
  }

  private LocationMgr() {
  }
}
