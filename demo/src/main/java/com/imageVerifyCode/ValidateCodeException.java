package com.imageVerifyCode;

/**
 * 验证码异常类
 *
 * @author fengxuechao
 * @date 2019-12-11
 */
public class ValidateCodeException extends RuntimeException {

    private static final long serialVersionUID = 3527502858901055265L;

    private String code;

    public ValidateCodeException(String code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
    }

    public ValidateCodeException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
