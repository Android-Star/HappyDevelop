package com.unisound.sdk.service.utils.mqtt.bean;

import java.io.Serializable;

public class RegisterResult implements Serializable {

  private int costTime;
  private String message;
  private ResultBean result;
  private String returnCode;

  public boolean isSuccess() {
    return "mc_0008".equals(returnCode) || "mc_0000".equals(returnCode);
  }

  public int getCostTime() {
    return costTime;
  }

  public void setCostTime(int costTime) {
    this.costTime = costTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ResultBean getResult() {
    return result;
  }

  public void setResult(ResultBean result) {
    this.result = result;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public static class ResultBean implements Serializable {
    private String clientId;
    private ConnectionBean connection;
    private TopicsBean topics;

    public String getClientId() {
      return clientId;
    }

    public void setClientId(String clientId) {
      this.clientId = clientId;
    }

    public ConnectionBean getConnection() {
      return connection;
    }

    public void setConnection(ConnectionBean connection) {
      this.connection = connection;
    }

    public TopicsBean getTopics() {
      return topics;
    }

    public void setTopics(TopicsBean topics) {
      this.topics = topics;
    }

    public static class ConnectionBean implements Serializable {

      private String ip;
      private int keepAlive;
      private String password;
      private int port;
      private String protocol;
      private String username;

      public String getIp() {
        return ip;
      }

      public void setIp(String ip) {
        this.ip = ip;
      }

      public int getKeepAlive() {
        return keepAlive;
      }

      public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
      }

      public String getPassword() {
        return password;
      }

      public void setPassword(String password) {
        this.password = password;
      }

      public int getPort() {
        return port;
      }

      public void setPort(int port) {
        this.port = port;
      }

      public String getProtocol() {
        return protocol;
      }

      public void setProtocol(String protocol) {
        this.protocol = protocol;
      }

      public String getUsername() {
        return username;
      }

      public void setUsername(String username) {
        this.username = username;
      }
    }

    public static class TopicsBean {
      /**
       * publish : c2s/msg/1rAvYskzkHybZkEzcaPYvS
       * subscribe : s2c/msg/1rAvYskzkHybZkEzcaPYvS
       */

      private String publish;
      private String subscribe;

      public String getPublish() {
        return publish;
      }

      public void setPublish(String publish) {
        this.publish = publish;
      }

      public String getSubscribe() {
        return subscribe;
      }

      public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
      }
    }
  }
}
