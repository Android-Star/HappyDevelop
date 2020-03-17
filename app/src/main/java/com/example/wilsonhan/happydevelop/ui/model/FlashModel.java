package com.example.wilsonhan.happydevelop.ui.model;

import com.example.wilsonhan.happydevelop.net.BaseRequest;
import com.example.wilsonhan.happydevelop.net.BaseResponse;
import com.example.wilsonhan.happydevelop.net.response.GetApkVersionResponse;
import com.example.wilsonhan.happydevelop.net.response.ImageResponse;
import com.example.wilsonhan.happydevelop.ui.contract.FlashContract;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class FlashModel implements FlashContract.Model {
  @Override public Observable<BaseResponse<GetApkVersionResponse>> checkApkVersion() {
    return BaseRequest.getInstance().getApiService().checkApkVersion();
  }

  @Override public Observable<BaseResponse<ImageResponse>> getUpdateImage() {
    return BaseRequest.getInstance().getApiService().getUpdateImage();
  }

  @Override public Observable<ResponseBody> downLoadFile(String fileUrl, String range) {
    return BaseRequest.getInstance().getApiService().downLoadFile(fileUrl, range);
  }
}
