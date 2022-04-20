package com.openStack;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Author lyuf
 * @Date 2022/2/16 14:30
 * @Version 1.0
 */
@Slf4j
public class Ping {

    //判断ip是否可以连接 timeOut是超时时间
    @Test
    public void test() {
        Integer timeOut = 1000;
        boolean isIpReachable = false;
        while (!isIpReachable) {
            InetAddress address;
            try {
                address = InetAddress.getByName("195.65.31.54");
                isIpReachable = address.isReachable(timeOut);
                System.out.println("Name: " + address.getHostName());
                System.out.println("Addr: " + address.getHostAddress());
                System.out.println("isIpReachable: " + isIpReachable);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("=======================================");
        }
    }

    //判断ip、端口是否可连接
    @Test
    public void isHostConnectable() {
        String host = "192.168.195.1";
        int port = 38025;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("false");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("true");
    }

    /**
     * test network * @param ip
     */
    @Test
    public void getNetworkState() {
        Runtime runtime = Runtime.getRuntime();
        String ip = "192.168.195.6";
        try {
            log.info("=================正在测试网络连通性ip：" + ip);
            Process process = runtime.exec("ping " + ip);
            InputStream iStream = process.getInputStream();
            InputStreamReader iSReader = new InputStreamReader(iStream, "UTF-8");
            BufferedReader bReader = new BufferedReader(iSReader);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = bReader.readLine()) != null) {
                sb.append(line);
            }
            iStream.close();
            iSReader.close();
            bReader.close();
            String result = new String(sb.toString().getBytes("UTF-8"));
            log.info("ping result:" + result);
            if (!StringUtils.isBlank(result)) {
                if (result.indexOf("TTL") > 0 || result.indexOf("ttl") > 0) {
                    log.info("网络正常，时间: " + new Date());
                } else {
                    log.info("网络断开，时间 :" + new Date());
                }
            }
        } catch (Exception e) {
            log.error("网络异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

}
