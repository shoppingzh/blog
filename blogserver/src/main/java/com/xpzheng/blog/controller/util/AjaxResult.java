package com.xpzheng.blog.controller.util;

public class AjaxResult {

    private boolean success;
    private String msg;
    private Object data;
    private Integer code = 20000;

    private AjaxResult(boolean success, Object data){
        this.success = success;
        this.data = data;
    }

    private AjaxResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static AjaxResult success(Object data){
        return new AjaxResult(true, data);
    }

    public static  AjaxResult failed(String msg) {
        return new AjaxResult(false, msg);
    }

}
