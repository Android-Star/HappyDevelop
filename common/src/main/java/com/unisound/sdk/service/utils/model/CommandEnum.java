package com.unisound.sdk.service.utils.model;

public enum CommandEnum {
  ROOTMENUS("getRootMenus"), CHILDMENUS("getChildMenus"), AUDIO("getAudio"), VIDEO(
      "getVideo"), APPLICATION("getApplication"), ACTIVITY("getActivity");
  private String command;

  CommandEnum(String command) {
    this.command = command;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }
}
