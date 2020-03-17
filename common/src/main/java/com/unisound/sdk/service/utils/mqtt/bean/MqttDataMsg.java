package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class MqttDataMsg<P> implements Serializable {
  private String messageType;
  private String version;
  private MessageBodyBean<P> messageBody;

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public MessageBodyBean<P> getMessageBody() {
    return messageBody;
  }

  public void setMessageBody(MessageBodyBean<P> messageBody) {
    this.messageBody = messageBody;
  }

  public static class MessageBodyBean<P> implements Serializable {
    private DialogBean dialog;
    private DstServiceBean<P> dstService;

    public DialogBean getDialog() {
      return dialog;
    }

    public void setDialog(DialogBean dialog) {
      this.dialog = dialog;
    }

    public DstServiceBean<P> getDstService() {
      return dstService;
    }

    public void setDstService(DstServiceBean<P> dstService) {
      this.dstService = dstService;
    }
  }
}
