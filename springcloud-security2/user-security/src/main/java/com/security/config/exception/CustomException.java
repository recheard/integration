package com.security.config.exception;

import com.security.common.result.CommonResult;
import com.security.common.result.ErrorCodeEnum;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class CustomException extends RuntimeException {

    private CommonResult commonResult;

    public CustomException(CommonResult commonResult) {
        this.commonResult = commonResult;
    }

    /**
     * 自定义异常构造方法
     * @param errorCodeEnum 错误枚举类
     */
    public CustomException(ErrorCodeEnum errorCodeEnum) {
        this.commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setErrorCode(errorCodeEnum.getErrorCode());
        commonResult.setErrorMsg(errorCodeEnum.getErrorMsg());
    }

    /**
     * 自定义异常构造方法
     * @param errorMessage 错误信息
     */
    public CustomException(String errorMessage) {
        this.commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setErrorCode("1000");
        commonResult.setErrorMsg(errorMessage);
    }



}
