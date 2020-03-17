package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;

public class ContactIntent extends CallIntent implements Serializable {
  private String receiverName;
  private String receiverNumber;

  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public String getReceiverNumber() {
    return receiverNumber;
  }

  public void setReceiverNumber(String receiverNumber) {
    this.receiverNumber = receiverNumber;
  }
}
