package com.example.common.result;


import com.example.common.enums.ResultCodeEnum;

public class R {
    private String code;
    private String msg;
    private Object data;


    public R() {
    }

    private R(Object data) {
        this.data = data;
    }

    public static R success() {
        R r = new R();
        r.setCode(ResultCodeEnum.SUCCESS.code);
        r.setMsg(ResultCodeEnum.SUCCESS.msg);
        return r;
    }

    public static R success(Object data) {
        R r = new R(data);
        r.setCode(ResultCodeEnum.SUCCESS.code);
        r.setMsg(ResultCodeEnum.SUCCESS.msg);
        return r;
    }

    public static R success(String code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ResultCodeEnum.SYSTEM_ERROR.code);
        r.setMsg(ResultCodeEnum.SYSTEM_ERROR.msg);
        return r;
    }

    public static R error(String code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}

