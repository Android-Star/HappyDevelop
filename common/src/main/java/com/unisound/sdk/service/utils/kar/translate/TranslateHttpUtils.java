package com.unisound.sdk.service.utils.kar.translate;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.device.DeviceHttpUtils;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;
import com.unisound.sdk.service.utils.kar.translate.response.AudioMeanBean;
import com.unisound.sdk.service.utils.kar.translate.response.SentenceMeanBean;
import com.unisound.sdk.service.utils.kar.translate.response.WordInfoBean;
import com.unisound.sdk.service.utils.kar.translate.response.WordListInfoBean;
import com.unisound.sdk.service.utils.kar.translate.response.WordMeanBean;
import java.util.HashMap;
import java.util.List;

import static com.unisound.sdk.service.utils.http.HttpUtils.MEDIA_TYPE_JSON;

public class TranslateHttpUtils {
  private static final String TAG = "TranslateHttpUtils";
  private static String baseUrl = UrlConstant.getInstance().getKarAppServerUrl();
  private static String version = "1.0.2";

  private static final String TRANSLATE_DELWORD = "kar-pro-ftfy/ftTranslate/delWord"; //删除单词
  private static final String TRANSLATE_WORDLIST = "kar-pro-ftfy/ftTranslate/getWordList";
  //获得单词列表-从id开始（不包含id）
  private static final String TRANSLATE_WORDLIST_BYPAGE =
      "kar-pro-ftfy/ftTranslate/getWordListByPage";
  //获得单词列表-分页
  private static final String TRANSLATE_INSERTWORD = "kar-pro-ftfy/ftTranslate/insertWord";
  //收藏单词
  private static final String TRANSLATE_SENTENCE = "kar-pro-ftfy/ftTranslate/sentence";
  //句子翻译
  private static final String TRANSLATE_WROD = "kar-pro-ftfy/ftTranslate/word"; //单词翻译
  private static final String AUDIO_TRANSLATE_WROD = "kar-pro-ftfy/ftTranslate/audioTranslate";
  //语音句子翻译

  public static final int WORD_HAS_COLLECTED = 100203; //单词已经收藏
  public static final int RECORD_EMPTY = 100303; //空语音
  public static final int RECORD_EMPTY2 = 100301;

  private TranslateHttpUtils() {

  }

  public static void setVersion(String version) {
    TranslateHttpUtils.version = version;
  }

  public static void setBaseUrl(String baseUrl) {
    TranslateHttpUtils.baseUrl = baseUrl;
  }

  public static void deleteWord(final String bindingId, final String[] idList,
      final ResponseCallBack<KarBaseResponse<Integer>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + TRANSLATE_DELWORD;
        KarBaseRequest<TranslateWord> wordTranslate = new KarBaseRequest<>();
        wordTranslate.setBusinessType(TRANSLATE_DELWORD.split("/")[0]);
        wordTranslate.setCommand(TRANSLATE_DELWORD.split("/")[1]);
        wordTranslate.setVersion(version);
        wordTranslate.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        TranslateWord translateWord = new TranslateWord();
        translateWord.setBindingId(bindingId);
        translateWord.setIdList(idList);
        wordTranslate.setData(translateWord);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(wordTranslate), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getWordListByPage(final String bindingId, final int pageNum,
      final int pageSize, final ResponseCallBack<KarBaseResponse<List<WordInfoBean>>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + TRANSLATE_WORDLIST_BYPAGE;
        KarBaseRequest<TranslateWord> wordTranslate = new KarBaseRequest<>();
        wordTranslate.setBusinessType(TRANSLATE_WORDLIST_BYPAGE.split("/")[0]);
        wordTranslate.setCommand(TRANSLATE_WORDLIST_BYPAGE.split("/")[1]);
        wordTranslate.setVersion(version);
        wordTranslate.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        TranslateWord translateWord = new TranslateWord();
        translateWord.setBindingId(bindingId);
        translateWord.setPageNum(pageNum);
        translateWord.setPageSize(pageSize);
        wordTranslate.setData(translateWord);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(wordTranslate), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void getWordList(final String bindingId, final long startId, final int num,
      final ResponseCallBack<KarBaseResponse<WordListInfoBean>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + TRANSLATE_WORDLIST;
        KarBaseRequest<TranslateWord> wordTranslate = new KarBaseRequest<>();
        wordTranslate.setBusinessType(TRANSLATE_WORDLIST.split("/")[0]);
        wordTranslate.setCommand(TRANSLATE_WORDLIST.split("/")[1]);
        wordTranslate.setVersion(version);
        wordTranslate.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        TranslateWord translateWord = new TranslateWord();
        translateWord.setBindingId(bindingId);
        translateWord.setStartId(startId);
        translateWord.setNum(num);
        wordTranslate.setData(translateWord);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(wordTranslate), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void insertWord(final String bindingId, final String word, final Language origin,
      final Language target, final ResponseCallBack<KarBaseResponse<String>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + TRANSLATE_INSERTWORD;
        KarBaseRequest<TranslateWord> wordTranslate = new KarBaseRequest<>();
        wordTranslate.setBusinessType(TRANSLATE_INSERTWORD.split("/")[0]);
        wordTranslate.setCommand(TRANSLATE_INSERTWORD.split("/")[1]);
        wordTranslate.setVersion(version);
        wordTranslate.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        TranslateWord translateWord = new TranslateWord();
        translateWord.setBindingId(bindingId);
        translateWord.setWord(word);
        translateWord.setOrigin(origin.toString());
        translateWord.setTarget(target.toString());
        wordTranslate.setData(translateWord);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(wordTranslate), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void translateSentence(final String sentence, final Language origin,
      final Language target, final ResponseCallBack<KarBaseResponse<SentenceMeanBean>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + TRANSLATE_SENTENCE;
        KarBaseRequest<TranslateWord> wordTranslate = new KarBaseRequest<>();
        wordTranslate.setBusinessType(TRANSLATE_SENTENCE.split("/")[0]);
        wordTranslate.setCommand(TRANSLATE_SENTENCE.split("/")[1]);
        wordTranslate.setVersion(version);
        wordTranslate.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        TranslateWord translateWord = new TranslateWord();
        translateWord.setQuery(sentence);
        translateWord.setOrigin(origin.toString());
        translateWord.setTarget(target.toString());
        wordTranslate.setData(translateWord);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(wordTranslate), true,
            callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void translateWord(final String word, final String bindingId, final Language origin, final Language target,
      final ResponseCallBack<KarBaseResponse<WordMeanBean>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + TRANSLATE_WROD;
        KarBaseRequest<TranslateWord> wordTranslate = new KarBaseRequest<>();
        wordTranslate.setBusinessType(TRANSLATE_WROD.split("/")[0]);
        wordTranslate.setCommand(TRANSLATE_WROD.split("/")[1]);
        wordTranslate.setVersion(version);
        wordTranslate.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        TranslateWord translateWord = new TranslateWord();
        translateWord.setQuery(word);
        translateWord.setBindingId(bindingId);
        translateWord.setOrigin(origin.toString());
        translateWord.setTarget(target.toString());
        wordTranslate.setData(translateWord);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(wordTranslate), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void translateWordByAudio(final HashMap<String, Object> param, final Language origin,
      final Language target, final ResponseCallBack<KarBaseResponse<AudioMeanBean>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + AUDIO_TRANSLATE_WROD;
        KarBaseRequest<TranslateWord> wordTranslate = new KarBaseRequest<>();
        wordTranslate.setBusinessType(AUDIO_TRANSLATE_WROD.split("/")[0]);
        wordTranslate.setCommand(AUDIO_TRANSLATE_WROD.split("/")[1]);
        wordTranslate.setVersion(version);
        wordTranslate.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        TranslateWord translateWord = new TranslateWord();
        translateWord.setOrigin(origin.toString());
        translateWord.setTarget(target.toString());
        translateWord.setUdid(SystemUtils.getDeviceId());
        translateWord.setImei("");
        wordTranslate.setData(translateWord);
        param.put("baseParam", JsonTool.toJson(wordTranslate));
        HttpUtils.post(TAG, url, null, param, true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {

      }
    });
  }

  public static void cancelAll() {
    HttpUtils.cancel(TAG);
  }
}
