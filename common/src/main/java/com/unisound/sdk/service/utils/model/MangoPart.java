package com.unisound.sdk.service.utils.model;

import java.io.Serializable;
import java.util.Objects;

public class MangoPart implements Serializable {
  private Long vodId;         //集合ID
  private Long partId;        //分集ID
  private String name;        //分集名称
  private Integer serialNo;   //分集号，1开始，整片分析好连续，非整片不连续
  private Integer status;     //1-已发布；0-未发布

  public Long getVodId() {
    return vodId;
  }

  public void setVodId(Long vodId) {
    this.vodId = vodId;
  }

  public Long getPartId() {
    return partId;
  }

  public void setPartId(Long partId) {
    this.partId = partId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(Integer serialNo) {
    this.serialNo = serialNo;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MangoPart mangoPart = (MangoPart) o;
    return Objects.equals(partId, mangoPart.partId);
  }

  @Override public int hashCode() {
    return Objects.hash(partId);
  }
}
