package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class BroadcastIntent implements Intent, Serializable {
  private String station;
  private String msg;
  private List<ChannelList> channelList = Collections.emptyList();
  private String channelType;
  private String responseId;

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public List<ChannelList> getChannelList() {
    return channelList;
  }

  public void setChannelList(List<ChannelList> channelList) {
    this.channelList = channelList;
  }

  public String getChannelType() {
    return channelType;
  }

  public void setChannelType(String channelType) {
    this.channelType = channelType;
  }

  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }

  public static class ChannelList implements Serializable {

    String channel;
    List<FrequencyList> frequencyList = Collections.emptyList();

    public String getChannel() {
      return channel;
    }

    public void setChannel(String channel) {
      this.channel = channel;
    }

    public List<FrequencyList> getFrequencyList() {
      return frequencyList;
    }

    public void setFrequencyList(List<FrequencyList> frequencyList) {
      this.frequencyList = frequencyList;
    }

    public static class FrequencyList implements Serializable {

      String frequency;
      String type;
      String unit;

      public String getFrequency() {
        return frequency;
      }

      public void setFrequency(String frequency) {
        this.frequency = frequency;
      }

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getUnit() {
        return unit;
      }

      public void setUnit(String unit) {
        this.unit = unit;
      }

      @Override public String toString() {
        return type + "#" + frequency + "#" + unit;
      }
    }
  }
}
