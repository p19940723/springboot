package com.seven.authority.common.result;


import com.seven.authority.common.enums.StatusCodeEnmus;

/**
 * 返回一般数据格式
 * @author seven
 * @param <T>
 */

public class ResultData<T> extends AbstractResult {

	private T data;
	
	@SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> ok() {
        return new ResultData(StatusCodeEnmus.SUCCESS);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error() {
        return new ResultData(StatusCodeEnmus.SYSTEM_ERROR);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error(StatusCodeEnmus statusCodeEnmus) {
        return new ResultData(statusCodeEnmus);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error(String code, String message) {
        return new ResultData(code, message);
    }

    public ResultData(StatusCodeEnmus statusCodeEnmus) {
        super(statusCodeEnmus);
    }

    public ResultData(String code, String message) {
        super(code, message);
    }

    public static <T> ResultData<T> one(T obj) {
        ResultData<T> res = new ResultData(StatusCodeEnmus.SUCCESS);
        res.data = obj;
        return res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
