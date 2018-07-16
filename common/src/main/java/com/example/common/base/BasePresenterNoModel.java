package com.example.common.base;

import android.app.Activity;
import android.content.Context;

import com.example.common.baserx.RxManager;

import rx.subscriptions.CompositeSubscription;

/**
 * des:基类presenter
 * on 2016.07.11:55
 */
public abstract class BasePresenterNoModel<T>{
    public Context mContext;
    public Activity mActivity;
    public T mView;
    public RxManager mRxManage = new RxManager();
    public CompositeSubscription sCompositeSubscription = new CompositeSubscription();

    public void setV(T v) {
        this.mView = v;
        this.onStart();
    }
    public void onStart(){
    }

    public void onDestroy() {
        mRxManage.clear();
    }
}
