package com.seven.authority.common.exception;


import com.seven.authority.common.enums.StatusCodeEnums;

/**
 * @author seven
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = -7171148211322503748L;

    private String message;

    private String code;

    public CommonException() {
        super();
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public CommonException(String code, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

    public CommonException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public CommonException(StatusCodeEnums statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public CommonException(String message) {
        super(message);
        this.message = message;
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
