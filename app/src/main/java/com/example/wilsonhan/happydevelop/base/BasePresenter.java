package com.example.wilsonhan.happydevelop.base;

public interface BasePresenter<V extends BaseView> {

  void attachBaseView(V view);

  void detachBaseView();

  boolean isViewAttached();
}
