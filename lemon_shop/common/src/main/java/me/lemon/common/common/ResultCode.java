package me.lemon.common.common;

public enum ResultCode implements IErrorCode{

    SUCCESS(200, "操作成功"),
    FAILED(400, "操作失败"),
    VALIDATE_FAILED(204, "参数检验失败"),
    UNAUTHORIZED(201, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    USER_NAME_NOT_FOUND(205,"登录用户名错误"),
    LOGIN_PASSWORD_ERROR(406,"登录密码错误");
    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
