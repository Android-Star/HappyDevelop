package com.unisound.sdk.service.utils.kar.calendar.response;

import java.io.Serializable;
import java.util.List;

public class CourseDetailResponse implements Serializable {
  private int count;
  private List<CourseInfoResponse> courseDetailInfoList;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<CourseInfoResponse> getCourseDetailInfoList() {
    return courseDetailInfoList;
  }

  public void setCourseDetailInfoList(List<CourseInfoResponse> courseDetailInfoList) {
    this.courseDetailInfoList = courseDetailInfoList;
  }
}
