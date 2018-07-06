package com.example.wilsonhan.happydevelop.app;


import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.example.common.baseapp.BaseApplication;
import com.example.common.commonutils.HttpsUtils;
import com.example.common.commonutils.LogUtils;

import java.io.IOException;
import java.io.InputStream;



/**
 * Created by yinzelu on 2017/8/29.
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        _initDatabase();
        _initInjector();
        _initConfig();
    }

    /**
     * 初始化注射器
     */
    private void _initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据

    }

    /**
     * 初始化数据库
     */
    private void _initDatabase() {

    }


    /**
     * 初始化配置
     */
    private void _initConfig() {
        //初始化logger 是否打开log
        LogUtils.logInit(true);

        //让Glide能用HTTPS
        InputStream[] InputStream = new InputStream[1];
        try {
            InputStream input = this.getAssets().open("RapidSSL_SHA256_CA_GeoTrust_Global_CA_1.cer");
            InputStream[0] = input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(HttpsUtils.getOkHttpClient(InputStream)));
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        LogUtils.logi("AppApplication onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        LogUtils.logi("AppApplication onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        LogUtils.logi("AppApplication onTrimMemory");
        super.onTrimMemory(level);
    }


}

