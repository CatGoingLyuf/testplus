package com.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA 加密工具类
 *
 * @author wei-yushan
 * @date 2021/08/05
 */
public final class EncryptUtils {

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
}
