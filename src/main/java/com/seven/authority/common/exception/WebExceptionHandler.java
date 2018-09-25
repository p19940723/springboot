package com.seven.authority.common.exception;

import com.seven.authority.common.enums.StatusCodeEnmus;
import com.seven.authority.common.result.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author seven
 */
@Slf4j
@ControllerAdvice(basePackages = {"com.seven.authority"})
public class WebExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * 捕捉全局的Exception的错误并统一返回
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResultData controllerException(Throwable ex) {
        logger.error(ex.getMessage());
        if (ex instanceof CommonException) {
            StatusCodeEnmus.SYSTEM_ERROR.setMessage(ex.getMessage());
            StatusCodeEnmus.SYSTEM_ERROR.setCode(((CommonException) ex).getCode());
        } else {
            StatusCodeEnmus.SYSTEM_ERROR.setMessage(ex.getMessage());
        }
        return ResultData.error(StatusCodeEnmus.SYSTEM_ERROR);
    }
}
