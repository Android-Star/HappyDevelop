package com.unisound.sdk.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class UserPreferenceUtils {
  public static final int TTS_MALE = 0;
  public static final int TTS_FEMALE = 1;
  public static final int TTS_BOY = 2;
  public static final int TTS_GIRL = 3;
  public static final String TTS_MALE_SUFFIX = "male";
  public static final String TTS_FEMALE_SUFFIX = "sweet";
  public static final String TTS_BOY_SUFFIX = "boy";
  public static final String TTS_GIRL_SUFFIX = "girl";
  private static final String SP_USE_DEFAULT_TTS_MODEL = "default_tts_model";

  public static final String FLUSH_TOKEN = "FLUSH_TOKEN";
  public static final String FLUSH_VALID_TIME = "FLUSH_VALID_TIME";
  public static final String FLUSH_REFRESH_TIME = "FLUSH_REFRESH_TIME";

  public static final String USER_URL = "USER_URL";
  public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
  public static final String ACCESS_VALID_TIME = "ACCESS_VALID_TIME";
  public static final String ACCESS_REFRESH_TIME = "ACCESS_REFRESH_TIME";

  public static final String DEVICE_URL = "DEVICE_URL";
  public static final String DEVICE_TOKEN = "DEVICE_TOKEN";
  public static final String DEVICE_VALID_TIME = "DEVICE_VALID_TIME";
  public static final String DEVICE_REFRESH_TIME = "DEVICE_REFRESH_TIME";

  public static final String DEVICE_ADMIN_UID = "DEVICE_ADMIN_UID";

  private UserPreferenceUtils() {

  }

  public static String getPcmFileSuffix() {
    String pcmSuffix;
    int ttsModel = UserPreferenceUtils.getUseTTSModel();
    switch (ttsModel) {
      case TTS_MALE:
        pcmSuffix = TTS_MALE_SUFFIX;
        break;
      case TTS_FEMALE:
        pcmSuffix = TTS_FEMALE_SUFFIX;
        break;
      case TTS_BOY:
        pcmSuffix = TTS_BOY_SUFFIX;
        break;
      case TTS_GIRL:
        pcmSuffix = TTS_GIRL_SUFFIX;
        break;
      default:
        pcmSuffix = TTS_FEMALE_SUFFIX;
        break;
    }
    return pcmSuffix;
  }

  public static int getUseTTSModel() {
    return SharedPreferencesHelper.getInstance()
        .getIntValue(SP_USE_DEFAULT_TTS_MODEL, SdkConfig.getInstance().getDefaultTts());
  }

  public static List<String> getMainWakeupWord() {
    HashSet<String> wakeupWord = new HashSet<>();
    wakeupWord.add("你好聪聪");
    List<String> wakeUpWordList = new ArrayList<>();
    wakeUpWordList.addAll(wakeupWord);
    return wakeUpWordList;
  }

  public static List<String> getWakeupWord() {
    List<String> allWakeUpWords = new ArrayList<>();
    List<String> mainWakeUpWords = getMainWakeupWord();
    List<String> otherWakeUpWord = new ArrayList<>();
    otherWakeUpWord.addAll(Arrays.asList(
        ContextUtils.getContext().getResources().getStringArray(R.array.wake_up_camera)));
    allWakeUpWords.addAll(mainWakeUpWords);
    allWakeUpWords.addAll(otherWakeUpWord);
    return allWakeUpWords;
  }

  public static void setUseTTSModel(int ttsModel) {
    SharedPreferencesHelper.getInstance().saveIntValue(SP_USE_DEFAULT_TTS_MODEL, ttsModel);
  }
}
