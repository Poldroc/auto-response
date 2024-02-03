package com.poldroc.autoresponse.core;

import com.poldroc.autoresponse.common.DefaultConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 核心配置类
 *
 * @author Poldroc
 * @date 2024/1/21
 */
@ConfigurationProperties(prefix = "auto-response")
public class AutoResponseProperties {

    /**
     * responseStyle的风格
     * 0（默认）: DefaultResponseImplStyle0
     * 1: DefaultResponseImplStyle1
     */
    private Integer responseStyle;

    /**
     * 默认的成功返回码
     */
    private String defaultSuccessCode = DefaultConstants.DEFAULT_SUCCESS_CODE;

    /**
     * 默认的成功提示
     */
    private String defaultSuccessMsg = DefaultConstants.DEFAULT_SUCCESS_MSG;

    /**
     * 默认的失败码
     */
    private String defaultErrorCode = DefaultConstants.DEFAULT_ERROR_CODE;

    /**
     * 默认的失败提示
     */
    private String defaultErrorMsg = DefaultConstants.DEFAULT_ERROR_MSG;

    public Integer getResponseStyle() {
        return responseStyle;
    }

    public void setResponseStyle(Integer responseStyle) {
        this.responseStyle = responseStyle;
    }

    public String getDefaultSuccessCode() {
        return defaultSuccessCode;
    }

    public void setDefaultSuccessCode(String defaultSuccessCode) {
        this.defaultSuccessCode = defaultSuccessCode;
    }

    public String getDefaultSuccessMsg() {
        return defaultSuccessMsg;
    }

    public void setDefaultSuccessMsg(String defaultSuccessMsg) {
        this.defaultSuccessMsg = defaultSuccessMsg;
    }

    public String getDefaultErrorCode() {
        return defaultErrorCode;
    }

    public void setDefaultErrorCode(String defaultErrorCode) {
        this.defaultErrorCode = defaultErrorCode;
    }

    public String getDefaultErrorMsg() {
        return defaultErrorMsg;
    }

    public void setDefaultErrorMsg(String defaultErrorMsg) {
        this.defaultErrorMsg = defaultErrorMsg;
    }


}
