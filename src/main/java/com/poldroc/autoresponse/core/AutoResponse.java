package com.poldroc.autoresponse.core;

import com.poldroc.autoresponse.api.AssertFunction;
import com.poldroc.autoresponse.common.AutoResponseException;

/**
 * AutoResponse工具类
 *
 * @author Poldroc
 * @date 2024/2/4
 */

public class AutoResponse {

    /**
     * 需要抛自定义异常时，调用该方法
     *
     * @param code 异常码
     * @param msg  异常提示
     */
    public static void raiseException(String code, String msg) {
        throw new AutoResponseException(code, msg);
    }


    /**
     * 需要抛自定义异常时，调用该方法
     *
     * @param code      异常码
     * @param msg       异常提示
     * @param throwable 捕获的异常
     */
    public static void raiseException(String code, String msg, Throwable throwable) {
        throw new AutoResponseException(code, msg, throwable);
    }

    /**
     * 断言函数
     *
     * @param assertFunction 断言函数
     */
    public static void wrapAssert(AssertFunction assertFunction) {
        try {
            assertFunction.doAssert();
        } catch (Exception e) {
            throw new AutoResponseException(e.getMessage(), e);
        }
    }

    public static void wrapAssert(String code, AssertFunction assertFunction) {
        try {
            assertFunction.doAssert();
        } catch (Exception e) {
            throw new AutoResponseException(code, e.getMessage(), e);
        }
    }
}
