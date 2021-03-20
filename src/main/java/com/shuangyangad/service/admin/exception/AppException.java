package com.shuangyangad.service.admin.exception;

public class AppException extends RuntimeException {

    public enum MESSAGE {
        NO_AUTHORIZATION("未授权", "401"),
        FILURE_WECHAT_LOGIN("微信登录失败", "401"),
        ERROR_PASSWORD("密码错误", "1000"),
        NOT_EXISTS_USER_ID("用户不存在", "1000"),
        PARAMS_NULL_CLIENT_NAME("客户端名称为空", ""),
        PARAMS_ILLEGAL_CLIENT_NAME("客户端名称不合法", ""),
        FILURE_INSERT_DB("存入数据库失败", "");

        String message;
        String code;

        MESSAGE(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public AppException() {
        super();
    }

    protected MESSAGE apiMessage;

    public MESSAGE getApiMessage() {
        return apiMessage;
    }

    public AppException(MESSAGE message) {
        super(message.getMessage());
        this.apiMessage = message;
    }
}
