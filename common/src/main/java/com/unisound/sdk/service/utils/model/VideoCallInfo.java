package com.unisound.sdk.service.utils.model;

import android.support.annotation.Nullable;
import com.unisound.sdk.service.utils.StringUtils;
import java.io.Serializable;

public class VideoCallInfo implements Serializable {

  private boolean videoCallEnable = false;
  private boolean monitorEnable = false;
  private String message = "";

  @Override public int hashCode() {
    return super.hashCode();
  }

  public boolean isVideoCallEnable() {
    return videoCallEnable;
  }

  public void setVideoCallEnable(boolean videoCallEnable) {
    this.videoCallEnable = videoCallEnable;
  }

  public boolean isMonitorEnable() {
    return monitorEnable;
  }

  public void setMonitorEnable(boolean monitorEnable) {
    this.monitorEnable = monitorEnable;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override public boolean equals(@Nullable Object obj) {
    if (obj == null || (!(obj instanceof VideoCallInfo))) {
      return false;
    }
    VideoCallInfo videoCallInfo = (VideoCallInfo) obj;
    return videoCallInfo.isMonitorEnable() == monitorEnable
        && videoCallInfo.isVideoCallEnable() == videoCallEnable && StringUtils.isSameStr(
        videoCallInfo.getMessage(), message);
  }
}
