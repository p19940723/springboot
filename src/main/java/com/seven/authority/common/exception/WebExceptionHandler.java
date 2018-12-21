package com.seven.authority.common.exception;

import com.seven.authority.common.enums.StatusCodeEnums;
import com.seven.authority.common.result.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author seven
 */
@Slf4j
@ControllerAdvice(basePackages = {"com.seven.authority"})
public class WebExceptionHandler {
    /**
     * 捕捉全局的CommonException的错误并统一返回
     *
     * @param e 错误对象
     * @return 给前台返回值错误数据
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ResultData error(CommonException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultData.error(e.getCode(), e.getMessage());
    }

    /**
     * 捕捉全局的Exception的错误并统一返回
     *
     * @param e 错误对象
     * @return 给前台返回值错误数据
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultData error(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultData.error(StatusCodeEnums.SYSTEM_ERROR);
    }
}
