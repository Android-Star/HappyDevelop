package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.List;

public class WechatResult implements Result, Serializable {
  private List<String> contactList;

  public List<String> getContactList() {
    return contactList;
  }

  public void setContactList(List<String> contactList) {
    this.contactList = contactList;
  }
}
