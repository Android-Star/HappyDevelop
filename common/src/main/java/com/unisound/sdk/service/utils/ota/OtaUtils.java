package com.unisound.sdk.service.utils.ota;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.account.UserLoginUtils;
import com.unisound.sdk.service.utils.account.callback.UserCheckLoginCallBack;
import com.unisound.sdk.service.utils.basebean.BaseRequestHeader;
import com.unisound.sdk.service.utils.basebean.BaseResponse;
import com.unisound.sdk.service.utils.basebean.EffectiveToken;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.ota.bean.OtaBaseResponse;
import com.unisound.sdk.service.utils.ota.bean.OtaChipVersion;
import com.unisound.sdk.service.utils.ota.bean.OtaMsgBean;
import com.unisound.sdk.service.utils.ota.bean.OtaResult;
import com.unisound.sdk.service.utils.ota.bean.OtaVersion;

public final class OtaUtils {

  private static final String TAG = "OtaUtils";

  //没有更新
  public static final String CODE_NO_UPDATE = "0000";

  //有更新
  public static final String CODE_NEED_UPDATE = "0001";

  //请求时间戳超出了请求有效期
  public static final String CODE_TIMESTAMP_OUT_TIME = "1000";

  //签名错误
  public static final String CODE_SIGNATURE_ERROR = "1001";

  //其他错误
  public static final String CODE_OTHER_ERROR = "9999";

  private static final String SELF_DEFINE_SERVICE =
      UrlConstant.getInstance().getAppServerUrl() + "selfDefinedService";

  private OtaUtils() {
  }

  public static void queryChipVersion(final OtaMsgBean oatMsgBean, final String udid,
      final ResponseCallBack<BaseResponse<OtaMsgBean>> callBack) {
    UserLoginUtils.getToken(false, new UserCheckLoginCallBack() {
      @Override public void checkLoginSuccess(String accessToken) {
        EffectiveToken effectiveToken = new EffectiveToken();
        effectiveToken.setToken(accessToken);
        BaseRequestHeader<OtaMsgBean> baseRequestHeader = new BaseRequestHeader();
        baseRequestHeader.setBusinessType(Constant.SELF_DEFINE_MANAGER);
        baseRequestHeader.setCommand(Constant.SELF_DEFINED_SERVICE);
        baseRequestHeader.setProtocolType("userDefined");
        BaseRequestHeader.Promise promise = new BaseRequestHeader.Promise();
        promise.setUdid(udid);
        baseRequestHeader.setPromise(promise);
        baseRequestHeader.setData(oatMsgBean);
        baseRequestHeader.setTcl(effectiveToken);
        HttpUtils.post(TAG, SELF_DEFINE_SERVICE, null, HttpUtils.MEDIA_TYPE_JSON,
            JsonTool.toJson(baseRequestHeader), true, callBack);
      }

      @Override public void checkLoginFail(String ret) {
        callBack.onError("get token fail");
      }
    });
  }

  public static void checkChipVersion(OtaChipVersion otaChipVersion,
      final ResponseCallBack<OtaBaseResponse<OtaResult>> callBack) {
    otaChipVersion.setSignature(otaChipVersion.generateSignature());
    String param = otaChipVersion.formatParam();
    HttpUtils.post(TAG, UrlConstant.getInstance().getChipOtaServerUrl(), null,
        HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void checkVersion(OtaVersion versionInfo, boolean callBackOnMainThread,
      final ResponseCallBack<OtaBaseResponse<OtaResult>> callBack) {
    String param = versionInfo.getHttpParam();
    HttpUtils.post(TAG, UrlConstant.getInstance().getOtaServerUrl(), null, HttpUtils.MEDIA_TYPE_WWW,
        param, callBackOnMainThread, callBack);
  }

  public static void cancel() {
    HttpUtils.cancel(TAG);
  }
}
