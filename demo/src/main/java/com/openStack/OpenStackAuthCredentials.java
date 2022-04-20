package com.openStack;

import org.junit.Test;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.api.OSClient.OSClientV3;

/**
 * @Author lyuf
 * @Date 2021/12/23 9:43
 * @Version 1.0
 */
public class OpenStackAuthCredentials {

    String url = "http://192.168.119.214:5000/";

    @Test
    public void test1() {


        // use Identifier.byId("domainId") or Identifier.byName("example-domain")
        Identifier domainIdentifier = Identifier.byName("Default");


        OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.119.214:5000/v3")
                .credentials("admin", "admin_ctsi", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                .authenticate();
        System.out.println("鉴权通过！");


//         unscoped身份验证
//         作为用户名不是唯一的跨域，您需要提供domainIdentifier
        OSClientV3 os1 = OSFactory.builderV3()
                .endpoint("http://192.168.119.214:5000/")
                .credentials("admin", "sample", domainIdentifier)
                .authenticate();

        // 项目范围的认证
        OSClientV3 os2 = OSFactory.builderV3()
                .endpoint(url)
                .credentials("admin", "admin_ctsi", domainIdentifier)
                .scopeToProject(Identifier.byId("c818d0930f4a4146ae94427d9335464e"))
                .authenticate();
        System.out.println("鉴权通过！");


        // 域作用域身份验证
        // 使用唯一userId不需要domainIdentifier
        OSClientV3 os3 = OSFactory.builderV3()
                .endpoint("http://127.0.0.1:5000/v3")
                .credentials("userId", "secret")
                .scopeToDomain(domainIdentifier)
                .authenticate();


        // 仅通过名称限定项目范围是不可能的，因为项目名称在域内是唯一的。
        // 你可以使用这个作为项目的id是唯一的跨域
        OSClientV3 os4 = OSFactory.builderV3()
                .endpoint("http://127.0.0.1:5000/v3")
                .credentials("userId", "secret")
                .scopeToProject(Identifier.byName("projectName"), Identifier.byName("domainName"))
                .authenticate();

        // 或者
        OSClientV3 os5 = OSFactory.builderV3()
                .endpoint("http://127.0.0.1:5000/v3")
                .credentials("userId", "secret")
                .scopeToDomain(Identifier.byName("domainName"))
                .authenticate();
    }
}
