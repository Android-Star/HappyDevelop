package com.example.wilsonhan.happydevelop.net;

/* 接口返回的实体基类
 * @param <T>
 */
public class BaseResponse<T> {
  private static final int SUCCESS_CODE = 0;    //成功的code
  private int status = -1;                       //响应码
  private String message;             //提示信息
  private T data;                  //返回的具体数据

  public boolean isSuccess() {
    return getStatus() == SUCCESS_CODE;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override public String toString() {
    return "BaseResponse{" + "status=" + status + ", message='" + message + '\'' + ", data=" + data
        + '}';
  }
}