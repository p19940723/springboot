package com.seven.authority.common.result;


import com.seven.authority.common.enums.StatusCodeEnums;

/**
 * 返回一般数据格式
 * @author seven
 * @param <T>
 */

public class ResultData<T> extends AbstractResult {

	private T data;
	
	@SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> ok() {
        return new ResultData(StatusCodeEnums.SUCCESS);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error() {
        return new ResultData(StatusCodeEnums.SYSTEM_ERROR);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error(StatusCodeEnums statusCodeEnums) {
        return new ResultData(statusCodeEnums);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error(String code, String message) {
        return new ResultData(code, message);
    }

    private ResultData(StatusCodeEnums statusCodeEnums) {
        super(statusCodeEnums);
    }

    private ResultData(String code, String message) {
        super(code, message);
    }

    public static <T> ResultData<T> one(T obj) {
        ResultData<T> res = new ResultData<>(StatusCodeEnums.SUCCESS);
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
