package com.unisound.sdk.service.utils.kar.menu.request;

import java.io.Serializable;

public class GetDataLinkRequest implements Serializable {
  private String dataSourceCode;
  private String dataType;
  private String deviceType = "android";
  private long id;
  private long resourceType;

  public String getDataSourceCode() {
    return dataSourceCode;
  }

  public void setDataSourceCode(String dataSourceCode) {
    this.dataSourceCode = dataSourceCode;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getResourceType() {
    return resourceType;
  }

  public void setResourceType(long resourceType) {
    this.resourceType = resourceType;
  }
}
