package com.unisound.sdk.service.utils.location.impl;

import com.unisound.sdk.service.utils.location.entity.LocationInfo;

/**
 * Created by gaohailong on 2016/12/19.
 */
public interface LocationCallBack {
  void locationChanged(LocationInfo locationInfo);

  void locationFailed(String error);
}
