package com.seven.authority.common.result;

import com.seven.authority.common.enums.StatusCodeEnmus;

/**
 * @author seven
 */
public class AbstractResult {

	String code;
	
	String message;
	
	public AbstractResult(StatusCodeEnmus statusCodeEnmus) {
        this.code = statusCodeEnmus.getCode();
        this.message = statusCodeEnmus.getMessage();
    }

    public AbstractResult(String code, String message) {
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
