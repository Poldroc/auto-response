package com.poldroc.autoresponse.starter.annotation;

import com.poldroc.autoresponse.starter.AutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * 注解启动全局结果处理的入口
 * @author Poldroc
 * @date 2024/1/21
 */

// 表示该注解可以被应用在类（ElementType.TYPE）
@Target({ElementType.TYPE})
// 表示该注解在运行时可以被反射机制读取（RetentionPolicy.RUNTIME）
@Retention(RetentionPolicy.RUNTIME)
// 表示该注解应该被包含在 Javadoc 中（@Documented）
@Documented
// 表示该注解可以被子类继承（@Inherited）
@Inherited
// 通过 @Import 注解导入了 AutoConfig 类，用于在启用该注解时执行一些自动配置的操作
@Import(AutoConfig.class)
public @interface EnableAutoResponse {
}
