package com.unisound.sdk.service.utils.kar.fruitrecognize;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.device.DeviceHttpUtils;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;
import com.unisound.sdk.service.utils.kar.fruitrecognize.request.FruitRequest;
import com.unisound.sdk.service.utils.kar.fruitrecognize.response.FruitThridResponse;
import java.util.HashMap;

public class FruitRecognizeUitls {
  private static final String TAG = "FruitRecognizeUitls";

  private FruitRecognizeUitls() {

  }
  private static String baseUrl = UrlConstant.getInstance().getKarAppServerUrl();
  private static final String FRUIT_IDENTIFY = "kar-pro-wpsb/fruit/identify";

  public static void fruitIdentify(final String tag, final HashMap<String, Object> param,
      final long deviceId, final ResponseCallBack<KarBaseResponse<FruitThridResponse>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + FRUIT_IDENTIFY;
        KarBaseRequest baseRequest = new KarBaseRequest();
        FruitRequest request = new FruitRequest();
        request.setDeviceId(deviceId);
        baseRequest.setData(request);
        baseRequest.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        param.put("baseParam", JsonTool.toJson(baseRequest));
        HttpUtils.post(TAG + tag, url, null, param, true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void cancelByTag(String tag) {
    HttpUtils.cancel(TAG + tag);
  }
}
