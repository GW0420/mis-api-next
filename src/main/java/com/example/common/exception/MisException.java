package com.example.common.exception;

import lombok.Data;

@Data
public class MisException extends RuntimeException {
    private String msg;
    private int code = 500;

    public MisException(Exception e) {
        super(e);
        this.msg = "执行异常";
    }

    public MisException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MisException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MisException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MisException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
