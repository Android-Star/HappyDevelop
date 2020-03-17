package com.example.wilsonhan.happydevelop.ui.presenter;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import com.example.wilsonhan.happydevelop.base.BasePresenterImpl;
import com.example.wilsonhan.happydevelop.callback.DownLoadListener;
import com.example.wilsonhan.happydevelop.constants.Constants;
import com.example.wilsonhan.happydevelop.net.BaseObserver;
import com.example.wilsonhan.happydevelop.net.BaseResponse;
import com.example.wilsonhan.happydevelop.net.SwitchSchedulers;
import com.example.wilsonhan.happydevelop.net.response.GetApkVersionResponse;
import com.example.wilsonhan.happydevelop.net.response.ImageResponse;
import com.example.wilsonhan.happydevelop.ui.contract.FlashContract;
import com.example.wilsonhan.happydevelop.ui.model.FlashModel;
import com.example.wilsonhan.happydevelop.utils.AppUtils;
import com.example.wilsonhan.happydevelop.utils.CleanDataUtils;
import com.example.wilsonhan.happydevelop.utils.DownLoadUtils;
import com.example.wilsonhan.happydevelop.utils.FileUtils;
import com.example.wilsonhan.happydevelop.utils.SystemHelper;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.NetUtils;
import com.unisound.sdk.service.utils.SharedPreferencesHelper;
import com.unisound.sdk.service.utils.ToastUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.ResponseBody;

public class FlashPresenter extends BasePresenterImpl<FlashContract.View>
    implements FlashContract.Presenter {
  private static final String TAG = "FlashPresenter";

  private FlashModel flashModel;
  private Activity context;
  private Handler handler = new Handler();

  public FlashPresenter(Activity context) {
    flashModel = new FlashModel();
    this.context = context;
  }

  @Override public void dealImage() {

  }

  @Override public void checkApkVersion() {
    flashModel.checkApkVersion()
        .compose(SwitchSchedulers.applySchedulers(this.view))
        .subscribe(new BaseObserver<GetApkVersionResponse>() {
          @Override public void onSuccess(BaseResponse<GetApkVersionResponse> response) {
            GetApkVersionResponse data = response.getData();
            int versionCode = AppUtils.getVersionCode();
            LogMgr.d(TAG, "current versionCode :" + versionCode);
            if (data == null || versionCode >= Integer.valueOf(data.getVersionCode())) {
              gotoMain();
            } else {
              DownLoadUtils.getInstance()
                  .downloadFile(data.getMd5(), data.getApkUrl(), new DownLoadListener() {
                    @Override public void onDownLoadStart() {
                      LogMgr.d(TAG, "download start");
                      ToastUtils.showShortToast("开始下载");
                      view.showDownloadView();
                    }

                    @Override public void onDownLoadProgress(long currentLength) {
                      LogMgr.d(TAG, "download update progress" + currentLength);
                      //ToastUtils.showShortToast("当前进度" + currentLength);
                      view.updateDownloadProgress(currentLength);
                    }

                    @Override public void onDownLoadFinish(String filePath, File downloadFile) {
                      LogMgr.d(TAG, "download complete, filePath:" + filePath);
                      ToastUtils.showShortToast("下载完成");
                      try {
                        String downLoadFileMd5 = FileUtils.generateFileMD5(filePath);
                        LogMgr.d(TAG,
                            "download complete, downloadFileMd5:" + downLoadFileMd5 + ",fileMd5:"
                                + data.getMd5());
                        if (downLoadFileMd5.equals(data.getMd5())) {
                          SystemHelper.silentInstallApkByReflect(filePath);
                        }
                      } catch (Exception e) {
                        e.printStackTrace();
                      }
                    }

                    @Override public void onDownLoadFair() {
                      LogMgr.d(TAG, "download fair");
                      ToastUtils.showShortToast("更新失败，请下次启动设备时再试");
                      gotoMain();
                    }
                  });
            }
          }

          @Override public void onCodeError(BaseResponse response) {
            gotoMain();
          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {
            gotoMain();
          }
        });
  }

  @Override public void getHomeImage() {
    flashModel.getUpdateImage()
        .compose(SwitchSchedulers.applySchedulers(view))
        .subscribe(new BaseObserver<ImageResponse>() {
          @Override public void onSuccess(BaseResponse<ImageResponse> response) {
            if (response != null && response.getData() != null) {
              ImageResponse imageData = response.getData();
              String stringValue = SharedPreferencesHelper.getInstance()
                  .getStringValue(Constants.LOCAL_HOME_IMAGE_PATH, "");
              LogMgr.d(TAG, "localPath:" + stringValue);
              if (!TextUtils.isEmpty(stringValue)) {
                String localMd5 = null;
                try {
                  localMd5 = FileUtils.generateFileMD5(stringValue);
                } catch (Exception e) {
                  e.printStackTrace();
                }
                LogMgr.d(TAG, "localMd5:" + localMd5 + ",service md5:" + imageData.getMd5());
                File file = new File(stringValue);
                if (!file.exists() || !localMd5.equals(imageData.getMd5())) {
                  doDownload(imageData.getPicUrl(), imageData.getMd5());
                }
              } else {
                doDownload(imageData.getPicUrl(), imageData.getMd5());
              }
            }
          }

          @Override public void onCodeError(BaseResponse response) {

          }

          @Override public void onFailure(Throwable e, boolean netWork) throws Exception {

          }
        });
  }

  @Override public void doDownload(String url, String md5) {
    flashModel.downLoadFile(url, "bytes=" + 0 + "-")
        .compose(SwitchSchedulers.applySchedulers(view))
        .subscribe(new Observer<ResponseBody>() {
          @Override public void onError(Throwable throwable) {
          }

          @Override public void onComplete() {

          }

          @Override public void onSubscribe(Disposable disposable) {

          }

          @Override public void onNext(ResponseBody responseBody) {
            OutputStream outputStream = null;
            InputStream inputStream = responseBody.byteStream();
            String filePath = FileUtils.DATA_IMAGE_URL + File.separator + "smartPhoneHome.png";
            try {
              int index = url.lastIndexOf('/');
              if (index != -1) {
                String name = url.substring(index);
                filePath = FileUtils.DATA_IMAGE_URL + name;
              }
              File file = new File(filePath);
              outputStream = new FileOutputStream(file);
              int len;
              byte[] buffer = new byte[2048];
              while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
              }
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            } finally {
              if (outputStream != null) {
                try {
                  outputStream.close();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
              if (inputStream != null) {
                try {
                  inputStream.close();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            }
            try {
              if (!md5.equals(FileUtils.generateFileMD5(filePath))) {
                LogMgr.d(TAG, "md5 different download error");
              } else {
                LogMgr.d(TAG, "download success,filePath:" + filePath);
                SharedPreferencesHelper.getInstance()
                    .saveStringValue(Constants.LOCAL_HOME_IMAGE_PATH, filePath);
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
  }

  @Override public void gotoMain() {
    CleanDataUtils.cleanCustomCache(FileUtils.DATA_APK_URL);
    CleanDataUtils.cleanCustomCache(FileUtils.DATA_RECORD_URL);
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        //Intent intent = new Intent(context, MainActivity.class);
        //context.startActivity(intent);
        //context.finish();
      }
    }, 2000);
  }

  @Override public void doOnResume() {
    LogMgr.d(TAG, "=============flashActivity onResume");
    if (NetUtils.isNetworkAvailable()) {
      getHomeImage();
      checkApkVersion();
    }
  }

  @Override public void release() {
    LogMgr.d(TAG, "=============flashActivity onFinish");
    if (handler != null) {
      handler.removeCallbacksAndMessages(null);
    }
  }
}
