package com.example.wilsonhan.happydevelop.net.response;

import com.unisound.smartphone.net.request.UserMsgRequest;
import java.io.Serializable;

public class RecordDetailResponse implements Serializable {
  private UserMsgRequest customer;
  private RecordDetailRecordBean records;

  public UserMsgRequest getCustomer() {
    return customer;
  }

  public void setCustomer(UserMsgRequest customer) {
    this.customer = customer;
  }

  public RecordDetailRecordBean getRecords() {
    return records;
  }

  public void setRecords(RecordDetailRecordBean records) {
    this.records = records;
  }
}
