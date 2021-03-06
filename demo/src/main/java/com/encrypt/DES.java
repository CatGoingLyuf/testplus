package com.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * 加密/解密 工具类
 * @author husiwei
 * @date 2021/8/6
 */
public class DES {

	/**
     * DES加密方法
     * @param message 加密数据
     * @param keyString 密钥
     * @return 加密结果
     */
    public static String encryptByDes(String message, String keyString) {
    	if(StringUtils.isEmpty(message) || StringUtils.isEmpty(keyString)){//当 加密数据或密钥 为空时，不做加密操作
            return StringUtils.EMPTY;
        }
        String keyHexString = stringToHexString(keyString);
        byte[] key = hexStringToBytes(keyHexString);
        String dataHexString = stringToHexString(message);
        byte[] data = hexStringToBytes(dataHexString);
        try {
            SecureRandom sr = new SecureRandom();
            SecretKeyFactory keyFactory;
            DESKeySpec dks = new DESKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, sr);
            byte[] result = cipher.doFinal(data);
            return new String(Base64.encodeBase64(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }
    
    /**
     * DES解密方法
     * @param dataHexString 解密数据
     * @param keyString 密钥
     * @return 解密后的值 
     */
    public static String decryptByDes(String dataHexString, String keyString) {
    	if(StringUtils.isEmpty(dataHexString) || StringUtils.isEmpty(keyString)){//当 解密数据或密钥 为空时，不做加密操作
            return StringUtils.EMPTY;
        }
        String keyHexString = stringToHexString(keyString);
        byte[] key = hexStringToBytes(keyHexString);
        byte[] result = null;
        try {
            SecureRandom sr = new SecureRandom();
            SecretKeyFactory keyFactory;
            DESKeySpec dks = new DESKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretkey, sr);
            result = cipher.doFinal(Base64.decodeBase64(dataHexString));
            return new String(result).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 字符串转16进制字符串
     * @param str 字符串
     * @return 转后的字符串
     */
    public static String stringToHexString(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }
    
    /**
     * 16进制字符串转成byte数组
     * @param hexString 字符串
     * @return 转后的字符串
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        int hexLength = length;
        while (hexLength % 8 != 0) {
            hexLength++;
        }
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[hexLength];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    
    /**
     * byte数组转换成16进制字符串
     * @param b byte数组
     * @return 转换成16进制字符串
     */
    public static String bytesToHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    /**
     * 
     * @param c 字符
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    
    /**
     * 16进制字符串转字符串
     * @param str 字符串
     * @return 字符串
     */
    public static String hexStringToString(String str) {
        byte[] baKeyword = new byte[str.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            str = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return str;
    }

    /**
     * md5加密
     * @param data 原始数据
     * @return 加密数据(16进制字符串)
     * @author 范立松
     * @date 2018年8月23日 上午9:06:55
     */
    public static String md5Digest(String data) {
        byte[] bytes = DigestUtils.md5Digest(data.getBytes(StandardCharsets.UTF_8));
        return byte2hex(bytes);
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
     * 测试函数
     * @param args 参数
     * @date 2015年11月30日 下午3:02:07
     */
    public static void main(String[] args) {
        // des 加密
        String str = "18989861590";
        String encryptStr = encryptByDes(str, "50739180");
        System.out.println("加密后的字符串 = " + encryptStr);

        // des 解密
         String decryptStr = decryptByDes(encryptStr,"50739180");
         System.out.println("解密后的字符串 = " + decryptStr);
    }
    
}