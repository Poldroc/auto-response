package com.poldroc.autoresponse.api;

import java.lang.annotation.*;

/**
 * 使用此注解的controller直接返回, 不被修饰
 * @author Poldroc
 * @date 2024/2/1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcludeFromAutoResponse {
}
