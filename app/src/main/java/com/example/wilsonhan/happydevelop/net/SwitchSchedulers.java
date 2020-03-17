package com.example.wilsonhan.happydevelop.net;

import com.example.wilsonhan.happydevelop.base.BaseView;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SwitchSchedulers {

  private SwitchSchedulers() {

  }

  public static <T> ObservableTransformer<T, T> applySchedulers(final BaseView baseView) {
    return observable -> observable.compose(baseView.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> ObservableTransformer<T, T> applySchedulers() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
