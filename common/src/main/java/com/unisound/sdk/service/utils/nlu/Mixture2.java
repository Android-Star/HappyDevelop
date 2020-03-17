package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class Mixture2 implements Serializable {

  /**
   * asr_recongize :
   * rc : 2
   * returnCode : 0
   * code : ANSWER
   * retTag : nlu
   * service : cn.yunzhisheng.error
   * nluProcessTime : 0
   * text :
   * history : cn.yunzhisheng.error
   * error : {"code":"2040","message":"text参数缺失或无效"}
   * responseId : 6c71c4efcd784bb6b43fc18fbf89ec01
   * displayProcessRecord : {"confidence":"1.0","timeCosts":{}}
   */

  private String asr_recongize;
  private int rc;
  private int returnCode;
  private String code;
  private String retTag;
  private String service;
  private String nluProcessTime;
  private String text;
  private String history;
  private Error error;
  private String responseId;
  private General general;
  private int speechStartPoint = 0;
  private int speechEndPoint = 0;

  public int getSpeechStartPoint() {
    return speechStartPoint;
  }

  public void setSpeechStartPoint(int speechStartPoint) {
    this.speechStartPoint = speechStartPoint;
  }

  public int getSpeechEndPoint() {
    return speechEndPoint;
  }

  public void setSpeechEndPoint(int speechEndPoint) {
    this.speechEndPoint = speechEndPoint;
  }

  public General getGeneral() {
    return general;
  }

  public void setGeneral(General general) {
    this.general = general;
  }

  public String getAsr_recongize() {
    return asr_recongize;
  }

  public void setAsr_recongize(String asr_recongize) {
    this.asr_recongize = asr_recongize;
  }

  public int getRc() {
    return rc;
  }

  public void setRc(int rc) {
    this.rc = rc;
  }

  public int getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(int returnCode) {
    this.returnCode = returnCode;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRetTag() {
    return retTag;
  }

  public void setRetTag(String retTag) {
    this.retTag = retTag;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getNluProcessTime() {
    return nluProcessTime;
  }

  public void setNluProcessTime(String nluProcessTime) {
    this.nluProcessTime = nluProcessTime;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }
}
