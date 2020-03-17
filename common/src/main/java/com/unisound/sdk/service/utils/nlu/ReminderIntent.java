package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class ReminderIntent implements Intent, Serializable {
  private String dateTime;
  private String eventTime;
  private String content;
  private boolean isSubscribe;
  private String triggerType;
  private String repeatType;
  private String topic;
  private Event event;

  public String getDateTime() {
    return dateTime;
  }

  public String getEventTime() {
    return eventTime;
  }

  public String getContent() {
    return content;
  }

  public boolean isSubscribe() {
    return isSubscribe;
  }

  public String getTriggerType() {
    return triggerType;
  }

  public String getRepeatType() {
    return repeatType;
  }

  public String getTopic() {
    return topic;
  }

  public Event getEvent() {
    return event;
  }

  public static class Event implements Serializable {
    private String startTime;
    private String topic;
    private String person;
    private String location;

    public String getStartTime() {
      return startTime;
    }

    public void setStartTime(String startTime) {
      this.startTime = startTime;
    }

    public String getTopic() {
      return topic;
    }

    public void setTopic(String topic) {
      this.topic = topic;
    }

    public String getPerson() {
      return person;
    }

    public void setPerson(String person) {
      this.person = person;
    }

    public String getLocation() {
      return location;
    }

    public void setLocation(String location) {
      this.location = location;
    }
  }
}
