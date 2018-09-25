package com.seven.authority.common.enums;

/**
 * 系统返回码
 * @author Segoul
 *
 */

public enum StatusCodeEnmus {

	SUCCESS("200","操作成功"),
    NODATA("201","操作成功无记录"),
    RESQUEST_FEAILED("400","请求参数错误"),
    LOGIN_ERROR("401","用户名或密码错误！"),
    LOGIN_STATUS_ERROR("402", "账户不存在或被禁用"),
    API_NOT_PER("403", "没有该接口的访问权限"),
    API_NOT_EXISTS("404", "请求的接口不存在"),
    SIGN_ERROR("405", "数据签名错误"),
    UNKNOWN_IP("406", "非法IP请求"),
    VERIFICATION_TIMEOUT("407","验证码失效"),
    VERIFICATION_ERROR("408","验证码错误"),
    MOBILE_INVALID("409","电话格式不正确"),
    MANAGE_MOBILE_EXISTS("410","此手机号已被占用"),
    COMPANY_MANEGE_EXISTS("411","此账户已有公司"),
    COMPANY_MOBILE_EXISTS("412","公司电话已被占用"),
    SHOP_MOBILE_EXISTS("413","店铺电话已被占用"),
    MANAGE_SHOP_EXISTS("414","此店铺已有店长"),
    SHOPPER_MOBILE_EXISTS("415","手机号已被占用"),
    FIRMWX_MOBILE_EXISTS("421","企业微信手机号已被占用"),
    MANAGE_EXISTS("416","请先删除店长"),
    SHOPPER_EXISTS("417","请先删除服务员"),
    MOBILE_EXISTS("418","此手机号已被占用,请在员工列表中删除该手机号!"),
    CREATE_USER_FAILURE("419","企业微信用户创建失败"),
    ENTERPRISE_MOBILE_EXISTS("420","系统无此手机号，绑定店掌柜账号失败！"),
    APP_ID_NOEXISTS("424","该公司暂未绑定小程序，请联系管理员!"),
    APP_ID_EXISTS("421","此appid已被绑定！"),
    AGENT_ID_EXISTS("423","该公司暂未绑定企业微信，请联系管理员!"),
    UNLOGIN("410", "未登录"),
    FEAILED("500","查询失败"),
    SYSTEM_ERROR("501", "系统异常"),
    UNKNOWN_ERROR("502", "未知异常"),

    INVALID_TOKEN_LOGIN("800000","登录Token失效"),
    INVALID_CODE_LOGIN("800001","获取验证码超时"),
    SEND_SEND_MSG("800002","验证码发送失败"),
    PHONE_NO_LIVE("800003","该手机号未注册boss账号，请联系客服"),
    USER_AUTHORITY_NOT_ENOUGH("800004","用户权限不足"),

    FAIL("900000","操作失败"),
	
	DATA_NOT_EXIST("900003", "要操作的数据不存在"),
	DATA_BASE_OPERATE_FAIL("900004", "数据库操作失败"),
	DATA_IS_EXIST("900005", "要操作的数据已存在"),
	//分销等级设置
	NOT_OVERSTEP_DISTRIBUTE("900006", "不可越级添加分销等级设置"),
	DISTRIBUTE_MUST_OVER_LEVEL("900007", "分销满足条件必须大于上一等级"),
	DISTRIBUTE_NOT_LESS_LEVEL("900008", "分销比例不得小于上一等级"),
	DISTRIBUTE_IS_EXIST_NEXT_LEVEL("900009", "分销等级存在下一等级"),
	DISTRIBUTE_MUST_LESS_LEVEL("900010", "分销满足条件必须小于下一等级"),
	DISTRIBUTE_NOT_OVER_LEVEL("900011", "分销比例不得大于下一等级"),
	ACCOUNT_BALANCE_NOT_ENOUGH("900012","账户余额不足"),
	LEVEL_INTERGER_MUST_TEN("900013","分销等级仅限1到10的正整数"),
	RATIO_INTERGER_MUST_HUNDRED("900014","分销比例仅限1到100的正整数"),
	
	//名片
	COMPANY_EXISTS("5000","公司不存在"),
	CARD_EXISTS("5001","名片不存在"),
	SHOP_EXISTS("5002","店铺不存在"),
	SHOPSHOPPER_EXISTS("5003","店员不存在"),
	SLAVE_USER("5004","用户不存在"),
	DATA_ADD_ERROR("5005","插入失败"),
	DATA_UPDATE_ERROR("5006","更新失败"),
	DATA_DELETE_ERROR("5007","删除失败"),
	LABLE_EXISTS("5008","印象标签不存在"),
	INFO_IMG_ERROR("5009","信息图片不存在"),
    LABEL_LIKE_ERROT("5010","印象标签操作失败"),
	
	
	//店员管理
	OPENID_PUBLICNUMID_IS_EXIST("900113","openID和公众号ID已被绑定"),
	SHOPPER_RELATE_OPENID_PUBLICNUMID("900114","该店员已经绑定openID和公众号ID"),
	CARD_SHARE_LINK_IS_ACTIVE("900115","该名片已被激活，您可查看公司其他名片"),
	
	//ai雷达
	STARTTIME_NOT_OVER_ENDTIME("900201","结束时间要大于等于开始时间");
	
	
	private String code;
	private String message;
	
	StatusCodeEnmus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusCodeEnmus getByCode(String code) {
        for (StatusCodeEnmus statusCodeEnmus : StatusCodeEnmus.values()) {
            if (statusCodeEnmus.getCode().equals(code)) {
                return statusCodeEnmus;
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
