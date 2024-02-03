package com.poldroc.autoresponse.common;
/**
 * 默认响应状态实现类
 * @author Poldroc
 * @date 2024/2/2
 */
public class DefaultResponseStatus implements ResponseStatus {
    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    public DefaultResponseStatus() {
    }

    public DefaultResponseStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
