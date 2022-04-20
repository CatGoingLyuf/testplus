
package com.k8s;

public class RepositoryConstants {

    private RepositoryConstants() {}

    /**
     * 仓库地址
     */
    public static final String REPOSITORY_IPADDR="192.168.211.118:8099";
    /**
     * http header Authorization的前缀
     */
    public static final String HTTP_AUTH_BEARER = "Bearer ";

    /**
     * header中token的key.
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * header中clusterId的key.
     */
    public static final String CLUSTERID = "clusterId";

    /**
     * header中集群的baseUrl (ip:port)
     */
    public static final String BASEURL="baseUrl";

    /**
     * header中集群的请求API的url
     */
    public static final String REQURL = "reqUrl";

    /**
     * 调用api成功时返回值
     */
    public enum SUCCESSCODE {
        CODE(200),CODE2(201);
        private Integer code ;
        private SUCCESSCODE(Integer code)
        {
            this.code = code;
        }
        public Integer getCode() {
            return code;
        }}


    /**
     * 集群启用状态
     */
    public static final  Integer STATUS = 1;

    /**
     * api调用正确时result返回字段
     */
    public static final String SUCCESSRESULTCODE = "0";
    /**
     * 传入url名称
     */
    public static final String REQURLPARAMS = "reqUrl";

    /**
     * harbor调用api的必须前缀
     */
    public static final String APIBASEURL = "/api/v2.0";
}
