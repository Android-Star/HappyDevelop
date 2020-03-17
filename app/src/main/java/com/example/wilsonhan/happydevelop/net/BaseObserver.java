package com.example.wilsonhan.happydevelop.net;

import android.accounts.NetworkErrorException;
import android.content.Context;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * 请求回调,主要实现Observer类
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
  private Context mContext;

  public BaseObserver(Context mContext) {
    this.mContext = mContext;
  }

  public BaseObserver() {
  }

  @Override public void onSubscribe(Disposable disposable) {

  }

  @Override public void onComplete() {

  }

  @Override public void onError(Throwable e) {
    if (e instanceof ConnectException || e instanceof TimeoutException
        || e instanceof NetworkErrorException || e instanceof UnknownHostException) {
      try {
        onFailure(e, false);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } else {
      try {
        onFailure(e, true);
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
  }

  @Override public void onNext(BaseResponse<T> response) {
    if (response.isSuccess()) {
      onSuccess(response);
    } else {
      onCodeError(response);
    }
  }

  //请求成功且返回码为200的回调方法,这里抽象方法申明
  public abstract void onSuccess(BaseResponse<T> response);

  //请求成功但返回的code码不是200的回调方法,这里抽象方法申明
  public abstract void onCodeError(BaseResponse response);

  //请求失败回调方法,这里抽象方法申明
  public abstract void onFailure(Throwable e, boolean netWork) throws Exception;
}
