package com.example.common.utils;

import com.example.common.basebean.ResponseCallBack;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class JsonTool {

  public <T> Type getResponseType(ResponseCallBack<T> callBack) {
    Method[] methods = callBack.getClass().getMethods();
    for (Method method : methods) {
      if (method.getName().contains("onResponse")) {
        return method.getGenericParameterTypes()[0];
      }
    }
    return null;
  }
}
