package com.mysqldemo.demo;

public class RemoteResult<T>{


    private Boolean isComplete;

    private Integer code;
    private String msg;
//    private Date timestamp;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public Date getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Date timestamp) {
//        this.timestamp = timestamp;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public Boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean complete) {
        isComplete = complete;
    }
}
