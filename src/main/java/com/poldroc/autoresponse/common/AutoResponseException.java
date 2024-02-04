package com.poldroc.autoresponse.common;
/**
 * 响应异常
 * @author Poldroc
 * @date 2024/2/4
 */

public class AutoResponseException extends RuntimeException {
    private String code;
    private String msg;

    public AutoResponseException() {
    }

    public AutoResponseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AutoResponseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public AutoResponseException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public AutoResponseException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
