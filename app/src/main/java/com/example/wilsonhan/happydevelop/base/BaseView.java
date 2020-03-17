package com.example.wilsonhan.happydevelop.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface BaseView {

  void showLoadingDialog(String msg);

  void hideLoadingDialog();

  <T> LifecycleTransformer<T> bindToLifecycle();
}
