package com.unisound.sdk.service.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
  private StringUtils() {
  }

  private static final String REGEX_IS_MOBILE = "^[1][3|4|5|6|7|8|9][0-9]{9}$";
  private static final String REGEX_FIND_MOBILE = "^[1][3|4|5|6|7|8|9][0-9]{9}";
  private static final String REGEX_FIX_NUM = "^(010|02\\d|0[3-9]\\d{2})?\\d{5,8}$";
  private static final String ENGLISH = "^[a-zA-Z]*$";
  private static final String REGEX_CHINESE = "^[\\u4e00-\\u9fa5]*";

  public static boolean isEmptyStr(String str) {
    return !(str != null && str.length() > 0);
  }

  public static boolean isValidStr(String str) {
    return str != null && str.length() > 0;
  }

  public static boolean isSameStr(String str, String str2) {
    if (str == null && str2 == null) {
      return true;
    }
    if (str == null || str2 == null) {
      return false;
    }
    return str.equals(str2);
  }

  public static boolean isPhoneNum(String str) {
    Pattern p = Pattern.compile(REGEX_IS_MOBILE);
    Matcher m = p.matcher(str);
    return m.matches();
  }

  public static boolean isFixNum(String str) {
    Pattern p = Pattern.compile(REGEX_FIX_NUM);
    Matcher m = p.matcher(str);
    return m.matches();
  }

  /**
   * 获取字符串里的里的手机号码
   *
   * @param text 包含手机号的字符串
   * @return 手机号
   */
  public static String getPhoneNum(String text) {
    String regex = REGEX_FIND_MOBILE;
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(text);
    if (matcher.find()) {
      return matcher.group();
    }
    return "";
  }

  public static boolean isEnglish(String str) {
    Pattern p = Pattern.compile(ENGLISH);
    Matcher m = p.matcher(str);
    return m.matches();
  }

  public static boolean isChinese(String kinShip) {
    Pattern pattern = Pattern.compile(REGEX_CHINESE);
    Matcher matcher = pattern.matcher(kinShip);
    return matcher.matches();
  }
}
