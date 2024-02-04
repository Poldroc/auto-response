package com.poldroc.autoresponse.test.app.controller;

import com.poldroc.autoresponse.core.AutoResponse;
import com.poldroc.autoresponse.test.app.exception.ExampleExceptions;
import com.poldroc.autoresponse.test.app.model.req.UserInfoQuery;
import com.poldroc.autoresponse.test.app.model.resp.UserInfoResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RequestMapping("test")
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 测试非空返回值.
     */
    @GetMapping("/object")
    public UserInfoResp testResponse() {
        logger.info("testResponse");
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setId("1");
        userInfoResp.setName("name");
        return userInfoResp;
    }

    /**
     * 测试null.
     */
    @GetMapping("/null")
    public UserInfoResp testNull() {
        logger.info("testNull");
        return null;
    }

    /**
     * 测试空返回值.
     */
    @GetMapping("/void")
    public void testNullResponse() {
        logger.info("testVoidResponse");
    }

    /**
     * 测试未知异常.
     */
    @GetMapping("/exception")
    public void testException() {
        logger.info("testException");
        throw new RuntimeException("testException");
    }

    @GetMapping("/throwable")
    public void testThrowable(UserInfoQuery dto) throws Throwable {
        logger.info(dto.toString());
        throw new Throwable();
    }

    /**
     * 测试自定义异常.
     */
    @GetMapping("/mapperException")
    public void testMapperException() {
        throw new ExampleExceptions.TestMapperException();
    }

    @GetMapping("/raiseException0")
    public void raiseException0(){
        AutoResponse.raiseException("520", "测试手工异常0");
    }

    @GetMapping("/assert0/{id}")
    public void assert0(@PathVariable("id") Integer id) {
        AutoResponse.wrapAssert(() -> Assert.isTrue(id == 1, "id不等于1"));
    }

    @GetMapping("/assert1/{id}")
    public void assert1(@PathVariable("id") Integer id) {
        AutoResponse.wrapAssert("1001", () -> Assert.isTrue(id == 1, "id不等于1"));
    }
}
