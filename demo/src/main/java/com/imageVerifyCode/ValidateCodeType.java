package com.imageVerifyCode;

/**
 * 校验码类型
 *
 * @author fengxuechao
 * @date 2019-12-11
 */
public enum ValidateCodeType {

    /**
     * 短信验证码, 暂时不用，任然使用旧的手机验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return "smsCode";
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return "imageCode";
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();

}
