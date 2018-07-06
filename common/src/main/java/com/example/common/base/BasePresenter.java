package com.example.common.base;

import android.app.Activity;
import android.content.Context;

import com.example.common.baserx.RxManager;
import com.example.common.commonutils.LogUtils;

import rx.subscriptions.CompositeSubscription;

/**
 * des:基类presenter
 * on 2016.07.11:55
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public Activity mActivity;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();
    public CompositeSubscription sCompositeSubscription = new CompositeSubscription();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    }

    public void onDestroy() {
        mRxManage.clear();
    }
}
