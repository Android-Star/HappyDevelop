package com.example.wilsonhan.happydevelop.net.response;

import java.io.Serializable;

public class TaskSecondBean implements Serializable {

  /**
   * buildingId (string, optional): 楼盘Id ,
   * completed (integer, optional): 任务客户完成数 ,
   * customerPhone (string, optional): 客户电话号码 ,
   * numberOfCalls (integer, optional): 客户来电次数 ,
   * taskName (string, optional): 任务名称 ,
   * total (integer, optional): 任务客户总数
   */

  private String customerPhone;
  private String taskName;
  private int completed;
  private int total;
  private int numberOfCalls;
  private String buildingId;

  public String getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(String customerPhone) {
    this.customerPhone = customerPhone;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public int getCompleted() {
    return completed;
  }

  public void setCompleted(int completed) {
    this.completed = completed;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getNumberOfCalls() {
    return numberOfCalls;
  }

  public void setNumberOfCalls(int numberOfCalls) {
    this.numberOfCalls = numberOfCalls;
  }

  public String getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(String buildingId) {
    this.buildingId = buildingId;
  }
}
