# Auto Response

Auto Response是基于Spring Boot的一个自动包装接口响应结果的处理器，功能包括但不限于：统一返回值封装、全局异常处理、自定义配置状态码等，使用该组件，开发人员无需每次都手动处理返回结果的格式和结构，简化了异常的捕获和处理，从而减少了繁琐的重复性工作。



## 快速入门





## Response格式

Auto Response内置了两种风格的响应格式，并通过`auto-response.response-style`进行配置

- auto-response.response-style=0，或者不配置（默认情况）

将以以下的格式进行返回：

```json
{
  "code": "",
  "msg": "",
  "data": {
  }
}
```

- auto-response.response-style=1

将以以下的格式进行返回：

```json
{
  "status": {
    "code": 0,
    "msg": ""
  },
  "payload": {
  }
}
```

- 自定义响应格式 如果以上两种格式均不能满足业务需要，可以通过自定义。



## 常用配置项

```yaml
auto-response:
  # 是否打印异常日志，默认为false
  print-exception-in-global-advice: false
  # 是否打印异常别名注册成功的日志，默认为false
  print-alias-register-success-log: false
  # Response风格，不配置默认为0
  response-style: 0
  # 自定义的成功响应码，不配置则为0
  default-success-code: 0
  # 自定义的成功提示，默认为ok
  default-success-msg: ok
  # 自定义的失败响应码，默认为1
  default-error-code: 1
  # 自定义的失败提示，默认为error
  default-error-msg: error
  # 不使用 @AutoExceptionMapper 和 @AutoExceptionAliasFor 修饰的原生异常
  # 是否使用异常信息Throwable类的detailMessage进行返回
  # originExceptionUsingDetailMessage=false，则msg=defaultErrorMsg
  # 默认为false
  use-origin-exception-detail-message: true
```