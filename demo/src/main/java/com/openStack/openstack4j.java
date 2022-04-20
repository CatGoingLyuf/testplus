//package com.openStack;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.openstack4j.api.Builders;
//import org.openstack4j.model.compute.BDMDestType;
//import org.openstack4j.model.compute.BDMSourceType;
//import org.openstack4j.model.compute.ServerCreate;
//import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
//import org.openstack4j.model.compute.builder.ServerCreateBuilder;
//
//
///**
// * @Author lyuf
// * @Date 2021/12/30 11:46
// * @Version 1.0
// */
//@Slf4j
//public class openstack4j {
//
//
//    public void creattest() {
//
//        log.info("创建Centos、Ubuntu系统。。。");
//        BlockDeviceMappingBuilder blockDeviceMappingBuilder = Builders.blockDeviceMapping()
//                .sourceType(BDMSourceType.IMAGE)
//                .uuid(imageId)
//                .volumeSize(instances.getFlavor().getDisk())
//                .destinationType(BDMDestType.VOLUME)
//                .deleteOnTermination(instances.getDel())
//                .bootIndex(0);
//
//        String pass = "#cloud-config\nchpasswd:\n   list: |\n       root:" + instances.getPwd() + "\n   expire: false\nssh_pwauth: true";
//        @SuppressWarnings("restriction")
//        String userData = new sun.misc.BASE64Encoder().encode(pass.getBytes()).replace("\r\n", "");
//        log.info("设置密码" + userData);
//        ServerCreateBuilder scb = Builders
//                .server()
//                .name(instances.getServerName())
//                .flavor(flavorId)
//                .blockDevice(blockDeviceMappingBuilder.build())
//                .keypairName(keypairName)
//                .image(imageId)
//                .userData(userData)
//                .addSecurityGroup(securityGroupName);
//
//        if (StringUtils.isNotEmpty(keypairName)) {
//            scb.keypairName(keypairName);
//        }
//        //经典网络
//        if (null != instances.getClassicNetWork() && Constant.INTEGR_FLAG_1.equals(instances.getClassicNetWork())) {
//            scb.networks(netWorks);
//        } else {
//            //普通VPC
//            scb.addNetworkPort(portId);
//        }
//        ServerCreate sc = scb.build();
//        long currentTimeMillis1 = System.currentTimeMillis();
//        server = os3.compute().servers().boot(sc);
//        long currentTimeMillis2 = System.currentTimeMillis();
//        log.error("centOS类 虚机创建时间:" + (currentTimeMillis2 - currentTimeMillis1));
//    }
//
//// openstack在创建虚拟机的时候 可以选择经典网络或者是VPC   选择经典网络时候  选择的安全组需要传入的参数为安全组名称 ，但是选择vpc的时候 需要传入安全组ID
////
////    scb.addNetworkPort(portId);   普通VPC设置的addSecurityGroup(securityGroupName) 并不会生效  而是需要在申请port的时候 指定使用的安全组ID 。谨记
////————————————————
//
//        private boolean isEnoughClassicPort () {
//
//            List<Networks> queryClassicNetworkList = networkService.queryClassicNetworkList();
//            //IP不足判断	   子网未使用IP拿不到
//            boolean flag = false;
//            OSClientV3 adminOS3 = null;
//            try {
//                adminOS3 = openstack4j.initOSClientByAdmin();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            String tokenId = adminOS3.getToken().getId();
//
//            HttpHeaders headers = new HttpHeaders();
//            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//            headers.setContentType(type);
//            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//            headers.add("X-Auth-Token", tokenId);
//            HttpEntity<String> formEntity = new HttpEntity<String>(null, headers);
//            for (Networks networks : queryClassicNetworkList) {
//                String networksId = networks.getNetworkId();
//                String subnetesId = adminOS3.networking().network().get(networksId).getSubnets().get(0);
//                String url = classicNetworkUrl + networksId;
//
//                ResponseEntity<NetWorkResponse> forEntity = restTemplate.exchange(url, HttpMethod.GET, formEntity, NetWorkResponse.class);
//                if (forEntity.getStatusCode().equals(HttpStatus.OK)) {
//                    NetWorkResponse body = forEntity.getBody();
//                    long total_ips = body.getNetwork_ip_availability().getTotal_ips();
//                    long used_ips = body.getNetwork_ip_availability().getUsed_ips();
//                    if (total_ips - used_ips > 0) {
//                        this.networkId = networksId;
//                        this.subneteId = subnetesId;
//                        flag = true;
//                        return flag;
//                    }
//                }
//
//            }
//            return flag;
//        }
//        //选择经典网络时候 需要对经典网络 IP是否足够做判断  。由于 本项目使用openstack4j 版本的原因  admin的API没有做封装，只能调用原生的restful 接口。
//        // 另外有一点比较坑的地方，在使用admin的api后  之前的普通用户认证会被替代，如果不重新进行认证的话，所建立的资源等将会直接属于admin用户下
//        //————————————————
//
//}
