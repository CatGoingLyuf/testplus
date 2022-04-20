package com.k8s;

import lombok.Data;

/**
 * 创建日期：2021/11/11
 * 创建作者：hupenghao
 * 文件名称：
 * 版本：1.0
 * 功能：
 * 最后修改时间：
 * 修改记录:
 */
@Data
public class HarborUser {
       private String email;
       private String realname;
       private String comment;
       private String password;
       private String username;
}
