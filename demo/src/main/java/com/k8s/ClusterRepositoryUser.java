package com.k8s;
import lombok.Data;

@Data
public class ClusterRepositoryUser {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 仓库名称
     */
    private String repositoryName;
    /**
     * harbor仓库的地址
     */
    private Long repositoryId;
}
