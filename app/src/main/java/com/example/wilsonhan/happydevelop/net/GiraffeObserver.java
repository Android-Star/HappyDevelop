package com.example.wilsonhan.happydevelop.net;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wilsonhan.happydevelop.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class GiraffeObserver<T> implements Observer<T> {
    private Context context;

    public GiraffeObserver(Context context) {
        this.context = context;
    }

    public GiraffeObserver() {
    }

    @Override
    public void onError(Throwable e) {
        Log.e("lvr", e.getMessage());
        // todo error somthing
        if (e instanceof ExceptionHandler.ResponseThrowable) {
            onError((ExceptionHandler.ResponseThrowable) e);
        } else {
            onError(new ExceptionHandler.ResponseThrowable(e, ExceptionHandler.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
//        Toast.makeText(context, "建立连接", Toast.LENGTH_SHORT).show();

        //可以弹出Dialog 提示正在加载
        showDialog();

    }

    protected abstract void hideDialog();

    protected abstract void showDialog();

    @Override
    public void onComplete() {

//        Toast.makeText(context, "请求完毕", Toast.LENGTH_SHORT).show();
        //可以取消Dialog 加载完毕
        hideDialog();
    }


    public abstract void onError(ExceptionHandler.ResponseThrowable e);
}
