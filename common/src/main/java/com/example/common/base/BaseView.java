package com.example.common.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * des:baseview
 * on 2016.07.11:53
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();
}
