package com.unisound.sdk.service.utils;

import android.os.Environment;
import java.io.File;

public class SdkConfig {
  private static final String TAG = "SdkConfig";
  private int ttsSpeed = 70;
  private int defaultTts = 3;
  private String trAddress = "tr.hivoice.cn:80";
  private String additionalService = "kar_pro";
  private String optionFilterUrl = "https://unitoy.hivoice.cn/kar-pro-tr-callback/tr/callback";
  private float wakeupOptThresholdValueHigh = -2.18f;
  private float wakeupOptThresholdValue = -4.26f;
  private float cmdWakeupOptThresholdValue = -3.80f;
  private float wakeupOptThresholdValueLow = -7.18f;
  private String nluScenario = "child";
  private String asrDomain = "general,kar";
  private int asrOptWakeupModelId = 1054;
  private float serverVadTime = 0.4f;
  private float startSil = 15.0f;
  private String filterName = "nlu3";
  private int ttsBright = 90;
  private String nluVer = "3.0";
  private boolean debug = false;
  private int opusCompressRatio = 8;
  private boolean saveRecord = false;
  private int voiceRestrain = 500;
  private int recordBufferLength = 3200;
  private int wakeUpBufferSize = 640;
  private int asrBufferSize = 1200;
  private float voiceRestrainValue = 0.2f;
  private boolean localVadEnable = false;
  private boolean serverVadEnable = false;
  private boolean bestResultReturn = true;
  private boolean punctuated = true;
  private int falseAlarm = 4;
  private boolean confidenceMeasure = true;
  private float confidenceThreshold = 2.0f;
  private boolean retTimeOut = true;
  private float vprScore = 20.0f;
  private boolean uploadWakeUpWord = true;
  private boolean kwsLog = false;
  private int asrBunchFrame = 12;
  private String voiceField = "near";

  public String getVoiceField() {
    return voiceField;
  }

  public void setVoiceField(String voiceField) {
    this.voiceField = voiceField;
  }

  public float getCmdWakeupOptThresholdValue() {
    return cmdWakeupOptThresholdValue;
  }

  public void setCmdWakeupOptThresholdValue(float cmdWakeupOptThresholdValue) {
    this.cmdWakeupOptThresholdValue = cmdWakeupOptThresholdValue;
  }

  public float getWakeupOptThresholdValueHigh() {
    return wakeupOptThresholdValueHigh;
  }

  public void setWakeupOptThresholdValueHigh(float wakeupOptThresholdValueHigh) {
    this.wakeupOptThresholdValueHigh = wakeupOptThresholdValueHigh;
  }

  public int getAsrBunchFrame() {
    return asrBunchFrame;
  }

  public void setAsrBunchFrame(int asrBunchFrame) {
    this.asrBunchFrame = asrBunchFrame;
  }

  public boolean isKwsLog() {
    return kwsLog;
  }

  public void setKwsLog(boolean kwsLog) {
    this.kwsLog = kwsLog;
  }

  public boolean isUploadWakeUpWord() {
    return uploadWakeUpWord;
  }

  public void setUploadWakeUpWord(boolean uploadWakeUpWord) {
    this.uploadWakeUpWord = uploadWakeUpWord;
  }

  public boolean isPunctuated() {
    return punctuated;
  }

  public void setPunctuated(boolean punctuated) {
    this.punctuated = punctuated;
  }

  public boolean isBestResultReturn() {
    return bestResultReturn;
  }

  public void setBestResultReturn(boolean bestResultReturn) {
    this.bestResultReturn = bestResultReturn;
  }

  public int getAsrBufferSize() {
    return asrBufferSize;
  }

  public void setAsrBufferSize(int asrBufferSize) {
    this.asrBufferSize = asrBufferSize;
  }

  public int getWakeUpBufferSize() {
    return wakeUpBufferSize;
  }

  public void setWakeUpBufferSize(int wakeUpBufferSize) {
    this.wakeUpBufferSize = wakeUpBufferSize;
  }

  public int getRecordBufferLength() {
    return recordBufferLength;
  }

  public void setRecordBufferLength(int recordBufferLength) {
    this.recordBufferLength = recordBufferLength;
  }

  public boolean isServerVadEnable() {
    return serverVadEnable;
  }

  public void setServerVadEnable(boolean serverVadEnable) {
    this.serverVadEnable = serverVadEnable;
  }

  public String getFilterName() {
    return filterName;
  }

  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }

  public boolean isLocalVadEnable() {
    return localVadEnable;
  }

  public void setLocalVadEnable(boolean localVadEnable) {
    this.localVadEnable = localVadEnable;
  }

  public int getVoiceRestrain() {
    return voiceRestrain;
  }

  public void setVoiceRestrain(int voiceRestrain) {
    this.voiceRestrain = voiceRestrain;
  }

  public float getVoiceRestrainValue() {
    return voiceRestrainValue;
  }

  public void setVoiceRestrainValue(float voiceRestrainValue) {
    this.voiceRestrainValue = voiceRestrainValue;
  }

  public boolean isSaveRecord() {
    return saveRecord;
  }

  public void setSaveRecord(boolean saveRecord) {
    this.saveRecord = saveRecord;
  }

  public int getOpusCompressRatio() {
    return opusCompressRatio;
  }

  public void setOpusCompressRatio(int opusCompressRatio) {
    this.opusCompressRatio = opusCompressRatio;
  }

  public float getStartSil() {
    return startSil;
  }

  public void setStartSil(float startSil) {
    this.startSil = startSil;
  }

  public float getServerVadTime() {
    return serverVadTime;
  }

  public void setServerVadTime(float serverVadTime) {
    this.serverVadTime = serverVadTime;
  }

  public int getTtsSpeed() {
    return ttsSpeed;
  }

  public void setTtsSpeed(int ttsSpeed) {
    this.ttsSpeed = ttsSpeed;
  }

  public int getDefaultTts() {
    return defaultTts;
  }

  public void setDefaultTts(int defaultTts) {
    this.defaultTts = defaultTts;
  }

  public String getTrAddress() {
    return trAddress;
  }

  public void setTrAddress(String trAddress) {
    this.trAddress = trAddress;
  }

  public String getOptionFilterUrl() {
    return optionFilterUrl;
  }

  public void setOptionFilterUrl(String optionFilterUrl) {
    this.optionFilterUrl = optionFilterUrl;
  }

  public String getAdditionalService() {
    return additionalService;
  }

  public void setAdditionalService(String additionalService) {
    this.additionalService = additionalService;
  }

  public float getWakeupOptThresholdValue() {
    return wakeupOptThresholdValue;
  }

  public void setWakeupOptThresholdValue(float wakeupOptThresholdValue) {
    this.wakeupOptThresholdValue = wakeupOptThresholdValue;
  }

  public String getNluScenario() {
    return nluScenario;
  }

  public void setNluScenario(String nluScenario) {
    this.nluScenario = nluScenario;
  }

  public String getAsrDomain() {
    return asrDomain;
  }

  public void setAsrDomain(String asrDomain) {
    this.asrDomain = asrDomain;
  }

  public int getAsrOptWakeupModelId() {
    return asrOptWakeupModelId;
  }

  public void setAsrOptWakeupModelId(int asrOptWakeupModelId) {
    this.asrOptWakeupModelId = asrOptWakeupModelId;
  }

  public String getNluVer() {
    return nluVer;
  }

  public void setNluVer(String nluVer) {
    this.nluVer = nluVer;
  }

  public boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  private static final String CONFIG_FILE_PATH =
      Environment.getExternalStorageDirectory() + File.separator + "unisound" + File.separator
          + "sdk.ini";

  private static SdkConfig build() {
    SdkConfig sdkConfig = new SdkConfig();
    LogMgr.d(TAG, "file path:" + CONFIG_FILE_PATH);
    File file = new File(CONFIG_FILE_PATH);
    if (file.isFile() && file.exists()) {
      LogMgr.d(TAG, "sdk config file exist");
      String value = FileUtils.readTxtFile(CONFIG_FILE_PATH);
      SdkConfig sdkConfig2 = JsonTool.fromJson(value, SdkConfig.class);
      LogMgr.d(TAG, "sdk2:" + JsonTool.toJson(sdkConfig2));
      if (sdkConfig2 != null) {
        sdkConfig = sdkConfig2;
      }
    }
    LogMgr.d(TAG, "sdk:" + JsonTool.toJson(sdkConfig));
    return sdkConfig;
  }

  public static SdkConfig sdkConfig = build();

  public static SdkConfig getInstance() {
    return sdkConfig;
  }

  public int getTtsBright() {
    return ttsBright;
  }

  public void setTtsBright(int ttsBright) {
    this.ttsBright = ttsBright;
  }

  public float getWakeupOptThresholdValueLow() {
    return wakeupOptThresholdValueLow;
  }

  public void setWakeupOptThresholdValueLow(float wakeupOptThresholdValueLow) {
    this.wakeupOptThresholdValueLow = wakeupOptThresholdValueLow;
  }

  public int getFalseAlarm() {
    return falseAlarm;
  }

  public void setFalseAlarm(int falseAlarm) {
    this.falseAlarm = falseAlarm;
  }

  public boolean isConfidenceMeasure() {
    return confidenceMeasure;
  }

  public void setConfidenceMeasure(boolean confidenceMeasure) {
    this.confidenceMeasure = confidenceMeasure;
  }

  public float getConfidenceThreshold() {
    return confidenceThreshold;
  }

  public void setConfidenceThreshold(float confidenceThreshold) {
    this.confidenceThreshold = confidenceThreshold;
  }

  public boolean isRetTimeOut() {
    return retTimeOut;
  }

  public void setRetTimeOut(boolean retTimeOut) {
    this.retTimeOut = retTimeOut;
  }

  public float getVprScore() {
    return vprScore;
  }

  public void setVprScore(float vprScore) {
    this.vprScore = vprScore;
  }
}
