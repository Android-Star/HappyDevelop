package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class PoiResult implements Result, Serializable {

  private PoiResultBeans fromPoi;
  private PoiResultBeans toPoi;

  public PoiResultBeans getFromPoi() {
    return fromPoi;
  }

  public void setFromPoi(PoiResultBeans fromPoi) {
    this.fromPoi = fromPoi;
  }

  public PoiResultBeans getToPoi() {
    return toPoi;
  }

  public void setToPoi(PoiResultBeans toPoi) {
    this.toPoi = toPoi;
  }

  public static class PoiResultBeans implements Serializable {

    private String total;
    private String page_size;
    private String count;
    private String page;
    private String totalTime;

    public String getTotal() {
      return total;
    }

    public void setTotal(String total) {
      this.total = total;
    }

    public String getPage_size() {
      return page_size;
    }

    public void setPage_size(String page_size) {
      this.page_size = page_size;
    }

    public String getCount() {
      return count;
    }

    public void setCount(String count) {
      this.count = count;
    }

    public String getPage() {
      return page;
    }

    public void setPage(String page) {
      this.page = page;
    }

    public String getTotalTime() {
      return totalTime;
    }

    public void setTotalTime(String totalTime) {
      this.totalTime = totalTime;
    }
  }

  public static class PoiServerBean implements Serializable {
    private String citycode;
    private String address;
    private String name;
    private String tel;
    private String cityname;
    private String type;
    private String lat;
    private String lon;

    public String getCityname() {
      return cityname;
    }

    public void setCityname(String cityname) {
      this.cityname = cityname;
    }

    public String getCitycode() {
      return citycode;
    }

    public void setCitycode(String citycode) {
      this.citycode = citycode;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getTel() {
      return tel;
    }

    public void setTel(String tel) {
      this.tel = tel;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getLat() {
      return lat;
    }

    public void setLat(String lat) {
      this.lat = lat;
    }

    public String getLon() {
      return lon;
    }

    public void setLon(String lon) {
      this.lon = lon;
    }
  }
}
