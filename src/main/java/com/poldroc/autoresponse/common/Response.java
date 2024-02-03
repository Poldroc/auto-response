package com.poldroc.autoresponse.common;

/**
 * 响应接口
 * @author Poldroc
 * @date 2024/2/2
 */

public interface Response {

    /**
     * 设置响应行
     *
     */
    void setStatus(ResponseStatus statusLine);

    /**
     * 获取响应行
     *
     * @return
     */
    ResponseStatus getStatus();

    /**
     * 设置响应数据.
     *
     * @param payload 设置的响应数据.
     */
    void setPayload(Object payload);

    /**
     * 获得响应数据.
     *
     * @return
     */
    Object getPayload();
}
