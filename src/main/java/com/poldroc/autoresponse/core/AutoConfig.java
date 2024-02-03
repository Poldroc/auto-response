package com.poldroc.autoresponse.core;

import com.poldroc.autoresponse.core.AutoResponseProperties;
import com.poldroc.autoresponse.core.advice.ObjectResponseBodyAdvice;
import com.poldroc.autoresponse.core.advice.VoidResponseBodyAdvice;
import com.poldroc.autoresponse.factory.ResponseFactory;
import com.poldroc.autoresponse.factory.ResponseStatusFactory;
import com.poldroc.autoresponse.factory.impl.DefaultResponseFactoryImpl;
import com.poldroc.autoresponse.factory.impl.DefaultResponseStatusFactoryImpl;
import com.poldroc.autoresponse.starter.Init;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 * @author Poldroc
 * @date 2024/1/21
 */
@Configuration
@EnableConfigurationProperties(AutoResponseProperties.class)
public class AutoConfig {

    @Bean
    @ConditionalOnMissingBean(ObjectResponseBodyAdvice.class)
    public ObjectResponseBodyAdvice objectResponseBodyAdvice(){
        return new ObjectResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(VoidResponseBodyAdvice.class)
    public VoidResponseBodyAdvice voidResponseBodyAdvice(){
        return new VoidResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(value = {ResponseFactory.class})
    public ResponseFactory responseBeanFactory() {
        return new DefaultResponseFactoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean(value = {ResponseStatusFactory.class})
    public ResponseStatusFactory responseStatusFactory() {
        return new DefaultResponseStatusFactoryImpl();
    }

    @Bean
    public Init init(){
        return new Init();
    }
}
