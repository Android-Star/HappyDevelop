package com.example.wilsonhan.happydevelop.ui.contract;

import com.example.wilsonhan.happydevelop.base.BaseView;
import com.example.wilsonhan.happydevelop.net.BaseResponse;
import com.example.wilsonhan.happydevelop.net.response.GetApkVersionResponse;
import com.example.wilsonhan.happydevelop.net.response.ImageResponse;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class FlashContract {
  public interface View extends BaseView {
    void showDownloadView();

    void updateDownloadProgress(long progress);
  }

  public interface Model {
    Observable<BaseResponse<GetApkVersionResponse>> checkApkVersion();

    Observable<BaseResponse<ImageResponse>> getUpdateImage();

    Observable<ResponseBody> downLoadFile(String fileUrl, String range);
  }

  public interface Presenter {

    void dealImage();

    void checkApkVersion();

    void getHomeImage();

    void doDownload(String url, String md5);

    void gotoMain();

    void doOnResume();

    void release();
  }
}
