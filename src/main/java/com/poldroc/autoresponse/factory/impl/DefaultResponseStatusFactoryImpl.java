package com.poldroc.autoresponse.factory.impl;

import com.poldroc.autoresponse.common.DefaultResponseStatus;
import com.poldroc.autoresponse.common.ResponseStatus;
import com.poldroc.autoresponse.core.AutoResponseProperties;
import com.poldroc.autoresponse.factory.ResponseStatusFactory;
import jakarta.annotation.Resource;
/**
 * 默认的响应状态工厂实现类
 * @author Poldroc
 * @date 2024/2/2
 */
public class DefaultResponseStatusFactoryImpl implements ResponseStatusFactory {

    @Resource
    private AutoResponseProperties properties;

    @Override
    public ResponseStatus defaultSuccess() {
        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(properties.getDefaultSuccessCode());
        defaultResponseStatus.setMsg(properties.getDefaultSuccessMsg());
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus defaultError() {
        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(properties.getDefaultErrorCode());
        defaultResponseStatus.setMsg(properties.getDefaultErrorMsg());
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus newInstance(String code, String msg) {
        return new DefaultResponseStatus(code, msg);
    }
}
