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
//        String backPasswd = EncryptUtils.sha512("263fec58861449aacc1c328a4aff64aff4c62df4a2d50b3f207fa89b6e242c9aa778e7a8baeffef85b6ca6d2e7dc16ff0a760d59c13c238f6bcdc32f8ce9cc62","263fec58861449aacc1c328a4aff64aff4c62df4a2d50b3f207fa89b6e242c9aa778e7a8baeffef85b6ca6d2e7dc16ff0a760d59c13c238f6bcdc32f8ce9cc62");
        String backPasswd2 = EncryptUtils.sha512("983c0b448f310df9febe3151477f30c3ef248f80363d90e5c2bc6b37c9b32766dc335d39c239ecfefc6063b4c8efe9b7efa7e7e4639128b13bed98f597d02c39","983c0b448f310df9febe3151477f30c3ef248f80363d90e5c2bc6b37c9b32766dc335d39c239ecfefc6063b4c8efe9b7efa7e7e4639128b13bed98f597d02c39");
        System.out.println(backPasswd2);
    }

    @Test
    public void test2() {
        String a = DES.decryptByDes("WJmyliPgL1hlCUmeda+aQg==","");
        System.out.println(a);
    }
}
