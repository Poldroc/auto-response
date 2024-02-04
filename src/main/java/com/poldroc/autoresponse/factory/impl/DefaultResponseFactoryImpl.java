package com.poldroc.autoresponse.factory.impl;

import com.poldroc.autoresponse.common.DefaultResponseImplStyle0;
import com.poldroc.autoresponse.common.DefaultResponseImplStyle1;
import com.poldroc.autoresponse.common.Response;
import com.poldroc.autoresponse.common.ResponseStatus;
import com.poldroc.autoresponse.core.AutoResponseProperties;
import com.poldroc.autoresponse.factory.ResponseFactory;
import com.poldroc.autoresponse.factory.ResponseStatusFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供的默认的 ResponseMetaFactory 实现类
 * @author Poldroc
 * @date 2024/2/2
 */

public class DefaultResponseFactoryImpl implements ResponseFactory {

    private final Logger logger = LoggerFactory.getLogger(DefaultResponseFactoryImpl.class);

    private static final Integer RESPONSE_STYLE_0 = 0;

    private static final Integer RESPONSE_STYLE_1 = 1;

    @Resource
    private AutoResponseProperties properties;

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    @Override
    public Response newEmptyInstance() {
        return generateDefaultResponse();
    }

    private Response generateDefaultResponse() {
        Integer responseStyle = properties.getResponseStyle();
        if (responseStyle == null || RESPONSE_STYLE_0.equals(responseStyle)) {
            return new DefaultResponseImplStyle0();
        } else if (RESPONSE_STYLE_1.equals(responseStyle)) {
            return new DefaultResponseImplStyle1();
        } else {
            logger.error("未知的响应风格，responseStyle={}", responseStyle);
            throw new IllegalArgumentException("未知的响应风格");
        }
    }

    @Override
    public Response newInstance(ResponseStatus statusLine) {
        Response bean = this.newEmptyInstance();
        bean.setStatus(statusLine);
        return bean;
    }

    @Override
    public Response newSuccessInstance() {
        Response response = this.newEmptyInstance();
        response.setStatus(responseStatusFactory.defaultSuccess());
        return response;
    }

    @Override
    public Response newSuccessInstance(Object data) {
        Response response = this.newSuccessInstance();
        response.setPayload(data);
        return response;
    }

    @Override
    public Response newFailInstance() {
        Response bean = this.newEmptyInstance();
        bean.setStatus(responseStatusFactory.defaultError());
        return bean;
    }
}
