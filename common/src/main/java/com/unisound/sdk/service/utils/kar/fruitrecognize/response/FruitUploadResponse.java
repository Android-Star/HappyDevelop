package com.unisound.sdk.service.utils.kar.fruitrecognize.response;

import java.io.Serializable;
import java.util.List;

public class FruitUploadResponse implements Serializable {
  private int objNum;
  private List<FruitInfoResponse> objects;

  public int getObjNum() {
    return objNum;
  }

  public void setObjNum(int objNum) {
    this.objNum = objNum;
  }

  public List<FruitInfoResponse> getObjects() {
    return objects;
  }

  public void setObjects(List<FruitInfoResponse> objects) {
    this.objects = objects;
  }
}
