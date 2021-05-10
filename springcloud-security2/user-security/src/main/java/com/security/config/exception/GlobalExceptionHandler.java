package com.security.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.security.common.result.ErrorCodeEnum;
import com.security.common.result.FailureResponseUtil;
import com.security.common.result.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局捕获异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局捕获异常处理方法
     *
     * @param e 异常
     */
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e, HttpServletResponse response) {
        log.error("报错内容:", e);
        FailureResponseUtil.failureResponse(response, JSONObject.toJSONString(ResponseUtil.serious()),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * 捕获自定义异常处理方法
     *
     * @param e 异常
     */
    @ExceptionHandler(value = CustomException.class)
    void handelCustomException(CustomException e, HttpServletResponse response) {
        FailureResponseUtil.failureResponse(response, JSONObject.toJSONString(e.getCommonResult()),
                HttpStatus.OK.value());
    }

    /**
     * 参数校验全局捕获异常
     * 
     * @param exception 异常对象
     * @param response 响应对象
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
    public void handleMethodArgumentNotValidException(Exception exception, HttpServletResponse response) {
        String errorInfo = "";
        if (exception instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
            errorInfo = bindingResult.getFieldErrors().get(0).getDefaultMessage();
        }
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                errorInfo = errorInfo + "," + item.getMessage();
            }
        }
        FailureResponseUtil.failureResponse(response,
                JSONObject.toJSONString(ResponseUtil.fail(ErrorCodeEnum.PARAM_ERROR.getErrorCode(), errorInfo)),
                HttpStatus.BAD_REQUEST.value());
    }

}
