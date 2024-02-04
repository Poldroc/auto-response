package com.poldroc.autoresponse.test.app.exception;

import com.poldroc.autoresponse.api.AutoExceptionMapper;

public class ExampleExceptions {
    @AutoExceptionMapper(code = "1024", msg = "test @AutoExceptionMapper")
    public static class TestMapperException extends RuntimeException {
    }

}
