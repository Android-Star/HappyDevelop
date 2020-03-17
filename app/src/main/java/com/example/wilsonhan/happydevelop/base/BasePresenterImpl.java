package com.example.wilsonhan.happydevelop.base;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {
  protected T view;

  @Override public void attachBaseView(T view) {
    this.view = view;
  }

  @Override public void detachBaseView() {
    this.view = null;
  }

  @Override public boolean isViewAttached() {
    return this.view != null;
  }
}
