package com.poldroc.autoresponse.core.advice;

import com.poldroc.autoresponse.api.AutoExceptionAliasFor;
import com.poldroc.autoresponse.api.AutoExceptionMapper;
import com.poldroc.autoresponse.common.Response;
import com.poldroc.autoresponse.common.ResponseStatus;
import com.poldroc.autoresponse.core.AutoExceptionAliasForRegister;
import com.poldroc.autoresponse.common.AutoResponseException;
import com.poldroc.autoresponse.core.AutoResponseProperties;
import com.poldroc.autoresponse.factory.ResponseFactory;
import com.poldroc.autoresponse.factory.ResponseStatusFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 全局异常处理
 * @author Poldroc
 * @date 2024/2/4
 */

@ControllerAdvice
@Order(200)
public class GlobalExceptionAdvice implements ApplicationContextAware {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @Resource
    private AutoResponseProperties properties;

    private AutoExceptionAliasForRegister autoExceptionAliasForRegister;

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    @Resource
    private ResponseFactory responseFactory;


    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public Response exceptionHandler(Throwable throwable) {
        if (properties.isPrintExceptionInGlobalAdvice()) {
            logger.error("Auto Response : GlobalExceptionAdvice捕获到异常,message=[{}]", throwable.getMessage(), throwable);
        }
        ResponseStatus statusLine;
        if (throwable instanceof AutoResponseException) {
            statusLine = getStatusFromAutoResponseExceptionInstance((AutoResponseException) throwable);
        } else {
            statusLine = getStatusFromExceptionInstance(throwable);
        }
        return responseFactory.newInstance(statusLine);
    }

    private ResponseStatus getStatusFromExceptionInstance(Throwable throwable) {
        Class<? extends Throwable> clazz = throwable.getClass();

        // 如果有被@AutoExceptionMapper注解的类，就用它
        AutoExceptionMapper autoExceptionMapper = clazz.getAnnotation(AutoExceptionMapper.class);
        if (autoExceptionMapper != null) {
            boolean msgReplaceable = autoExceptionMapper.msgReplaceable();
            // 如果msgReplaceable为true，并且throwable的message不为空，就用throwable的原始message
            if (msgReplaceable) {
                String throwableMessage = throwable.getMessage();
                if (throwableMessage != null) {
                    return responseStatusFactory.newInstance(autoExceptionMapper.code(), throwableMessage);
                }
            }
            return responseStatusFactory.newInstance(autoExceptionMapper.code(), autoExceptionMapper.msg());
        }

        // 如果有被@AutoExceptionAliasFor注解的类，就用它
        if (autoExceptionAliasForRegister != null) {
            AutoExceptionAliasFor autoExceptionAliasFor = autoExceptionAliasForRegister.getExceptionAliasFor(clazz);
            if (autoExceptionAliasFor != null) {
                return responseStatusFactory.newInstance(autoExceptionAliasFor.code(), autoExceptionAliasFor.msg());
            }
        }

        ResponseStatus defaultError = responseStatusFactory.defaultError();

        // 如果都没有被@AutoExceptionMapper或@AutoExceptionAliasFor注解的类
        // 并且为原生异常和 useOriginExceptionDetailMessage=true
        // 如果有自定义的异常信息，原生异常将直接使用异常信息进行返回，不再返回默认错误提示
        if (properties.getUseOriginExceptionDetailMessage() && throwable.getMessage() != null) {
            return responseStatusFactory.newInstance(defaultError.getCode(), throwable.getMessage());
        }

        return defaultError;
    }

    private ResponseStatus getStatusFromAutoResponseExceptionInstance(AutoResponseException throwable) {
        String code = throwable.getCode() == null ? properties.getDefaultErrorCode() : throwable.getCode();
        String msg = throwable.getMsg() == null ? properties.getDefaultErrorMsg() : throwable.getMsg();
        return responseStatusFactory.newInstance(code, msg);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.autoExceptionAliasForRegister = applicationContext.getBean(AutoExceptionAliasForRegister.class);
    }
}
