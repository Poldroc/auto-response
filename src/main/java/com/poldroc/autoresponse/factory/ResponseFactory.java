package com.poldroc.autoresponse.factory;

import com.poldroc.autoresponse.common.Response;
import com.poldroc.autoresponse.common.ResponseStatus;

/**
 * 响应工厂接口
 * @author Poldroc
 * @date 2024/2/2
 */
public interface ResponseFactory {

    /**
     * 创建新的空响应.
     *
     * @return
     */
    Response newEmptyInstance();

    /**
     * 创建新的空响应.
     *
     * @param statusLine 响应行信息.
     * @return
     */
    Response newInstance(ResponseStatus statusLine);

    /**
     * 创建新的响应.
     *
     * @return
     */
    Response newSuccessInstance();

    /**
     * 从数据中创建成功响应.
     *
     * @param data 响应数据.
     * @return
     */
    Response newSuccessInstance(Object data);

    /**
     * 创建新的失败响应.
     *
     * @return
     */
    Response newFailInstance();
}
