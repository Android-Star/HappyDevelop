package com.unisound.sdk.service.utils;

import android.os.Environment;
import java.io.File;
import java.util.HashMap;

public class TtsFileUtils {

  private static final String TAG = "TtsFileUtils";
  public static final String TTS_PCM_PATH = "tts_pcm";

  private static final HashMap<String, String> TTS_MAP = new HashMap<>();

  static {
    TTS_MAP.put("抱歉,播放列表中没有歌曲", "1");
    TTS_MAP.put("抱歉,播放列表中只有一首歌曲", "2");
    TTS_MAP.put("抱歉,没有查询到天气信息", "3");
    TTS_MAP.put("抱歉,没有找到相关视频", "4");
    TTS_MAP.put("抱歉,您说的暂不支持", "5");
    TTS_MAP.put("抱歉,设置闹钟失败", "6");
    TTS_MAP.put("抱歉,设置提醒失败", "7");
    TTS_MAP.put("抱歉,暂不支持收藏功能", "8");
    TTS_MAP.put("不存在这个闹钟哦~", "9");
    TTS_MAP.put("当前播放的是新闻节目", "10");
    TTS_MAP.put("对不起,我还不明白你说的,换个其他问题吧", "11");
    TTS_MAP.put("歌曲未收藏", "12");
    TTS_MAP.put("歌曲已经收藏", "13");
    TTS_MAP.put("好的，单曲播放", "14");
    TTS_MAP.put("好的，单曲循环", "15");
    TTS_MAP.put("好的,马上为您收藏", "16");
    TTS_MAP.put("好的,那我先退下了,有需要在喊我", "17");
    TTS_MAP.put("好的，顺序播放", "18");
    TTS_MAP.put("好的，随机播放", "19");
    TTS_MAP.put("好的,我在", "20");
    TTS_MAP.put("好的，循环播放", "21");
    TTS_MAP.put("好的,已经为你取消收藏", "22");
    TTS_MAP.put("好的", "23");
    TTS_MAP.put("好像没有连接网络哦,请先帮我联网吧", "24");
    TTS_MAP.put("来了来了", "25");
    TTS_MAP.put("没问题", "26");
    TTS_MAP.put("没有找到这个应用", "27");
    TTS_MAP.put("取消收藏失败", "28");
    TTS_MAP.put("实在找不到，请您换个试试吧", "29");
    TTS_MAP.put("收藏失败", "30");
    TTS_MAP.put("我好像没有听到你说话哦,有需要在喊我", "31");
    TTS_MAP.put("已经删除全部闹钟", "32");
    TTS_MAP.put("已退出", "33");
    TTS_MAP.put("已暂停", "34");
    TTS_MAP.put("主人,当前没有设置闹钟哦", "35");
    TTS_MAP.put("主人,请选择正确的闹钟哦", "36");
    TTS_MAP.put("主人,要删除第几个闹钟呢", "37");
    TTS_MAP.put("主人,已经设置过这个闹钟了", "38");
    TTS_MAP.put("遵旨", "39");
    TTS_MAP.put("我在哦,请说", "40");
    TTS_MAP.put("小朋友好", "41");
    TTS_MAP.put("找我干啥", "42");
    TTS_MAP.put("小主人,你好", "43");
    TTS_MAP.put("主人好", "44");
  }

  private TtsFileUtils() {

  }

  public static boolean init() {
    boolean result = true;
    try {
      String[] folderList = ContextUtils.getContext().getAssets().list(TTS_PCM_PATH);
      if (folderList != null && folderList.length > 0) {
        for (String file : folderList) {
          LogMgr.d(TAG, "copy:" + TTS_PCM_PATH + File.separator + file);
          result = result && AssetsUtils.copyAssetsDirectory(TTS_PCM_PATH + File.separator + file,
              Environment.getExternalStorageDirectory() + File.separator + TTS_PCM_PATH
                  + File.separator + file, false, true, true);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      result = false;
    }
    LogMgr.d(TAG, "init result:" + result);
    return result;
  }

  public static String getTtsPath(String tts) {
    if (TTS_MAP.containsKey(tts)) {
      String fileName = TTS_MAP.get(tts);
      String path =
          Environment.getExternalStorageDirectory() + File.separator + TTS_PCM_PATH + File.separator
              + UserPreferenceUtils.getPcmFileSuffix();
      String filePcm = path + File.separator + fileName + ".pcm";
      String fileWav = path + File.separator + fileName + ".wav";
      if (new File(filePcm).exists()) {
        return "<PCM>" + filePcm + "</PCM>";
      } else if (new File(fileWav).exists()) {
        return "<WAV>" + fileWav + "</WAV>";
      }
    }
    return "";
  }
}
