package com.poldroc.autoresponse.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 异常别名注册器模板类
 * @author Poldroc
 * @date 2024/2/4
 */
public abstract class AbstractAutoExceptionAliasForRegisterConfig implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(AbstractAutoExceptionAliasForRegisterConfig.class);

    /**
     * 注册别名
     * 使用doRegisterExceptionAliasFor方法注册别名异常类
     * @param register 注册器
     */
    protected abstract void registerAliasFor(AutoExceptionAliasForRegister register);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        try {
            AutoExceptionAliasForRegister aliasForRegister = applicationContext.getBean(AutoExceptionAliasForRegister.class);
            this.registerAliasFor(aliasForRegister);
        } catch (Exception e) {
            logger.warn("Auto Response : 未从ApplicationContext中获取到AutoExceptionAliasForRegister实例， @AutoExceptionAliasFor注解将无效", e);
        }
    }
}
