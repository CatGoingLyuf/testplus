package com.encrypt;

import org.junit.Test;

/**
 * @Author lyuf
 * @Date 2022/2/25 10:14
 * @Version 1.0
 */
public class demo {

    @Test
    public void test() {
        String s = EncryptUtils.sha512("awifi@123", "awifi@123");
        String s2 = EncryptUtils.sha512("awifi@123");
        System.out.println(s);
        System.out.println(s2);
        String backPasswd = EncryptUtils.sha512("Awifi@123");
        String backPasswd2 = EncryptUtils.sha512(backPasswd,backPasswd);
        System.out.println(backPasswd2);
        // 4e65baf89d3585f14fb5bab6017c6cc63ec3ad059e106f72a52c43c64f658e34e61c5fd601de049ffcce531103ffdc39862cb8dd0a1489398ba3ac6a265d941b
    }

    @Test
    public void test2() {
        String a = DES.decryptByDes("WJmyliPgL1hlCUmeda+aQg==","");
        System.out.println(a);
    }
}
