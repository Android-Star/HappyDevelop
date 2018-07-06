package com.example.wilsonhan.happydevelop.net;

import com.example.wilsonhan.happydevelop.bean.RetrofitResBean;

import io.reactivex.functions.Function;

public class MapFunction <T>implements Function<RetrofitResBean<T>, T> {
    @Override
    public T apply(RetrofitResBean<T> tRetrofitResBean) throws Exception {
        //response中code码不会0 出现错误
        if (!tRetrofitResBean.isOk())
            throw new RuntimeException(tRetrofitResBean.getStatus() + "" + tRetrofitResBean.getReturn_msg() != null ? tRetrofitResBean.getReturn_msg() : "");
        return tRetrofitResBean.getResult();
    }
}
