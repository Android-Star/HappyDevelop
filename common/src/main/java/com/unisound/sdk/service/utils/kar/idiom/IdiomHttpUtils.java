package com.unisound.sdk.service.utils.kar.idiom;

import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.constants.Constant;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseRequest;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.device.DeviceHttpUtils;
import com.unisound.sdk.service.utils.kar.device.callback.DeviceTokenCallBack;
import com.unisound.sdk.service.utils.kar.idiom.request.EnterGameRequest;
import com.unisound.sdk.service.utils.kar.idiom.request.GameStateRequest;
import com.unisound.sdk.service.utils.kar.idiom.request.HintRequest;
import com.unisound.sdk.service.utils.kar.idiom.request.PlayGameRequest;
import com.unisound.sdk.service.utils.kar.idiom.request.RankRequest;
import com.unisound.sdk.service.utils.kar.idiom.request.WinRequest;
import com.unisound.sdk.service.utils.kar.idiom.response.EnterGameBean;
import com.unisound.sdk.service.utils.kar.idiom.response.GameStateBean;
import com.unisound.sdk.service.utils.kar.idiom.response.HintBean;
import com.unisound.sdk.service.utils.kar.idiom.response.MedalBean;
import com.unisound.sdk.service.utils.kar.idiom.response.PlayGameBean;
import com.unisound.sdk.service.utils.kar.idiom.response.RankingBean;
import com.unisound.sdk.service.utils.kar.idiom.response.WinBean;
import java.util.HashMap;
import java.util.List;

import static com.unisound.sdk.service.utils.http.HttpUtils.MEDIA_TYPE_JSON;

public class IdiomHttpUtils {
  private IdiomHttpUtils() {

  }

  private static String version = "1.0.2";
  private static final String TAG = "IdiomHttpUtils";
  private static String baseUrl = UrlConstant.getInstance().getKarAppServerUrl();
  private static final String IDIOM_RANKING = "kar-pro-cyjl/cyjl/top"; //词语接龙游戏排行榜
  private static final String IDIOM_MEDAL = "kar-pro-cyjl/cyjl/allMedal"; //获得奖章列表
  private static final String IDIOM_FIND_GAME_STATE = "kar-pro-cyjl/cyjl/findGameState ";
  //获得用户游戏进度
  private static final String IDIOM_ENTER_GAME = "kar-pro-cyjl/cyjl/enterGame"; //进入第N关
  private static final String IDIOM_PLAY_GAME = "kar-pro-cyjl/cyjl/play"; //词语接龙游戏语音输入
  private static final String IDIOM_WIN = "kar-pro-cyjl/cyjl/win"; //过关成功
  private static final String IDIOM_HINT = "kar-pro-cyjl/cyjl/hint"; //提示操作
  private static final String IDIOM_SKIP = "kar-pro-cyjl/cyjl/pass"; //词语跳过操作

  public static void rankingList(final int num,
      final ResponseCallBack<KarBaseResponse<List<RankingBean>>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_RANKING;
        KarBaseRequest<RankRequest> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_RANKING.split("/")[0]);
        idiomBase.setCommand(IDIOM_RANKING.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        RankRequest rankRequest = new RankRequest();
        rankRequest.setN(num);
        idiomBase.setData(rankRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(idiomBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void allMedal(final ResponseCallBack<KarBaseResponse<List<MedalBean>>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_MEDAL;
        KarBaseRequest<Object> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_MEDAL.split("/")[0]);
        idiomBase.setCommand(IDIOM_MEDAL.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        idiomBase.setData(new Object());
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(idiomBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void findGameState(
      final ResponseCallBack<KarBaseResponse<GameStateBean>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_FIND_GAME_STATE;
        KarBaseRequest<GameStateRequest> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_FIND_GAME_STATE.split("/")[0]);
        idiomBase.setCommand(IDIOM_FIND_GAME_STATE.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        GameStateRequest gameStateRequest = new GameStateRequest();
        gameStateRequest.setNeedRanking(true);
        String deviceId = SystemUtils.getDeviceId();
        LogMgr.d(TAG, "deviceId:" + deviceId);
        gameStateRequest.setUdid(deviceId);
        idiomBase.setData(gameStateRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(idiomBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void enterGame(final long gameStateId, final int level, final long randomSeed,
      final ResponseCallBack<KarBaseResponse<EnterGameBean>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_ENTER_GAME;
        KarBaseRequest<EnterGameRequest> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_ENTER_GAME.split("/")[0]);
        idiomBase.setCommand(IDIOM_ENTER_GAME.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        EnterGameRequest enterGameRequest = new EnterGameRequest();
        enterGameRequest.setGameStateId(gameStateId);
        enterGameRequest.setLevel(level);
        enterGameRequest.setRandomSeed(randomSeed);
        idiomBase.setData(enterGameRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(idiomBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void playGame(final HashMap<String, Object> param, final String lastWord,
      final ResponseCallBack<KarBaseResponse<PlayGameBean>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_PLAY_GAME;
        KarBaseRequest<PlayGameRequest> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_PLAY_GAME.split("/")[0]);
        idiomBase.setCommand(IDIOM_PLAY_GAME.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        PlayGameRequest playGameRequest = new PlayGameRequest();
        playGameRequest.setLastWord(lastWord);
        String deviceId = SystemUtils.getDeviceId();
        LogMgr.d(TAG, "deviceId:" + deviceId);
        playGameRequest.setUdid(deviceId);
        playGameRequest.setImei("");
        idiomBase.setData(playGameRequest);
        param.put("baseParam", JsonTool.toJson(idiomBase));
        HttpUtils.post(TAG, url, null, param, true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void win(final long gameStateId, final int level,
      final ResponseCallBack<KarBaseResponse<WinBean>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_WIN;
        KarBaseRequest<WinRequest> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_WIN.split("/")[0]);
        idiomBase.setCommand(IDIOM_WIN.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        WinRequest winRequest = new WinRequest();
        winRequest.setGameStateId(gameStateId);
        winRequest.setLevel(level);
        idiomBase.setData(winRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(idiomBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void hint(final int currentLevel, final long gameStateId, final String lastWord,
      final ResponseCallBack<KarBaseResponse<HintBean>> callBack) {

    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_HINT;
        KarBaseRequest<HintRequest> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_HINT.split("/")[0]);
        idiomBase.setCommand(IDIOM_HINT.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HintRequest hintRequest = new HintRequest();
        hintRequest.setGameStateId(gameStateId);
        hintRequest.setLastWord(lastWord);
        hintRequest.setCurrentLevel(currentLevel);
        idiomBase.setData(hintRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(idiomBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void skip(final int currentLevel, final long gameStateId, final String lastWord,
      final ResponseCallBack<KarBaseResponse<HintBean>> callBack) {
    DeviceHttpUtils.getDeviceToken(false, new DeviceTokenCallBack() {
      @Override public void getDeviceTokenSuccess(String token) {
        String url = baseUrl + IDIOM_SKIP;
        KarBaseRequest<HintRequest> idiomBase = new KarBaseRequest<>();
        idiomBase.setBusinessType(IDIOM_SKIP.split("/")[0]);
        idiomBase.setCommand(IDIOM_SKIP.split("/")[1]);
        idiomBase.setVersion(version);
        idiomBase.setTcl(KarBaseRequest.TclBean.getDefaultTclBeanDevice(token));
        HintRequest hintRequest = new HintRequest();
        hintRequest.setGameStateId(gameStateId);
        hintRequest.setLastWord(lastWord);
        hintRequest.setCurrentLevel(currentLevel);
        idiomBase.setData(hintRequest);
        HttpUtils.post(TAG, url, null, MEDIA_TYPE_JSON, JsonTool.toJson(idiomBase), true, callBack);
      }

      @Override public void getDeviceTokenFail(String error) {
        callBack.onError(Constant.DEVICE_TOKEN_ERROR);
      }
    });
  }

  public static void cancel() {
    LogMgr.d(TAG, "cancel");
    HttpUtils.cancel(TAG);
  }
}

