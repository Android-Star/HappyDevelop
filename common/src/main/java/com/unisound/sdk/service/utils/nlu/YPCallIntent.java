package com.unisound.sdk.service.utils.nlu;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class YPCallIntent implements Intent, Serializable {

  private List<Contacts> contacts = Collections.emptyList();

  public List<Contacts> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contacts> contacts) {
    this.contacts = contacts;
  }

  public static class Contacts implements Serializable {
    private String name;
    private String numbers;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNumbers() {
      return numbers;
    }

    public void setNumbers(String numbers) {
      this.numbers = numbers;
    }
  }
}
