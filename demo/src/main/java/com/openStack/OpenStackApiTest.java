package com.openStack;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openstack4j.api.Apis;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.*;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.ext.*;
import org.openstack4j.model.identity.v3.Domain;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.model.image.Image;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.compute.domain.NovaFlavor;
import org.openstack4j.openstack.compute.domain.NovaQuotaSetUpdate;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lyuf
 * @Date 2021/12/24 11:08
 * @Version 1.0
 */
@Slf4j
public class OpenStackApiTest {

//    private String url = "http://192.168.119.214:5000/v3";
    private String url = "http://192.168.119.100:5000/v3";
    private String userName = "admin";
    private String password = "admin_ctsi";


    // openStack4j 权限认证
    public OSClientV3 credentials(String username, String password, String url) {
        Identifier domainIdentifier = Identifier.byName("Default");
        OSClientV3 os = null;
        try {
            // 不同域内的项目名和用户名有可能相同
            os = OSFactory.builderV3()
                    .endpoint(url) //(openstack url)
                    .credentials(username, password, domainIdentifier) //(userName,password,domainIdentifier)
                    .scopeToProject(Identifier.byName("admin"), Identifier.byName("default")) // (userName,domainName)
                    .authenticate();
            System.out.println("鉴权通过！");
        } catch (Exception e) {
            log.error("鉴权失败:" + e.getMessage());
        }
        return os;
    }

    private void module() {

        OSClientV3 os = this.credentials(userName, password, url);
        // Find all Users
        List<? extends User> users = os.identity().users().list();
        // List all Tenants
        List<? extends Project> tenants = os.identity().projects().list();
        // Find all Compute Flavors
//        List<? extends Flavor> flavors = os.compute().flavors().list();
        // Find all running Servers
        List<? extends Server> servers = os.compute().servers().list();
        // Suspend a Server
        os.compute().servers().action("serverId", Action.SUSPEND);
        // List all Networks
        List<? extends Network> networks = os.networking().network().list();
        // List all Subnets
        List<? extends Subnet> subnets = os.networking().subnet().list();
        // List all Routers
        List<? extends Router> routers = os.networking().router().list();
        // List all Images (Glance)
        List<? extends Image> images = os.images().list();
        // Download the Image Data
        InputStream is = os.images().getAsStream("imageId");
    }

    @Test
    public void getNetWork() {
        //认证
        OSClientV3 os = this.credentials(userName, password, url);
//        //获取网络list
//        List<? extends Network> list = os.networking().network().list();
//        Network physnet1 = os.networking().network().get("awifi");
//        networks.forEach(network -> {
//            System.out.println("网络名称:" + network.getName());
//            System.out.println("网络ID:" + network.getId());
//            System.out.println("状态:" + network.getStatus());
//            for (Subnet subnet : network.getNeutronSubnets()) {
//                System.out.println("子网ID:" + subnet.getId());
//                System.out.println("子网cidr:" + subnet.getCidr());
//            }
//        });
//        List<Network> networks = new ArrayList<>();
//
//        list.forEach(network -> {
//            networks.add(network);
//        });
//
//        networks.forEach(network -> {
//            System.out.println(network.toString());
//        });

        ArrayList<Server> servers = new ArrayList<>();

        os.compute().servers().list().forEach(server -> {
            System.out.println(server.getStatus());
//            if (server.getStatus().equals(Server.Status.ACTIVE)) {
//                servers.add(server);
//            }
        });

    }

    @Test
    public void getServers() {
        //认证
        OSClientV3 os = this.credentials(userName, password, url);
        //获取服务list
        List<? extends Server> servers = os.compute().servers().list();
        servers.forEach(server -> {
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
        });
    }

    @Test
    public void getFlavors() {
        //认证
        OSClientV3 os = this.credentials(userName, password, url);

        Map<String, Flavor> filteringParams = new HashMap<String, Flavor>();

//        List<? extends Flavor> list = os.compute().flavors().list();

        // create(String name, int ram, int vcpus, int disk, int ephemeral, int swap, float rxtxFactor, boolean isPublic)
//        Flavor testFlavor1 = Builders.flavor().name("testFlavor").ram(1024).disk(10).vcpus(1).isPublic(true).ephemeral(0).rxtxFactor(1).swap(0).build();
//        Flavor flavor = os.compute().flavors().create(testFlavor1);
        HashMap<String, String> map = new HashMap<>();

//        Lists.reverse()


        // list
//        List<? extends Flavor> list = os.compute().flavors().list();
//        Flavor testFlavor = list.stream().filter(flavor -> flavor.getName().equals("testFlavor")).collect(Collectors.toList()).get(0);
        // delete
//        ActionResponse delete = os.compute().flavors().delete("75fcc94b-bae0-4e05-8005-992f97902c77");
        // creat
//        Flavor testFlavor1 = os.compute().flavors().create("testFlavor", 1024, 1, 10, 0, 0, 1, true);

//        try {
//            Flavor flavor = os.compute().flavors().create(testFlavor1);
//        } catch (Exception e) {
//            System.out.println(e);
//        }


//        list.forEach(flavor -> {
//            String ram = String.valueOf(flavor.getRam());
//            String disk = String.valueOf(flavor.getDisk());
//            String vcpus = String.valueOf(flavor.getVcpus());
////            String a = ram + disk + vcpus;
////            Integer b = flavor.getRam() + flavor.getDisk() + flavor.getVcpus();
////            System.out.println(a + ":" + b);
//            filteringParams.put(ram + disk + vcpus, flavor);
//        });

    }

    @Test
    public void getHost() {
        //认证
        OSClientV3 os = this.credentials(userName, password, url);


//        org.openstack4j.model.compute.Image image = os.compute().images().get("42ba0c93-1866-474c-b73b-78dc9c6c2b3a");
//        List<? extends Image> list = os.images().list();
//        List<? extends org.openstack4j.model.compute.Image> list1 = os.compute().images().list();
//        String name = image.getName();
//        System.out.println(name);
//        System.out.println(list);
//        System.out.println(list1);
        //Extensions 扩展功能 hypervisor 虚拟机管理器 migrations 数据库迁移
        List<? extends HostResource> control01 = os.compute().host().hostDescribe("control01");
//        List<? extends FloatingIP> floatingIPS = os.compute().floatingIps().list();
//        List<? extends Keypair> keypairs = os.compute().keypairs().list();
//        List<? extends HostAggregate> list = os.compute().hostAggregates().list();
        AbsoluteLimit quotaSource = os.compute().quotaSets().limits().getAbsolute();
//        List<? extends RateLimit> rate = os.compute().quotaSets().limits().getRate();
//        List<? extends SimpleTenantUsage> simpleTenantUsages = os.compute().quotaSets().listTenantUsages();
//        List<? extends SecGroupExtension> secGroupExtensions = os.compute().securityGroups().list();
//        List<? extends ServerGroup> serverGroups = os.compute().serverGroups().list();
//        List<? extends AvailabilityZone> zones = os.compute().zones().list();
        QuotaSet c5acc88b32f04504bc89ca832eafacbe = os.compute().quotaSets().get("c5acc88b32f04504bc89ca832eafacbe");
        QuotaSetUpdate quotaSetUpdate;

        //     Integer metadataItems;
        //     Integer injectedFileContentBytes;
        //     Integer injectedFiles;
        //     Integer ram;
        //     Integer floatingIps;
        //     Integer instances;
        //     Integer cores;
        //     Integer securityGroups;
        //     Integer securityGroupRules;
        //     Integer injectedFilePathBytes;
        //     Integer keyPairs;
        NovaQuotaSetUpdate.NovaQuotaSetUpdateTenant novaQuotaSetUpdateTenant =
                new NovaQuotaSetUpdate.NovaQuotaSetUpdateTenant(null,null,null,null,null,null,40,null,null,null,null);
        QuotaSet quotaSet = os.compute().quotaSets().updateForTenant("c5acc88b32f04504bc89ca832eafacbe", novaQuotaSetUpdateTenant);

//        Long freeCup;
//        Double freeRam;
//        Double freeDisk;
//        Long freeIPs;
//        Long freeVMs;
//        List<? extends Server> vmList = os.compute().servers().list();
//        List<? extends Hypervisor> hypervisors = os.compute().hypervisors().list();
//        List<Integer> freeDiskList = hypervisors.stream().map(Hypervisor::getFreeDisk).collect(Collectors.toList());
//        freeCup = (long) (quotaSource.getMaxTotalCores() - quotaSource.getTotalCoresUsed());
//        freeRam = (double) (quotaSource.getMaxTotalRAMSize() - quotaSource.getTotalRAMUsed() - (hypervisors.size() * 512)) / 1024;
//        freeVMs = (long) (quotaSource.getMaxTotalInstances() - quotaSource.getTotalInstancesUsed());
//        freeDisk = (double) (hypervisors.stream().collect(Collectors.summingInt(Hypervisor::getFreeDisk)));
//        freeIPs = (long) (254 - vmList.size());

        control01.forEach(hostResource -> {
            System.out.println("cpu:" + hostResource.getCpu());
            System.out.println("hostname:" + hostResource.getHost());
            System.out.println("memory:" + hostResource.getMemoryInMb());
            System.out.println("project:" + hostResource.getProject());
            System.out.println("disk:" + hostResource.getDiskInGb());

            System.out.println("------------------------------------------------------");
        });
    }

    @Test
    public void getIdentify() {
        OSClientV3 os = this.credentials(userName, password, url);

        String id = "8a07bc11-1dae-42cc-aad4-2b47001d3af2";

        ServerPassword password = os.compute().servers().getPassword(id);
        System.out.println(password.getPassword());


        System.out.println("aaaa");
        System.out.println("aaaa");



//        Project project = os.identity().projects().create("default", "lyuf", "test", true);//String domainId, String name, String description, boolean enabled
//        System.out.println(project);
//        List<? extends Project> list = os.identity().projects().list();
//        list.forEach(project1 -> {
//            System.out.println(project1.getId() + "||" + project1.getName() + "||" + project1.getDescription() + "||" + project1.getDomain() + "||" + project1.getParents());
//        });
//
//        List<? extends User> list1 = os.identity().users().list();
//        System.out.println("userList:" + list1);
//
//        String id = os.getToken().getId();
//        Domain domain = os.getToken().getDomain();
//        System.out.println("tokenId:" + id);
//        System.out.println("tokenDomain:" + domain);
//
//        List<? extends Service> list2 = os.compute().services().list();
//        list2.forEach(service -> {
//            System.out.println("serviceId:" + service.getId());
//        });
    }

    private static NetworkHelper instance = null;
    public static synchronized NetworkHelper getInstance() {
        if (instance == null) {
            instance = new NetworkHelper();
        }
        return instance;
    }


    /**
     * @param ipAddress 待检测IP地址
     * @param timeout   检测超时时间
     * @return
     */
    public Boolean ipDetection(String ipAddress, Integer timeout) {
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status = false;
        try {
            status = InetAddress.getByName(ipAddress).isReachable(timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 通过socket检测ip:port是否能够通信
     *
     * @param ipAddress
     * @param port
     * @param timeout
     * @return
     */
    public Boolean ipDetection(String ipAddress, Integer port, Integer timeout) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(InetAddress.getByName(ipAddress), port), timeout);
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    /**
     * @param ipAddress  待检测IP地址
     * @param port       待检测port
     * @param retryCount 重试次数
     * @param timeout    检测超时时间（超时应该在3钞以上）
     * @param detectionFlag   标志位 0检测IP  1检测IP:PORT
     * @return
     */
    public Boolean retryIPDetection(String ipAddress, Integer port, Integer retryCount, Integer timeout, Integer detectionFlag) {
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status = false;
        Integer tryCount = 1;

        //重试机制
        while (tryCount <= retryCount && status == false) {
            if (detectionFlag.equals(0)) {
                status = ipDetection(ipAddress, timeout);
            } else {
                status = ipDetection(ipAddress, port, timeout);
            }
            if (status == false) {
                log.info("第[" + tryCount + "]次连接 " + ipAddress + ":" + port + " 失败！");
                tryCount++;
                continue;
            }
            log.info("连接 " + ipAddress + ":" + port + " 成功！");
            return true;
        }
        return false;
    }
    @Test
    public void creatVM() {
//        认证
        OSClientV3 os = this.credentials(userName, password, url);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("name","new-server-test");
//        List<? extends Server> list = os.compute().servers().list(map);
//        Integer i = Math.toIntExact(System.currentTimeMillis() / 1000);


//        String userData = new sun.misc.BASE64Encoder().encode(timestamp);
//        System.out.println(userData);


//        String id = os.getToken().getProject().getId();
//        List<? extends Project> admin = os.identity().projects().getByName("admin");
//        System.out.println(id);

        String password2 = "123456";
        String pass = "#cloud-config\nchpasswd:\n   list: |\n       root:" + password2 + "\n   expire: false\nssh_pwauth: true";
        String userData = new sun.misc.BASE64Encoder().encode(pass.getBytes()).replace("\r\n", "");
        System.out.println(userData);
        ServerCreate sc = Builders.server().name("testttt").flavor("0efa8a97-2f1d-4929-98bb-ed4dede71966")
                .networks(Arrays.asList("c94ca912-25f2-4139-b3e5-d910007c0b35"))
                .image("05291cb9-2e9e-4e8f-a495-3c3feaf011cc")
                .addAdminPass("123456")
                .build();//主要去掉networks,不然会绑定两个IP上去


//        String pass = "#cloud-config\nchpasswd:\n   list: |\n       root:" + "123456" +"\n   expire: false\nssh_pwauth: true";
//
//        String userData = new sun.misc.BASE64Encoder().encode(pass.getBytes()).replace("\r\n", "");
//        System.out.println("设置密码" + userData);
//        Map<String, String> map = new HashMap<>();
//        map.put("instance_count","2");
//        map.put("name","aaoaa");


//        ServerCreate sc = Builders.server()
//                .flavor("111e91c6-a237-4b8c-9439-86563830d0f0")
//                .networks(Arrays.asList("c94ca912-25f2-4139-b3e5-d910007c0b35"))
//                .image("05291cb9-2e9e-4e8f-a495-3c3feaf011cc")
//                .userData(userData)
//                .build();//主要去掉networks,不然会绑定两个IP上去
//        Server boot = os.compute().servers().boot(sc);
//        String id = boot.getId();
//        System.out.println("创建vmid：" + id);
//
//
//        Network network = os.networking().network().get("c94ca912-25f2-4139-b3e5-d910007c0b35");
//        String name = network.getName();
//
//        System.out.println("创建网络name：" + name);
//        String addr = null;
//
//        for (int i = 0; i < 100; i++) {
//            Server server = os.compute().servers().get(id);
//            if (server.getAddresses().getAddresses().size() != 0) {
//                addr = server.getAddresses().getAddresses().get(name).get(0).getAddr();
//                break;
//            }
//        }
//        System.out.println(addr + ":" + "123456");
//
//        Boolean aBoolean = retryIPDetection(addr, null, 100, 1000, 0);
//        System.out.println("测试联通性：" + aBoolean);
    }

    @Test
    public void tess() {
        int num = 6;
        int a = 10;
        int b = 10;
        for(int j = 2; j< num; j++) {
            a = a * b;
            System.out.println(a);
        }
        String s = String.valueOf(((Math.random() * 9 + 1) * 100000));
        String substring = s.substring(0, num);
        System.out.println(substring);
    }
}
