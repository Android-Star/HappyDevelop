package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;
import java.util.List;

public class RecordDetailRecordBean implements Serializable {
  private BasicBean basic;
  private List<CallRecordsContainerBean> callRecordsContainer;

  public BasicBean getBasic() {
    return basic;
  }

  public void setBasic(BasicBean basic) {
    this.basic = basic;
  }

  public List<CallRecordsContainerBean> getCallRecordsContainer() {
    return callRecordsContainer;
  }

  public void setCallRecordsContainer(List<CallRecordsContainerBean> callRecordsContainer) {
    this.callRecordsContainer = callRecordsContainer;
  }

  public static class BasicBean {
    /**
     * name : 韩文肖
     * phone : 13140167595
     */

    private String name;
    private String phone;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }
  }

  public static class CallRecordsContainerBean {
    /**
     * timeStr : 今天
     * records : [{"phoneNumber":"13140167595","phoneName":"韩文肖","phoneType":"phoneNumber","talkType":3,"talkDate":"2020-02-21
     * 12:06:55","talkCostTime":0},{"phoneNumber":"13140167595","phoneName":"韩文肖","phoneType":"phoneNumber","talkType":3,"talkDate":"2020-02-21
     * 13:41:49","talkCostTime":0},{"phoneNumber":"13140167595","phoneName":"韩文肖","phoneType":"phoneNumber","talkType":3,"talkDate":"2020-02-21
     * 13:46:46","talkCostTime":0}]
     */

    private String timeStr;
    private List<RecordsBean> records;

    public String getTimeStr() {
      return timeStr;
    }

    public void setTimeStr(String timeStr) {
      this.timeStr = timeStr;
    }

    public List<RecordsBean> getRecords() {
      return records;
    }

    public void setRecords(List<RecordsBean> records) {
      this.records = records;
    }

    public static class RecordsBean {
      /**
       * phoneNumber : 13140167595
       * phoneName : 韩文肖
       * phoneType : phoneNumber
       * talkType : 3
       * talkDate : 2020-02-21 12:06:55
       * talkCostTime : 0
       */

      private String phoneNumber;
      private String phoneName;
      private String phoneType;
      private int talkType;
      private String talkDate;
      private int talkCostTime;

      public String getPhoneNumber() {
        return phoneNumber;
      }

      public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
      }

      public String getPhoneName() {
        return phoneName;
      }

      public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
      }

      public String getPhoneType() {
        return phoneType;
      }

      public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
      }

      public int getTalkType() {
        return talkType;
      }

      public void setTalkType(int talkType) {
        this.talkType = talkType;
      }

      public String getTalkDate() {
        return talkDate;
      }

      public void setTalkDate(String talkDate) {
        this.talkDate = talkDate;
      }

      public int getTalkCostTime() {
        return talkCostTime;
      }

      public void setTalkCostTime(int talkCostTime) {
        this.talkCostTime = talkCostTime;
      }
    }
  }
}
