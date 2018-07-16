package com.example.wilsonhan.happydevelop.net;

import com.example.wilsonhan.happydevelop.bean.RetrofitResBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class ErrorTransformer<T> implements ObservableTransformer {
    Function errorFun = new Function<RetrofitResBean<T>, T>() {

        @Override
        public T apply(RetrofitResBean<T> tRetrofitResBean) throws Exception {
            //response中code码不会0 出现错误
            if (!tRetrofitResBean.isOk())
                throw new RuntimeException(tRetrofitResBean.getStatus() + "" + tRetrofitResBean.getReturn_msg() != null ? tRetrofitResBean.getReturn_msg() : "");
            return tRetrofitResBean.getResult() == null ? (T) "" : tRetrofitResBean.getResult();
        }
    };

    Function httpResponseFunction=new Function<Throwable, Observable<T>>() {

        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(ExceptionHandler.handleException(throwable));
        }
    };

    @Override
    public ObservableSource apply(Observable upstream) {
        //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
        return (Observable<T>) upstream.map(errorFun)
                .onErrorResumeNext(httpResponseFunction);
    }

}
