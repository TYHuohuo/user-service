package com.tay.userservice.enums;
/**
 * <p>
 *  返回值状态枚举
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
public enum ResultEnum {
    /**
     * 成功
     */
    OK(200, "Success"),
    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 找不到
     */
    NOT_FOUND(404, "Not Found"),

    /**
     * 服务器错误
     */
    ERROR(500, "Server Error"),

    /**
     * 参数不正确
     */
    PARAM_ERROR(501,"Parameter Error");

    /**
     * 操作代码
     */
    int code;

    /**
     * 提示信息
     */
    String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
