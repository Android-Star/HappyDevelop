/*
 * Copyright (c) 2012-2015 Beijing Unisound Information Technology Co., Ltd. All right reserved.
 */

package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class ASR implements Serializable {
  public static final String MIX = "mix";
  public static final String LOCAL = "local";
  public static final String NET = "net";

  public static final String PARTIAL = "partial";
  public static final String FULL = "full";

  private String engine_mode;
  private String result_type;
  private String recognition_result;

  public String getResult_type() {
    return result_type;
  }

  public void setResult_type(String result_type) {
    this.result_type = result_type;
  }

  public String getEngine_mode() {
    return engine_mode;
  }

  public void setEngine_mode(String engine_mode) {
    this.engine_mode = engine_mode;
  }

  public String getRecognition_result() {
    return recognition_result.trim();
  }

  public void setRecognition_result(String recognition_result) {
    this.recognition_result = recognition_result;
  }

  public boolean isMixMode() {
    return MIX.equals(engine_mode);
  }

  public boolean isNetMode() {
    return NET.equals(engine_mode);
  }

  public boolean isLocalMode() {
    return LOCAL.equals(engine_mode);
  }
}
