package com.poldroc.autoresponse.factory;

import com.poldroc.autoresponse.common.ResponseStatus;

/**
 * 响应状态工厂接口
 * @author Poldroc
 * @date 2024/2/2
 */

public interface ResponseStatusFactory {
    /**
     * 获得响应成功的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultSuccess();

    /**
     * 获得失败的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultError();


    /**
     * 从code和msg创建ResponseStatus
     * @param code 状态码
     * @param msg  响应信息
     * @return
     */
    ResponseStatus newInstance(String code,String msg);
}
