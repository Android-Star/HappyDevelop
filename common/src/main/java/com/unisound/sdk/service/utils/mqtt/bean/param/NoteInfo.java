package com.unisound.sdk.service.utils.mqtt.bean.param;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.UUID;

public class NoteInfo implements Serializable {

  @SerializedName("id") private String noteId = UUID.randomUUID().toString();
  private String createTime;
  private String msg;

  public String getNoteId() {
    return noteId;
  }

  public void setNoteId(String noteId) {
    this.noteId = noteId;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
