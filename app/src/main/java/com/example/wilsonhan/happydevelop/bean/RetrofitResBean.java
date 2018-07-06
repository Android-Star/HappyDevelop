package com.example.wilsonhan.happydevelop.bean;

import java.io.Serializable;

public class RetrofitResBean<T> implements Serializable {
    private String status;
    private String return_msg;
    private T result;

    public boolean isOk(){
        return "200".equals(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
