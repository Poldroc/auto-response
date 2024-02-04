package com.poldroc.autoresponse.api;

import java.lang.annotation.*;
/**
 * 异常映射注解
 * @author Poldroc
 * @date 2024/2/4
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AutoExceptionMapper {

    /**
     * 异常对应的错误码
     *
     * @return 异常对应的错误码
     */
    String code() default "error";

    /**
     * 异常信息
     *
     * @return 异常对应的提示信息
     */
    String msg() default "unknown anomaly!";

    /**
     * 异常信息是否支持替换
     * 仅当 msgReplaceable==ture，且异常实例的 message 不为空时才能替换
     *
     * @return
     */
    boolean msgReplaceable() default false;

}
