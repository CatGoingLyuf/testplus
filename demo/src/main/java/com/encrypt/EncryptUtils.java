package com.encrypt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * SHA 加密工具类
 *
 * @author wei-yushan
 * @date 2021/08/05
 */
public final class EncryptUtils {

    public static final String SIGN_METHOD_MD5 = "md5";

    public static final String SIGN_METHOD_HMAC = "hmac";



    private EncryptUtils() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param rawStr 明文
     * @param salt   盐
     * @return 密文
     */
    public static String sha512(final String rawStr, final String salt) {
        return encrypt(rawStr, salt, "SHA-512");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param rawStr 明文
     * @return 密文
     */
    public static String sha512(final String rawStr) {
        return encrypt(rawStr, null, "SHA-512");
    }


    /**
     * 字符串 SHA 加密
     *
     * @param rawStr    明文
     * @param salt      盐
     * @param algorithm 算法
     * @return 密文
     */
    private static String encrypt(final String rawStr, String salt, final String algorithm) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (rawStr != null && rawStr.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                // 传入要加密的字符串
                messageDigest.update(rawStr.getBytes(StandardCharsets.UTF_8));
                // 得到 byte 类型结果
                byte[] byteBuffer;
                if (salt == null || "".equals(salt)) {
                    byteBuffer = messageDigest.digest();
                } else {
                    byteBuffer = messageDigest.digest(salt.getBytes(StandardCharsets.UTF_8));
                }
                // 將 byte 转换为 string
                StringBuffer strHexString = new StringBuffer();
                // 遍历 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    strHexString.append(Integer.toString((byteBuffer[i] & 0xff) + 0x100, 16).substring(1));
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }


    /**
     * hmac加密
     * @param data 原始数据
     * @param secret 秘钥
     * @return 加密数据
     * @throws IOException 异常
     * @author 范立松
     * @date 2018年8月23日 上午9:05:31
     */
    public static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * md5加密
     * @param data 原始数据
     * @return 加密数据
     * @author 范立松
     * @date 2018年8月23日 上午9:06:10
     */
    public static byte[] encryptMD5(String data) {
        return DigestUtils.md5Digest(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * byte[]转换为16进制字符串
     * @param bytes 字节数组
     * @return 16进制字符串
     * @author 范立松
     * @date 2018年8月23日 上午9:07:40
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 请求签名
     * @param params 请求参数
     * @param secret 秘钥
     * @param signMethod 签名算法(md5或hmac)
     * @return 签名
     * @throws IOException 异常
     * @author 范立松
     * @date 2018年8月23日 上午9:04:26
     */
    public static String signTopRequest(Map<String, String> params, String secret, String signMethod)
            throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                query.append(key).append(value);
            }
        }

        // 第三步：使用MD5/HMAC加密
        byte[] bytes;
        if (SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 第四步：把二进制转化为大写的十六进制(正确签名应该为32大写字符串，此方法需要时使用)
        return byte2hex(bytes);
    }
}
