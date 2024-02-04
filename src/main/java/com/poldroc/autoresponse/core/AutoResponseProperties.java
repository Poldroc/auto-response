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

    /**
     * 是否打印异常别名注册成功的日志
     */
    private boolean printAliasRegisterSuccessLog = false;

    /**
     * 是否在全局异常处理器中打印异常，默认不打印
     */
    private boolean printExceptionInGlobalAdvice = false;

    /**
     * 不使用 @AutoExceptionMapper 和 @AutoExceptionAliasFor 修饰的原生异常
     * 是否使用异常信息Throwable类的detailMessage进行返回
     * originExceptionUsingDetailMessage=false，则msg=defaultErrorMsg
     * 默认为false
     */
    private Boolean useOriginExceptionDetailMessage = false;

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

    public boolean isPrintAliasRegisterSuccessLog() {
        return printAliasRegisterSuccessLog;
    }

    public void setPrintAliasRegisterSuccessLog(boolean printAliasRegisterSuccessLog) {
        this.printAliasRegisterSuccessLog = printAliasRegisterSuccessLog;
    }

    public boolean isPrintExceptionInGlobalAdvice() {
        return printExceptionInGlobalAdvice;
    }

    public void setPrintExceptionInGlobalAdvice(boolean printExceptionInGlobalAdvice) {
        this.printExceptionInGlobalAdvice = printExceptionInGlobalAdvice;
    }

    public Boolean getUseOriginExceptionDetailMessage() {
        return useOriginExceptionDetailMessage;
    }

    public void setUseOriginExceptionDetailMessage(Boolean useOriginExceptionDetailMessage) {
        this.useOriginExceptionDetailMessage = useOriginExceptionDetailMessage;
    }


}
