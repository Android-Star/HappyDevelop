package com.unisound.sdk.service.utils.account;

import android.text.TextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.SharedPreferencesHelper;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.UserPreferenceUtils;
import com.unisound.sdk.service.utils.account.bean.AccessTokenResponse;
import com.unisound.sdk.service.utils.account.bean.AccountBaseResponse;
import com.unisound.sdk.service.utils.account.bean.LoginByPasswordResponse;
import com.unisound.sdk.service.utils.account.bean.PhoneCodeHeader;
import com.unisound.sdk.service.utils.account.bean.RegisterResponse;
import com.unisound.sdk.service.utils.account.bean.UserAccountHeader;
import com.unisound.sdk.service.utils.account.bean.UserInfoResponse;
import com.unisound.sdk.service.utils.account.bean.UserTokenHeader;
import com.unisound.sdk.service.utils.account.callback.UserCheckLoginCallBack;
import com.unisound.sdk.service.utils.account.callback.UserFlushTokenInvalid;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import java.util.ArrayList;
import java.util.List;

public class UserLoginUtils {
  private UserLoginUtils() {

  }

  private static final String TAG = "UserLoginUtils";
  private static final String GET_ACCESS_TOKEN_URL = "token/get_access_token";
  private static final String USER_EXIST_URL = "user/is_user_exist";
  private static final String LOGIN_URL = "user/login";
  private static final String LOGOUT_URL = "user/logout";
  private static final String GET_USER_INFO = "user/get_user_info";
  private static final String SEND_SMS_URL = "phone/send_phone_code";
  private static final String CHECK_PHONE_CODE_URL = "phone/validate_phone_code";
  private static final String REGISTER_URL = "phone/register_user";
  private static final String RESET_PWD_URL = "phone/reset_pwd";
  private static final String TIME_URL = "http://uc.hivoice.cn/timestamp.jsp";
  private static UserFlushTokenInvalid invalidFlushCallBack;

  public static void setUserFlushTokenInvalid(UserFlushTokenInvalid callBack) {
    invalidFlushCallBack = callBack;
  }

  public static UserFlushTokenInvalid getUserFlushTokenInvalid() {
    return invalidFlushCallBack;
  }

  public static void removeUserFlushTokenInvalid(UserFlushTokenInvalid callBack) {
    if (invalidFlushCallBack == callBack) {
      invalidFlushCallBack = null;
    }
  }

  private static final List<String> FLUSH_TOKEN_ERROR_LIST = new ArrayList<>();

  static {
    FLUSH_TOKEN_ERROR_LIST.add("uc_0103");
    FLUSH_TOKEN_ERROR_LIST.add("uc_0105");
  }

  public static final String CODE_USE_EXIST = "uc_0206";
  public static final String CODE_USER_NOT_EXIST = "uc_0205";
  public static final String CODE_USERNAME_PASSWORD_ERROR = "uc_0207";
  public static final String CODE_FLUSHTOKEN_ERROR = "uc_0103";
  public static final String CODE_ACCESSTOKEN_ERROR = "uc_0104";
  public static final String CODE_FLUSHTOKEN_TIMEOUT = "uc_0105";
  public static final String CODE_ACCESSTOKEN_TIMEOUT = "uc_0106";

  private static final int ACCESS_DELTA = 10 * 60 * 1000;

  private static boolean hasValidToken(String url) {
    String urlPre =
        SharedPreferencesHelper.getInstance().getStringValue(UserPreferenceUtils.USER_URL, "");
    LogMgr.d(TAG, "urlPre:" + urlPre + ",url:" + url);
    if (url == null || !url.equals(urlPre)) {
      return false;
    }
    String stringValue =
        SharedPreferencesHelper.getInstance().getStringValue(UserPreferenceUtils.ACCESS_TOKEN, "");
    long validTime =
        SharedPreferencesHelper.getInstance().getLongValue(UserPreferenceUtils.ACCESS_VALID_TIME);
    long refreshTime =
        SharedPreferencesHelper.getInstance().getLongValue(UserPreferenceUtils.ACCESS_REFRESH_TIME);
    if (TextUtils.isEmpty(stringValue)) {
      return false;
    } else {
      return (refreshTime + validTime * 1000 - ACCESS_DELTA > System.currentTimeMillis());
    }
  }

  public static <T> void getToken(boolean forceRefresh,
      final UserCheckLoginCallBack userCheckLoginCallBack) {
    if (userCheckLoginCallBack == null) {
      LogMgr.d(TAG, "userCheckLoginCallBack need not null");
      return;
    }
    if (!forceRefresh) {
      if (hasValidToken(UrlConstant.getInstance().getUserCenterUrl() + GET_ACCESS_TOKEN_URL)) {
        userCheckLoginCallBack.checkLoginSuccess(SharedPreferencesHelper.getInstance()
            .getStringValue(UserPreferenceUtils.ACCESS_TOKEN, ""));
        return;
      }
    }
    String flushToken = SystemUtils.getStringFromFile(UserPreferenceUtils.FLUSH_TOKEN);
    if (TextUtils.isEmpty(flushToken)) {
      if (invalidFlushCallBack != null) {
        invalidFlushCallBack.userFlushTokenInvalid();
      }
    } else {
      getAccessToken(flushToken, new ResponseCallBack<AccountBaseResponse<AccessTokenResponse>>() {
        @Override public void onResponse(AccountBaseResponse<AccessTokenResponse> response) {
          if (response.isSuccessResult()) {
            SharedPreferencesHelper.getInstance()
                .saveStringValue(UserPreferenceUtils.USER_URL,
                    UrlConstant.getInstance().getUserCenterUrl() + GET_ACCESS_TOKEN_URL);
            SharedPreferencesHelper.getInstance()
                .saveStringValue(UserPreferenceUtils.ACCESS_TOKEN,
                    response.getResult().getAccessToken());
            SharedPreferencesHelper.getInstance()
                .saveLongValue(UserPreferenceUtils.ACCESS_VALID_TIME,
                    response.getResult().getValidTime());
            SharedPreferencesHelper.getInstance()
                .saveLongValue(UserPreferenceUtils.ACCESS_REFRESH_TIME, System.currentTimeMillis());
            userCheckLoginCallBack.checkLoginSuccess(response.getResult().getAccessToken());
          } else {
            if (FLUSH_TOKEN_ERROR_LIST.contains(response.getReturnCode())) {
              if (invalidFlushCallBack != null) {
                invalidFlushCallBack.userFlushTokenInvalid();
              }
            } else {
              userCheckLoginCallBack.checkLoginFail(response.getReturnCode());
            }
          }
        }

        @Override public void onError(String error) {
          userCheckLoginCallBack.checkLoginFail(error);
        }
      });
    }
  }

  public static void getAccessToken(String flushToken,
      ResponseCallBack<AccountBaseResponse<AccessTokenResponse>> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + GET_ACCESS_TOKEN_URL;
    LogMgr.d(TAG, "getAccessToken flushToken:" + flushToken);
    LogMgr.d(TAG, "getAccessToken url:" + url);
    UserTokenHeader header = new UserTokenHeader();
    header.setFlushToken(flushToken);
    header.setSignature(header.generateSignature());
    String param = header.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void loginByPassWord(String userName, String passWord,
      ResponseCallBack<AccountBaseResponse<LoginByPasswordResponse>> callBack) {
    final String url = UrlConstant.getInstance().getUserCenterUrl() + LOGIN_URL;
    LogMgr.d(TAG, "loginByPassWord userName:" + userName + ",passWord:" + passWord);
    LogMgr.d(TAG, "loginByPassWord url:" + url);
    UserAccountHeader header = new UserAccountHeader();
    header.setAccount(userName);
    header.setPassword(passWord);
    header.setSignature(header.generateSignature());
    final String param = header.formatParam();
    LogMgr.d(TAG, "param:" + param);
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void loginByPassWordAndUdid(String userName, String passWord, String clientId,
      ResponseCallBack<AccountBaseResponse<LoginByPasswordResponse>> callBack) {
    final String url = UrlConstant.getInstance().getUserCenterUrl() + LOGIN_URL;
    LogMgr.d(TAG, "loginByPassWord userName:" + userName + ",passWord:" + passWord);
    LogMgr.d(TAG, "loginByPassWord url:" + url);
    UserAccountHeader header = new UserAccountHeader(clientId);
    header.setAccount(userName);
    header.setPassword(passWord);
    header.setSignature(header.generateSignature());
    final String param = header.formatParam();
    LogMgr.d(TAG, "param:" + param);
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void isUserExist(String userName, ResponseCallBack<AccountBaseResponse> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + USER_EXIST_URL;
    UserAccountHeader userExist = new UserAccountHeader();
    userExist.setAccount(userName);
    userExist.setSignature(userExist.generateSignature());
    String param = userExist.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void sendPhoneCode(String phoneNum,
      ResponseCallBack<AccountBaseResponse> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + SEND_SMS_URL;
    PhoneCodeHeader header = new PhoneCodeHeader();
    header.setUserCell(phoneNum);
    header.setSignature(header.generateSignature());
    String param = header.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void checkPhoneCode(String phoneNum, String phoneCode,
      ResponseCallBack<AccountBaseResponse> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + CHECK_PHONE_CODE_URL;
    PhoneCodeHeader header = new PhoneCodeHeader();
    header.setUserCell(phoneNum);
    header.setPhoneCode(phoneCode);
    header.setSignature(header.generateSignature());
    String param = header.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void registerUser(String phoneNum, String phoneCode, String passWord,
      ResponseCallBack<AccountBaseResponse<RegisterResponse>> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + REGISTER_URL;
    PhoneCodeHeader header = new PhoneCodeHeader();
    header.setUserCell(phoneNum);
    header.setPhoneCode(phoneCode);
    header.setPassword(passWord);
    header.setSignature(header.generateSignature());
    String param = header.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void resetPassWord(String phoneNum, String phoneCode, String passWord,
      ResponseCallBack<AccountBaseResponse> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + RESET_PWD_URL;
    PhoneCodeHeader header = new PhoneCodeHeader();
    header.setUserCell(phoneNum);
    header.setPhoneCode(phoneCode);
    header.setPassword(passWord);
    header.setSignature(header.generateSignature());
    String param = header.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void logOut(ResponseCallBack<AccountBaseResponse> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + LOGOUT_URL;
    UserAccountHeader userAccountHeader = new UserAccountHeader();
    userAccountHeader.setAccessToken(
        SharedPreferencesHelper.getInstance().getStringValue(UserPreferenceUtils.ACCESS_TOKEN, ""));
    userAccountHeader.setSignature(userAccountHeader.generateSignature());
    String param = userAccountHeader.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void getUserInfo(ResponseCallBack<AccountBaseResponse<UserInfoResponse>> callBack) {
    String url = UrlConstant.getInstance().getUserCenterUrl() + GET_USER_INFO;
    UserAccountHeader userAccountHeader = new UserAccountHeader();
    userAccountHeader.setAccessToken(
        SharedPreferencesHelper.getInstance().getStringValue(UserPreferenceUtils.ACCESS_TOKEN, ""));
    userAccountHeader.setSignature(userAccountHeader.generateSignature());
    String param = userAccountHeader.formatParam();
    HttpUtils.post(TAG, url, null, HttpUtils.MEDIA_TYPE_WWW, param, true, callBack);
  }

  public static void getServerTime(ResponseCallBack<Long> callBack) {
    HttpUtils.get(TAG, TIME_URL, null, true, callBack);
  }
}
