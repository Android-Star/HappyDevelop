package com.unisound.sdk.service.utils.model;

import java.io.Serializable;
import java.util.List;

public class MangoAsset implements Serializable {
  private Long vodId;             //集合ID
  private Integer vodTypeId;      //集合类型ID
  private String vodTypeName;     //类型名称
  private String name;            //集合名称
  private String englishName;     //英文名
  private String otherName;       //副标题
  private String director;        //导演
  private String adaptor;         //编剧（主持人）(格式：王晶|张艺谋)
  private String leader;          //主演(嘉宾） (格式：王晶|张艺谋)
  private String kind;            //标签  如："古装|历史|战争|爱情|综艺|MV"
  private String area;            //区域标签 （内地|港台）
  private String language;        //语言  "国语"*/
  private String caption;         //字幕  "中文简体",
  private String story;
  //简介  "九环公主对着薛刚大说特说了一通，并且说要薛刚陪着自己玩。九环公主给薛刚出了一个问题，薛刚趁机自己上马溜走了，说要五虎结义。"
  private String awards;          //看点  "薛刚杀死张宝"
  private Integer year;           //年份
  private String releaseTime;     //上映时间
  private String productionTime;  //出品时间
  private String workRoom;        //工作室
  private String inital;          //首字母
  private String fullSpell;       //全拼 多音字用英文竖线分隔
  private String simpleSpell;     //简拼 多音字用英文竖线分隔
  private Integer serialCount;    //当前已更新集数
  private Integer totalNumber;    //总集数
  private String scores;          //集合影片的评分
  private Integer status;         //是否发布 1-已发布 0-未发布
  private String img_v;           //海报竖图 326x482 *返回给终端
  private String createTime;      //创建时间
  private String updateTime;      //更新时间
  private Integer recState;       //是否完结 0-未完结 1-已完结
  private String playTime;        //更新说明
  private String updateInfo;      //更新集数|更新期数   10|2017-04-18
  private List<MangoPart> parts;  //分集详情

  public Long getVodId() {
    return vodId;
  }

  public void setVodId(Long vodId) {
    this.vodId = vodId;
  }

  public Integer getVodTypeId() {
    return vodTypeId;
  }

  public void setVodTypeId(Integer vodTypeId) {
    this.vodTypeId = vodTypeId;
  }

  public String getVodTypeName() {
    return vodTypeName;
  }

  public void setVodTypeName(String vodTypeName) {
    this.vodTypeName = vodTypeName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEnglishName() {
    return englishName;
  }

  public void setEnglishName(String englishName) {
    this.englishName = englishName;
  }

  public String getOtherName() {
    return otherName;
  }

  public void setOtherName(String otherName) {
    this.otherName = otherName;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getAdaptor() {
    return adaptor;
  }

  public void setAdaptor(String adaptor) {
    this.adaptor = adaptor;
  }

  public String getLeader() {
    return leader;
  }

  public void setLeader(String leader) {
    this.leader = leader;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getStory() {
    return story;
  }

  public void setStory(String story) {
    this.story = story;
  }

  public String getAwards() {
    return awards;
  }

  public void setAwards(String awards) {
    this.awards = awards;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getReleaseTime() {
    return releaseTime;
  }

  public void setReleaseTime(String releaseTime) {
    this.releaseTime = releaseTime;
  }

  public String getProductionTime() {
    return productionTime;
  }

  public void setProductionTime(String productionTime) {
    this.productionTime = productionTime;
  }

  public String getWorkRoom() {
    return workRoom;
  }

  public void setWorkRoom(String workRoom) {
    this.workRoom = workRoom;
  }

  public String getInital() {
    return inital;
  }

  public void setInital(String inital) {
    this.inital = inital;
  }

  public String getFullSpell() {
    return fullSpell;
  }

  public void setFullSpell(String fullSpell) {
    this.fullSpell = fullSpell;
  }

  public String getSimpleSpell() {
    return simpleSpell;
  }

  public void setSimpleSpell(String simpleSpell) {
    this.simpleSpell = simpleSpell;
  }

  public Integer getSerialCount() {
    return serialCount;
  }

  public void setSerialCount(Integer serialCount) {
    this.serialCount = serialCount;
  }

  public Integer getTotalNumber() {
    return totalNumber;
  }

  public void setTotalNumber(Integer totalNumber) {
    this.totalNumber = totalNumber;
  }

  public String getScores() {
    return scores;
  }

  public void setScores(String scores) {
    this.scores = scores;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getImg_v() {
    return img_v;
  }

  public void setImg_v(String img_v) {
    this.img_v = img_v;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getRecState() {
    return recState;
  }

  public void setRecState(Integer recState) {
    this.recState = recState;
  }

  public String getPlayTime() {
    return playTime;
  }

  public void setPlayTime(String playTime) {
    this.playTime = playTime;
  }

  public String getUpdateInfo() {
    return updateInfo;
  }

  public void setUpdateInfo(String updateInfo) {
    this.updateInfo = updateInfo;
  }

  public List<MangoPart> getParts() {
    return parts;
  }

  public void setParts(List<MangoPart> parts) {
    this.parts = parts;
  }
}
