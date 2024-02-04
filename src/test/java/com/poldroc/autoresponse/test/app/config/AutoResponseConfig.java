package com.poldroc.autoresponse.test.app.config;

import com.poldroc.autoresponse.core.AbstractAutoExceptionAliasForRegisterConfig;
import com.poldroc.autoresponse.core.AutoExceptionAliasForRegister;
import com.poldroc.autoresponse.test.app.exception.NotFoundUriException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoResponseConfig extends AbstractAutoExceptionAliasForRegisterConfig {
    @Override
    protected void registerAliasFor(AutoExceptionAliasForRegister register) {
        register.doRegisterExceptionAliasFor(NotFoundUriException.class);
    }
}
