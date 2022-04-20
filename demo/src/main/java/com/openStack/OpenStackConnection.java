package com.openStack;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.identity.EndpointURLResolver;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.identity.URLResolverParams;

import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.openstack.OSFactory;

public class OpenStackConnection {
    public static void main(String[] args) {
        final EndpointURLResolver endpointUrlResolver = new EndpointURLResolver() {
            @Override
            public String findURLV2(URLResolverParams params) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String findURLV3(URLResolverParams params) {
                switch (params.type.getServiceName()) {
                    case "keystone":
                        return "http://192.168.119.214:5000/v3";
                    case "glance":
                        return "http://192.168.119.214:9292";// 经openstack4j检验用的是v1版本
                    case "nova":
                        return "http://192.168.119.214:8774/v2.1/c818d0930f4a4146ae94427d9335464e";// compute计算相关
                    case "neutron":
                        return "http://192.168.119.214:9696";
                    case "cinder":
                        return "http://192.168.119.214:8776/v1/c818d0930f4a4146ae94427d9335464e";// 经openstack4j检验用的是v1版本
                    default:
                        return null;
                }
            }
        };

        // OSFactory.enableHttpLoggingFilter(true);//
        Identifier domainIdentitier = Identifier.byName("Default");
        try {
            // 不同域内的项目名和用户名有可能相同。
            OSClientV3 os = OSFactory.builderV3()
                    .withConfig(Config.newConfig().withEndpointURLResolver(endpointUrlResolver))
                    .endpoint("http://192.168.119.214:5000/v3")
                    .credentials("admin", "admin_ctsi", domainIdentitier)
                    .scopeToProject(Identifier.byName("admin"), Identifier.byName("Default"))
                    .perspective(Facing.PUBLIC)
                    .authenticate();
            System.out.println("鉴权通过！");
            List<? extends Network> networks = os.networking().network().list();

            for (Network network : networks) {
                System.out.println("网络名称:" + network.getName());
                System.out.println("网络ID:" + network.getId());
                System.out.println("状态:" + network.getStatus());
                for (Subnet subnet : network.getNeutronSubnets()) {
                    System.out.println("子网ID:" + subnet.getId());
                    System.out.println("子网cidr:" + subnet.getCidr());
                }
            }
            List<? extends Server> servers = os.compute().servers().list();
            for (Server server : servers) {
                System.out.println("主机名称:" + server.getName());
                System.out.println("主机ID:" + server.getId());
                System.out.println("主机状态:" + server.getStatus());
                System.out.println("主机网络信息:");
                Map<String, List<? extends Address>> addressesMap = server.getAddresses().getAddresses();
                for (String key : addressesMap.keySet()) {
                    System.out.println("网络:" + key);
                    for (Address address : addressesMap.get(key)) {
                        System.out.println("IP地址 :" + address.getAddr());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("message: " + e.getMessage());
        } finally {
        }
    }
}