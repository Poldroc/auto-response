package com.poldroc.autoresponse.core;

import com.poldroc.autoresponse.api.AutoExceptionAliasFor;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 异常别名注册器
 * 用于给异常注册别名，自定义异常的错误码和提示信息
 * 方便在全局异常处理器中获取
 * @author Poldroc
 * @date 2024/2/4
 */

public class AutoExceptionAliasForRegister {

    private final Logger logger = LoggerFactory.getLogger(AutoExceptionAliasForRegister.class);

    @Resource
    private AutoResponseProperties properties;

    private final ConcurrentHashMap<Class<? extends Throwable>, AutoExceptionAliasFor> aliasForMap = new ConcurrentHashMap<>();

    /**
     * 注册
     *
     * @param throwableClass 异常类
     */
    public AutoExceptionAliasForRegister doRegisterExceptionAliasFor(Class<? extends Throwable> throwableClass) {

        AutoExceptionAliasFor autoExceptionAliasFor = throwableClass.getAnnotation(AutoExceptionAliasFor.class);
        if (autoExceptionAliasFor == null) {
            logger.error("Auto Response : 异常未设置别名,无法被AutoResponse捕获,throwableClass={}", throwableClass);
            throw new RuntimeException();
        }

        Class<? extends Throwable>[] classes = autoExceptionAliasFor.aliasFor();
        for (Class<? extends Throwable> c : classes) {
            aliasForMap.put(c, autoExceptionAliasFor);
            if (properties.isPrintExceptionInGlobalAdvice()) {
                logger.info("Auto Response : 异常别名注册成功,aliasFor={},throwableClass={}", autoExceptionAliasFor, c);
            }
        }
        return this;
    }

    /**
     * 获取
     *
     * @param tClazz 异常类
     * @return
     */
    public AutoExceptionAliasFor getExceptionAliasFor(Class<? extends Throwable> tClazz) {
        return aliasForMap.get(tClazz);
    }
}
