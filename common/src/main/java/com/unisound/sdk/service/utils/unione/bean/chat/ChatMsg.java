package com.unisound.sdk.service.utils.unione.bean.chat;

import java.util.List;

public class ChatMsg {

  private List<MsgBean> info;

  public List<MsgBean> getInfo() {
    return info;
  }

  public void setInfo(List<MsgBean> info) {
    this.info = info;
  }

  public static class MsgBean {

    private String logId;
    private String msg;
    private String feedBackType;
    private String createTime;
    private boolean isUserEdited;
    private boolean isCorrect;

    public String getLogId() {
      return logId;
    }

    public void setLogId(String logId) {
      this.logId = logId;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }

    public String getFeedBackType() {
      return feedBackType;
    }

    public void setFeedBackType(String feedBackType) {
      this.feedBackType = feedBackType;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public boolean isUserEdited() {
      return isUserEdited;
    }

    public void setUserEdited(boolean userEdited) {
      isUserEdited = userEdited;
    }

    public boolean isCorrect() {
      return isCorrect;
    }

    public void setCorrect(boolean correct) {
      isCorrect = correct;
    }
  }
}
