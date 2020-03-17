package com.example.wilsonhan.happydevelop.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.SimpleImmersionFragment;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.smartphone.bean.EventBean;
import com.unisound.smartphone.ui.view.LoadingDialogUtils;
import com.unisound.smartphone.utils.EventBusUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends SimpleImmersionFragment {
  private static final String TAG = "BaseFragment";
  protected Activity mActivity;
  protected View mRootView;

  private Unbinder unbinder;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mActivity = (Activity) context;
  }

  protected void setStatusBar(View titleView) {
    ImmersionBar.setTitleBar(mActivity, titleView);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(getLayoutId(), container, false);
    return mRootView;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
    if (enableEventBus()) {
      LogMgr.d(TAG, "eventBus register");
      EventBusUtils.register(this);
    }
  }

  protected boolean enableEventBus() {
    return false;
  }

  @Subscribe(threadMode = ThreadMode.MAIN) public void onEventCome(EventBean event) {
    if (event != null) {
      receiveEvent(event);
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void onStickyEventCome(EventBean event) {
    if (event != null) {
      receiveStickyEvent(event);
    }
  }

  protected void receiveEvent(EventBean event) {

  }

  protected void receiveStickyEvent(EventBean event) {

  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }
    if (enableEventBus()) {
      EventBusUtils.unregister(this);
    }
  }

  protected abstract int getLayoutId();

  @Override public void initImmersionBar() {
    //ImmersionBar.with(this).keyboardEnable(true).init();
  }

  protected String getFragmentName() {
    return this.getClass().getSimpleName();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    LoadingDialogUtils.getDefault().destroy(getActivity());
  }

  protected void showLoadingDialog(String content) {
    if (LoadingDialogUtils.getDefault().isSHowing()) {
      return;
    }
    LoadingDialogUtils.getDefault().showLoadDialog(getActivity(), content);
  }

  protected void hideLoadingDialog() {
    LoadingDialogUtils.getDefault().dismiss();
  }
}
