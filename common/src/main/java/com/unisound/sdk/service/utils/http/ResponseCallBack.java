package com.unisound.sdk.service.utils.http;

import java.io.Serializable;

public interface ResponseCallBack<T> extends Serializable {
  void onResponse(T response);

  void onError(String error);
}
