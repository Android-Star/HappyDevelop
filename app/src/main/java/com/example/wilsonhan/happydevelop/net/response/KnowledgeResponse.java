package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class KnowledgeResponse implements Serializable {
  /**
   * building (string, optional): 所属楼盘 ,
   * content (string, optional): 知识库内容 ,
   * createBy (string, optional): 创建人 ,
   * createTime (string, optional): 创建时间 ,
   * id (integer, optional): 数据id编号 ,
   * isPublish (integer, optional): 是否已发布：0未发布，1已发布 ,
   * org (string, optional): 所属集团 ,
   * project (string, optional): 所属项目 ,
   * region (string, optional): 所属区域 ,
   * tag (string, optional): 知识库标签 ,
   * updateBy (string, optional): 更新人 ,
   * updateTime (string, optional): 更新时间
   */

  private String building;
  private String content;
  private String createBy;
  private String createTime;
  private int id;
  private int isPublish;
  private String org;
  private String project;
  private String region;
  private String tag;
  private String updateBy;
  private String updateTime;

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIsPublish() {
    return isPublish;
  }

  public void setIsPublish(int isPublish) {
    this.isPublish = isPublish;
  }

  public String getOrg() {
    return org;
  }

  public void setOrg(String org) {
    this.org = org;
  }

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }
}
