package com.unisound.sdk.service.utils.kar.calendar.request;

import java.io.Serializable;

public class CourseInfoRequest implements Serializable {
  private long courseId;
  private int pageNum;
  private int pageSize;

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
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
}
