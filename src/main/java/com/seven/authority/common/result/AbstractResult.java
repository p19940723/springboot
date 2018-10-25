package com.seven.authority.common.result;

import com.seven.authority.common.enums.StatusCodeEnums;

/**
 * @author seven
 */
public class AbstractResult {

    private String code;

    private String message;
	
	AbstractResult(StatusCodeEnums statusCodeEnums) {
        this.code = statusCodeEnums.getCode();
        this.message = statusCodeEnums.getMessage();
    }

    AbstractResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
