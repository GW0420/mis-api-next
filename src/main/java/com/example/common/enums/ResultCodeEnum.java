package com.example.common.enums;


public enum ResultCodeEnum {
    SUCCESS("200","成功"),
    SUCCESS_VALIDATE("201","成功"),
    LOGIN_VALIDATE("202","账号或密码错误"),
    TOKEN_TIMEOUT("203","token已过期"),
    SYSTEM_ERROR("500","系统异常");

    public String code;
    public String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

