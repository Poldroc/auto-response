package com.poldroc.autoresponse.test.app.exception;

import com.poldroc.autoresponse.api.AutoExceptionAliasFor;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@AutoExceptionAliasFor(code = "1201", msg = "not found this uri", aliasFor = NoResourceFoundException.class)
public class NotFoundUriException extends RuntimeException{
}
