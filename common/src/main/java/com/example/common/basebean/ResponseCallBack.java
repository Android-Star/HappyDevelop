package com.example.common.basebean;

import java.io.Serializable;

public interface ResponseCallBack<T> extends Serializable {

  void onResponse(BaseResponseBean<T> response);
}
