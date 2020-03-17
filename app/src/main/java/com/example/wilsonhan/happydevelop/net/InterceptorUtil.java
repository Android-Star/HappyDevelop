package com.example.wilsonhan.happydevelop.net;

import com.example.wilsonhan.happydevelop.callback.ProgressListener;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 工具拦截器
 */
public class InterceptorUtil {
  private static final String TAG = "InterceptorUtil";

  private InterceptorUtil() {
  }

  public static HttpLoggingInterceptor logInterceptor() {     //日志拦截器,用于打印返回请求的结果
    return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
      @Override public void log(String message) {
        LogMgr.w(TAG, "log:" + message);
      }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  public static Interceptor headerInterceptor() {      //头部添加请求头信息
    return new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().
            addHeader("Content-Type", "application/json;charSet=UTF-8").build();
        return chain.proceed(request);
      }
    };
  }

  public static Interceptor progressInterceptor(ProgressListener progressListener) {
    return new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
            .body(new ProgressResponseBody(chain.request().url().url().toString(),
                originalResponse.body(), progressListener))
            .build();
      }
    };
  }
}