package com.unisound.sdk.service.utils.kar.menu.request;

import java.io.Serializable;

public class AddFavoriteRequest implements Serializable {
  private long deviceId;
  private int resourceType;
  private long resourceId;
  private String resourceName;
  private String imgUrl;
  private String dataType;
  private String dataSourceCode;
  private long dataResourceType;

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public String getDataSourceCode() {
    return dataSourceCode;
  }

  public void setDataSourceCode(String dataSourceCode) {
    this.dataSourceCode = dataSourceCode;
  }

  public long getDataResourceType() {
    return dataResourceType;
  }

  public void setDataResourceType(long dataResourceType) {
    this.dataResourceType = dataResourceType;
  }

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  public int getResourceType() {
    return resourceType;
  }

  public void setResourceType(int resourceType) {
    this.resourceType = resourceType;
  }

  public long getResourceId() {
    return resourceId;
  }

  public void setResourceId(long resourceId) {
    this.resourceId = resourceId;
  }

  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
