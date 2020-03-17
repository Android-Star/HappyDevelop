package com.unisound.sdk.service.utils.kar.oral.response;

import java.io.Serializable;
import java.util.List;

public class VersionBean implements Serializable {
  private String img;
  private String scopeType;
  private int level;
  private String name;
  private String scopeValue;
  private int pid;
  private int id;
  private int sort;
  private int type; //1：普通目录 2：专辑类型
  private long resourceId;
  private List<VersionBean> list;

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getScopeType() {
    return scopeType;
  }

  public void setScopeType(String scopeType) {
    this.scopeType = scopeType;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScopeValue() {
    return scopeValue;
  }

  public void setScopeValue(String scopeValue) {
    this.scopeValue = scopeValue;
  }

  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSort() {
    return sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public long getResourceId() {
    return resourceId;
  }

  public void setResourceId(long resourceId) {
    this.resourceId = resourceId;
  }

  public List<VersionBean> getList() {
    return list;
  }

  public void setList(List<VersionBean> list) {
    this.list = list;
  }
}
