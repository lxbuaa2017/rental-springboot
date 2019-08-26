package com.rental.demo.util;

import java.io.Serializable;

import static com.rental.demo.util.Constant.ERROR;
import static com.rental.demo.util.Constant.SUCCESS;

public class JsonResult<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "成功";


    private int state;
    private String message;
    private T data;

    //构造方法
    public JsonResult() {
        state = SUCCESS;
        message = MESSAGE;
    }

    public JsonResult(T data) {
        state = SUCCESS;
        this.data = data;
    }

    public JsonResult(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static String build(int state, String message, Object data) {
        JsonResult jsonResult = new JsonResult(state, message, data);
        jsonResult.state = state;
        jsonResult.message = message;
        jsonResult.data = data;
        return jsonResult.toString();
    }

    public JsonResult(Throwable e) {
        state = ERROR;
        this.message = e.getMessage();
    }

    //get AND set 方法
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    @Override
    public String toString() {
        return "{state=" + state + ", message=" + message + ", data=" + data + "}";
    }
}

