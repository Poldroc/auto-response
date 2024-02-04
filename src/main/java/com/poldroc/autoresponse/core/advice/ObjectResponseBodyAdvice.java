package com.poldroc.autoresponse.core.advice;

import com.poldroc.autoresponse.api.ExcludeFromAutoResponse;
import com.poldroc.autoresponse.common.Response;
import com.poldroc.autoresponse.factory.ResponseFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 全局结果处理的切面类，实现了 ResponseBodyAdvice 接口，
 * 在返回响应体之前对控制器方法的返回值进行修改或添加额外的处理逻辑
 * 用于对非空返回结果进行统一处理
 *
 * @author Poldroc
 * @date 2024/2/1
 */
@ControllerAdvice
@Order(value = 1000)
public class ObjectResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final Logger logger = LoggerFactory.getLogger(ObjectResponseBodyAdvice.class);

    private final Boolean IS_LOG_DEBUG = logger.isDebugEnabled();

    @Resource
    private ResponseFactory responseFactory;

    /**
     * 判断是否支持对该控制器方法的返回值进行处理
     * 1. 不为Void
     * 2. 且为 MappingJackson2HttpMessageConverter 支持的类型
     *
     * @param returnType    控制器方法的返回类型
     * @param converterType 消息转换器的类型 通常是 MappingJackson2HttpMessageConverter
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = returnType.getMethod();

        // method为空、返回值为void、或者消息转换器不是 MappingJackson2HttpMessageConverter 的子类、实现类，或者是它本身时，不进行处理
        if (Objects.isNull(method)
                || method.getReturnType().equals(Void.TYPE)
                || !MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType)) {
            if (IS_LOG_DEBUG) {
                logger.debug("Auto Response : method为空、返回值为void、非JSONConverter，跳过");
            }
            return false;
        }

        // 如果方法上标注了 ExcludeFromGiveMeResp 注解，则不对该方法的返回值进行处理
        if (method.isAnnotationPresent(ExcludeFromAutoResponse.class)) {
            if (IS_LOG_DEBUG) {
                logger.debug("Auto Response : 方法上标注了 @ExcludeFromGiveMeResp 注解，跳过");
            }
            return false;
        }

        logger.debug("Auto Response : 对方法 {} 的返回值进行处理", method.getName());
        return true;

    }

    /**
     * 在返回响应体之前对控制器方法的返回值进行修改或添加额外的处理逻辑
     *
     * @param body                    控制器方法的返回值
     * @param returnType              控制器方法的返回类型
     * @param selectedContentType     选择的内容类型
     * @param selectedConverterType   选择的转换器类型
     * @param request                 请求
     * @param response                响应
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return responseFactory.newSuccessInstance();
        } else if (body instanceof Response) {
            return body;
        } else {
            if (IS_LOG_DEBUG) {
                String path = request.getURI().getPath();
                logger.debug("Auto Response : 对路径 {} 的非空返回值进行封装", path);
            }
            return responseFactory.newSuccessInstance(body);
        }
    }
}
