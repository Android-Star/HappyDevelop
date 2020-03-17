package com.unisound.sdk.service.utils;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TTSUtils {
  public static final int TTS_SPEAKER_DEFAULT = 0;
  public static final int TTS_SPEAKER_MALE = 1;
  public static final int TTS_SPEAKER_FEMALE = 2;
  private static final String TAG = "TTSUtils";
  private static final String MALE_START = "[男]";
  private static final String FEMALE_START = "[女]";
  private static int speakerType = TTS_SPEAKER_DEFAULT;

  public static void setSpeakerType(int speakerType) {
    TTSUtils.speakerType = speakerType;
  }

  private static SparseIntArray stringIndex = new SparseIntArray();

  static {
  }

  private TTSUtils() {
  }

  public static String getTts(int id) {
    return ContextUtils.getContext().getResources().getString(id);
  }

  public static String getTtsInArray(int id) {
    List<String> arrayTTS =
        Arrays.asList(ContextUtils.getContext().getResources().getStringArray(id));
    List<String> arrayTTSDefault = new ArrayList<>();
    List<String> arrayTTSMale = new ArrayList<>();
    List<String> arrayTTSFemale = new ArrayList<>();
    for (String str : arrayTTS) {
      if (str.startsWith(MALE_START)) {
        str = str.replace(MALE_START, "");
        arrayTTSMale.add(str);
      } else if (str.startsWith(FEMALE_START)) {
        str = str.replace(FEMALE_START, "");
        arrayTTSFemale.add(str);
      } else {
        arrayTTSDefault.add(str);
      }
    }
    switch (speakerType) {
      case TTS_SPEAKER_DEFAULT:
        return getOrderString(id, arrayTTSDefault);
      case TTS_SPEAKER_FEMALE:
        if (arrayTTSFemale != null && arrayTTSFemale.size() > 0) {
          return getOrderString(id, arrayTTSFemale);
        }
        return getOrderString(id, arrayTTSDefault);
      case TTS_SPEAKER_MALE:
        if (arrayTTSMale != null && arrayTTSMale.size() > 0) {
          return getOrderString(id, arrayTTSMale);
        }
        return getOrderString(id, arrayTTSDefault);
      default:
        break;
    }
    return null;
  }

  public static String getOrderString(int id, List<String> inputs) {
    if (inputs == null || inputs.size() == 0) return null;
    int index = 0;
    if (stringIndex.get(id, Integer.MAX_VALUE) != Integer.MIN_VALUE) {
      index = stringIndex.get(id);
    }
    String value = inputs.get(index % inputs.size());
    index++;
    stringIndex.put(id, index);
    return value;
  }
}
