package com.poldroc.autoresponse.test.app.controller;

import com.poldroc.autoresponse.test.app.model.resp.UserInfoResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
