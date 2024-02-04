package com.poldroc.autoresponse.starter;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 项目启动时的初始化操作
 * @author Poldroc
 * @date 2024/1/21
 */

public class Init {

    private final Logger logger = LoggerFactory.getLogger(Init.class);

    @PostConstruct
    public void init() {
        logger.info("Auto Response : Successful startup!");
    }
}
