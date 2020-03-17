package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;
import java.util.List;

public class ConfigResponse implements Serializable {
  private UpdateFieldParameterBean updateFieldParameter;
  private UpdateFieldSalerBean updateFieldSaler;

  public UpdateFieldParameterBean getUpdateFieldParameter() {
    return updateFieldParameter;
  }

  public void setUpdateFieldParameter(UpdateFieldParameterBean updateFieldParameter) {
    this.updateFieldParameter = updateFieldParameter;
  }

  public UpdateFieldSalerBean getUpdateFieldSaler() {
    return updateFieldSaler;
  }

  public void setUpdateFieldSaler(UpdateFieldSalerBean updateFieldSaler) {
    this.updateFieldSaler = updateFieldSaler;
  }

  public static class UpdateFieldParameterBean {

    private String head;
    private DataBean data;
    private String merchantId;
    private String createTime;

    public String getHead() {
      return head;
    }

    public void setHead(String head) {
      this.head = head;
    }

    public DataBean getData() {
      return data;
    }

    public void setData(DataBean data) {
      this.data = data;
    }

    public String getMerchantId() {
      return merchantId;
    }

    public void setMerchantId(String merchantId) {
      this.merchantId = merchantId;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public static class DataBean {

      private IntentsBean intents;
      private List<TagsBean> tags;

      public IntentsBean getIntents() {
        return intents;
      }

      public void setIntents(IntentsBean intents) {
        this.intents = intents;
      }

      public List<TagsBean> getTags() {
        return tags;
      }

      public void setTags(List<TagsBean> tags) {
        this.tags = tags;
      }
    }
  }

  public static class UpdateFieldSalerBean {

    private String head;
    private DataBeanX data;
    private String merchantId;
    private String createTime;

    public String getHead() {
      return head;
    }

    public void setHead(String head) {
      this.head = head;
    }

    public DataBeanX getData() {
      return data;
    }

    public void setData(DataBeanX data) {
      this.data = data;
    }

    public String getMerchantId() {
      return merchantId;
    }

    public void setMerchantId(String merchantId) {
      this.merchantId = merchantId;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public static class DataBeanX {
      private List<SalerBean> saler;

      public List<SalerBean> getSaler() {
        return saler;
      }

      public void setSaler(List<SalerBean> saler) {
        this.saler = saler;
      }
    }
  }
}
