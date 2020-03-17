package com.example.wilsonhan.happydevelop.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.smartphone.R;
import com.unisound.smartphone.bean.EventBean;
import com.unisound.smartphone.ui.view.LoadingDialogUtils;
import com.unisound.smartphone.ui.view.TitleView;
import com.unisound.smartphone.utils.EventBusUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity
    implements BaseView {
  private static final String TAG = "BaseActivity";
  @Nullable @BindView(R.id.titleView) protected TitleView titleView;
  protected Unbinder unbinder;
  protected ImmersionBar immersionBar;
  protected boolean isResume = false;
  private static final float DESIGN_DP = 400f;
  private static final float DESIGN_DP_LANDSCAPE = 640f;
  protected Handler handler = new Handler(Looper.getMainLooper());
  private static float sNonCompatDensity = 0;
  private static float sNonCompatScaledDensity = 0;
  protected P presenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    doBeforeSetView();
    setContentView(getLayoutId());
    unbinder = ButterKnife.bind(this);
    createPresenter();
    if (presenter != null) {
      presenter.attachBaseView(this);
    }
    if (enableImmersionBar()) {
      initImmersionBar();
    }
    setCustomDensity(this, this.getApplication());
    if (enableEventBus()) {
      LogMgr.d(TAG, "eventBus register");
      EventBusUtils.register(this);
    }
    if (titleView != null) {
      titleView.getRlTitleBack().setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          finish();
        }
      });
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

  protected void doBeforeSetView() {
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
  }

  private static void setCustomDensity(Activity activity, final Application application) {
    Configuration mConfiguration = activity.getResources().getConfiguration();
    final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
    if (sNonCompatDensity == 0) {
      sNonCompatDensity = appDisplayMetrics.density;
      sNonCompatScaledDensity = appDisplayMetrics.scaledDensity;
      application.registerComponentCallbacks(new ComponentCallbacks() {
        @Override public void onConfigurationChanged(Configuration newConfig) {
          if (newConfig != null && newConfig.fontScale > 0) {
            sNonCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
          }
        }

        @Override public void onLowMemory() {

        }
      });
    }
    final float targetDensity = appDisplayMetrics.widthPixels / (
        mConfiguration.orientation == mConfiguration.ORIENTATION_LANDSCAPE ? DESIGN_DP_LANDSCAPE
            : DESIGN_DP);
    final float targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity);
    final int targetDensityDpi = (int) (160 * targetDensity);
    appDisplayMetrics.density = targetDensity;
    appDisplayMetrics.scaledDensity = targetScaledDensity;
    appDisplayMetrics.densityDpi = targetDensityDpi;
    final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
    activityDisplayMetrics.density = targetDensity;
    activityDisplayMetrics.scaledDensity = targetScaledDensity;
    activityDisplayMetrics.densityDpi = targetDensityDpi;
  }

  protected void initImmersionBar() {
    immersionBar = ImmersionBar.with(this)
        .fitsSystemWindows(true)
        .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
        .statusBarColor(R.color.white);
    if (immersionBar.isSupportStatusBarDarkFont()) {
      immersionBar.statusBarDarkFont(true);
    } else {
      immersionBar.statusBarAlpha(0.1f);
    }
    immersionBar.init();
  }

  protected abstract int getLayoutId();

  protected void createPresenter() {

  }

  protected boolean enableImmersionBar() {
    return true;
  }

  protected void setStatusBarPadding(View view) {
    immersionBar.fitsSystemWindows(false)
        .titleBar(view)
        .statusBarColor(R.color.transparent)
        .statusBarDarkFont(false)
        .init();
  }

  protected void setStatusBarPadding(View view, boolean darkFont) {
    immersionBar.fitsSystemWindows(false).titleBar(view).statusBarColor(R.color.transparent);
    if (darkFont) {
      if (immersionBar.isSupportStatusBarDarkFont()) {
        immersionBar.statusBarDarkFont(true);
      } else {
        immersionBar.statusBarAlpha(0.1f);
      }
    } else {
      immersionBar.statusBarDarkFont(false);
      immersionBar.statusBarAlpha(0f);
    }
    immersionBar.init();
  }

  protected void setDarkStatus(boolean dark) {
    ImmersionBar immersionBar = ImmersionBar.with(this);
    if (dark) {
      if (immersionBar.isSupportStatusBarDarkFont()) {
        immersionBar.statusBarDarkFont(true);
      } else {
        immersionBar.statusBarAlpha(0.1f);
      }
    } else {
      immersionBar.statusBarDarkFont(false);
      immersionBar.statusBarAlpha(0f);
    }
    immersionBar.init();
  }

  @Override protected void onResume() {
    isResume = true;
    super.onResume();
    LogMgr.d(TAG, "onResume");
    //FloatWindowManager.getInstance().dismissWindow();
    if (enableImmersionBar()) {
      initImmersionBar();
    }
  }

  public boolean isResume() {
    return isResume;
  }

  @Override protected void onPause() {
    isResume = false;
    super.onPause();
  }

  public void softInputMethod(boolean show) {
    try {
      InputMethodManager inputMethodManager =
          ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
      if (show) {
        inputMethodManager.showSoftInput(null, InputMethodManager.SHOW_FORCED);
      } else {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
          inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(),
              InputMethodManager.HIDE_NOT_ALWAYS);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override public void showLoadingDialog(String msg) {
    if (LoadingDialogUtils.getDefault().isSHowing()) {
      return;
    }
    LoadingDialogUtils.getDefault().showLoadDialog(this, msg);
  }

  @Override public void hideLoadingDialog() {
    LoadingDialogUtils.getDefault().dismiss();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ImmersionBar.with(this).destroy();
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }
    if (enableEventBus()) {
      EventBusUtils.unregister(this);
    }

    if (presenter != null) {
      presenter.detachBaseView();
    }
    LogMgr.d(TAG, "onDestroy:" + getClassName());
  }

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
      View v = getCurrentFocus();
      if (isShouldHideInput(v, ev)) {
        InputMethodManager imm =
            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
          imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
      }
      return super.dispatchTouchEvent(ev);
    }
    if (getWindow().superDispatchTouchEvent(ev)) {
      return true;
    }
    return onTouchEvent(ev);
  }

  public boolean isShouldHideInput(View view, MotionEvent event) {
    if ((view instanceof EditText)) {
      int[] leftTop = { 0, 0 };
      view.getLocationInWindow(leftTop);
      int left = leftTop[0];
      int top = leftTop[1];
      int bottom = top + view.getHeight();
      int right = left + view.getWidth();
      if (!(event.getX() > left && event.getX() < right && event.getY() > top
          && event.getY() < bottom)) {
        return true;
      }
    }
    return false;
  }

  @Override public void onBackPressed() {
    finish();
  }

  public String getClassName() {
    return this.getClass().getSimpleName();
  }
}
