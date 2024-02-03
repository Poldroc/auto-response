package com.poldroc.autoresponse.common;

import java.util.Collections;

/**
 * 默认响应状态实现类 1
 *
 * @author Poldroc
 * @date 2024/2/2
 */
public class DefaultResponseImplStyle1 implements Response {

    private ResponseStatus status;

    private Object payload = Collections.emptyMap();

    public DefaultResponseImplStyle1() {
    }

    public DefaultResponseImplStyle1(ResponseStatus status, Object payload) {
        this.status = status;
        this.payload = payload;
    }

    @Override
    public void setStatus(ResponseStatus statusLine) {
        this.status = statusLine;
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public Object getPayload() {
        return payload;
    }
}
