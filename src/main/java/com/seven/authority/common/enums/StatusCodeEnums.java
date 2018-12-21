package com.seven.authority.common.enums;

/**
 * 系统返回码
 *
 * @author Segoul
 */

public enum StatusCodeEnums {
    //操作成功
    SUCCESS("200", "操作成功"),
    //操作成功无记录
    NODATA("201", "操作成功无记录"),
    //请求参数错误
    RESQUEST_FEAILED("400", "请求参数错误"),
    //用户名或密码错误
    LOGIN_ERROR("401", "用户名或密码错误！"),
    //账户不存在或被禁用
    LOGIN_STATUS_ERROR("402", "账户不存在或被禁用"),
    //没有该接口的访问权限
    API_NOT_PER("403", "没有该接口的访问权限"),
    //请求的接口不存在
    API_NOT_EXISTS("404", "请求的接口不存在"),
    //数据签名错误
    SIGN_ERROR("405", "数据签名错误"),
    //非法IP请求
    UNKNOWN_IP("406", "非法IP请求"),
    //未登录
    UNLOGIN("410", "未登录"),
    //查询失败
    FEAILED("500", "查询失败"),
    //系统异常
    SYSTEM_ERROR("501", "系统异常"),
    //未知异常
    UNKNOWN_ERROR("502", "未知异常"),
    //日志插入异常
    LOG_ERROR("503", "日志插入异常");

    private String code;
    private String message;

    StatusCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusCodeEnums getByCode(String code) {
        for (StatusCodeEnums statusCodeEnums : StatusCodeEnums.values()) {
            if (statusCodeEnums.getCode().equals(code)) {
                return statusCodeEnums;
            }
        }
        return null;
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
