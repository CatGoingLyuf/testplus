package com.encrypt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @Author lyuf
 * @Date 2022/5/20 14:17
 * @Version 1.0
 */
public class Sign {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("appId","a80a18c4416bb7729ac8");
        parameters.put("accessToken","6F50F7EE563B1BDC254A0E5A5C04E412");

        String appSecret = "f6ed86755e5b3a59";

        String encryptedSign = EncryptUtils.signTopRequest(parameters, appSecret, EncryptUtils.SIGN_METHOD_MD5);
        System.out.println(encryptedSign);
    }
}
