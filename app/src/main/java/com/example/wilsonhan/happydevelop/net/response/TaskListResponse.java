package com.example.wilsonhan.happydevelop.net.response;

import com.unisound.smartphone.bean.TaskBean;
import java.io.Serializable;
import java.util.List;

public class TaskListResponse implements Serializable {
  /**
   * data (Array[Inline Model 1], optional): 实际数据 ,
   * pageNum (integer, optional): 当前页 ,
   * pageSize (integer, optional): 每页的数量 ,
   * pages (integer, optional): 总页数 ,
   * total (integer, optional): 数据总条数
   */
  private List<TaskBean> data;
  private int pageNum;
  private int pageSize;
  private int pages;
  private long total;

  public List<TaskBean> getData() {
    return data;
  }

  public void setData(List<TaskBean> data) {
    this.data = data;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }
}
