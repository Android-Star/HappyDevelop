package com.example.wilsonhan.happydevelop.net;

import com.example.wilsonhan.happydevelop.callback.ProgressListener;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 通过初始化okhttp和retrofit,获取ApiServise的实例
 * 主要做retrofit绑定okhttp,然后通过retrofit的实例
 * 获取ApiServise的实例
 */
public class BaseRequest {

  private static BaseRequest instance;
  private ApiService apiService;
  //通过retrofit的实例,获取ApiServise接口的实例

  private BaseRequest() {
    //初始化Okhttp,绑定拦截器事件
    OkHttpClient client =
        new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)    //设置请求超时时间
            .readTimeout(20, TimeUnit.SECONDS)                       //设置读取数据超时时间
            .writeTimeout(20, TimeUnit.SECONDS)                      //设置写入数据超时时间
            .addInterceptor(InterceptorUtil.logInterceptor())                //绑定日志拦截器
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(InterceptorUtil.headerInterceptor())       //绑定header拦截器
            .build();

    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create())             //设置gson转换器,将返回的json数据转为实体
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())       //设置CallAdapter
        .baseUrl(UrlConstant.getInstance().getAppUrl())
        .client(client)                                                 //设置客户端okhttp相关参数.
        .build();
    apiService = retrofit.create(ApiService.class);
  }

  public BaseRequest(ProgressListener listener) {
    //初始化Okhttp,绑定拦截器事件
    OkHttpClient client =
        new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)    //设置请求超时时间
            .readTimeout(20, TimeUnit.SECONDS)                       //设置读取数据超时时间
            .writeTimeout(20, TimeUnit.SECONDS)                      //设置写入数据超时时间
            .addInterceptor(InterceptorUtil.logInterceptor())                //绑定日志拦截器
            .addInterceptor(InterceptorUtil.progressInterceptor(listener))
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(InterceptorUtil.headerInterceptor())       //绑定header拦截器
            .build();

    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create())                                    //设置gson转换器,将返回的json数据转为实体
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())       //设置CallAdapter
        .baseUrl(UrlConstant.getInstance().getAppUrl())
        .client(client)                                                 //设置客户端okhttp相关参数.
        .build();
    apiService = retrofit.create(ApiService.class);
  }

  public static BaseRequest getInstance() {
    if (instance == null) {
      synchronized (BaseRequest.class) {
        if (instance == null) {
          instance = new BaseRequest();
        }
      }
    }
    return instance;
  }

  public ApiService getApiService() {
    return apiService;
  }
}
