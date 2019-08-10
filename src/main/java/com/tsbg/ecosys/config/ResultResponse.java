package com.tsbg.ecosys.config;

/**
 * 前后端分离信息返回类
 */
public class ResultResponse {

    private static final int SUCCESS = 0;
    private static final int FAILED = 500;

    private int code; //返回的状态码
    private String message; //返回的信息详情
    private Object data; //返回的对象数据
    private Object dataSecond;//返回的第二个对象

    /**
     * 构造注入
     */
    public ResultResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法重载
     */
    public ResultResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造方法重载
     * 有两个对象传递时使用
     */
    public ResultResponse(int code, String message, Object data,Object dataSecond) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.dataSecond = dataSecond;
    }

    /**
     * 设值注入
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDataSecond() {
        return dataSecond;
    }

    public void setDataSecond(Object dataSecond) {
        this.dataSecond = dataSecond;
    }
}
