package com.imageVerifyCode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 *
 * @author fengxuechao
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param uuid
     * @param request
     * @throws Exception
     */
    void create(String uuid, ValidateCodeType type, ServletWebRequest request) throws Exception;


    /**
     * 校验验证码
     *
     * @param uuid
     * @param code
     * @param type
     */
    void validate(String uuid, ValidateCode code, ValidateCodeType type);

}
