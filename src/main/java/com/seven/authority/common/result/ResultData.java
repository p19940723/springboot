package com.seven.authority.common.result;


import com.seven.authority.common.enums.StatusCodeEnums;

/**
 * 返回一般数据格式
 *
 * @param <T>
 * @author seven
 */

public class ResultData<T> extends AbstractResult {

    private T data;

    public ResultData(StatusCodeEnums statusCodeEnums) {
        super(statusCodeEnums);
    }

    public ResultData(String code, String message) {
        super(code, message);
    }

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
