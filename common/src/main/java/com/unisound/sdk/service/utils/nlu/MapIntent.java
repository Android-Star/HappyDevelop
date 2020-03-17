package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class MapIntent implements Intent, Serializable {
  public static final String CURRENT_LOC = "CURRENT_LOC";
  public static final String DES_LOC = "DES_LOC";
  public static final String CURRENT_CITY = "CURRENT_CITY";
  public static final String LOC_HOME = "LOC_HOME";
  public static final String LOC_OFFICE = "LOC_OFFICE";

  public static final String BUS = "BUS";
  public static final String WALK = "WALK";
  public static final String CAR = "CAR";

  public static final String EBUS_NO_SUBWAY = "EBUS_NO_SUBWAY";
  public static final String EBUS_WALK_FIRST = "EBUS_WALK_FIRST";
  public static final String EBUS_TRANSFER_FIRST = "EBUS_TRANSFER_FIRST";
  public static final String ECAR_DIS_FIRST = "ECAR_DIS_FIRST";
  public static final String ECAR_FEE_FIRST = "ECAR_FEE_FIRST";
  public static final String TIME_FIRST = "TIME_FIRST";
  private static HashMap<String, String> toPoiMaps;

  static {
    if (toPoiMaps == null) {
      toPoiMaps = new HashMap<>();
      toPoiMaps.put("LOC_SCHOOL", "学校");
    }
  }

  private String fromPOI;
  private String fromCity;
  private String toPOI;
  private String toCity;
  private String method;
  private String condition;
  private List<String> pathPoints;

  public String getFromPOI() {
    return fromPOI;
  }

  public void setFromPOI(String fromPOI) {
    this.fromPOI = fromPOI;
  }

  public String getFromCity() {
    return fromCity;
  }

  public void setFromCity(String fromCity) {
    this.fromCity = fromCity;
  }

  public String getToPOI() {
    if (toPoiMaps.containsKey(toPOI)) {
      this.toPOI = toPoiMaps.get(toPOI);
    }
    return toPOI;
  }

  public void setToPOI(String toPOI) {
    this.toPOI = toPOI;
  }

  public String getToCity() {
    return toCity;
  }

  public void setToCity(String toCity) {
    this.toCity = toCity;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public List<String> getPathPoints() {
    return pathPoints;
  }

  public void setPathPoints(List<String> pathPoints) {
    this.pathPoints = pathPoints;
  }
}
