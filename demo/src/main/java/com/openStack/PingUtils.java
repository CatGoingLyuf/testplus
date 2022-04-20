package com.openStack;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author lyuf
 * @Date 2022/2/16 17:03
 * @Version 1.0
 */
@Slf4j
public class PingUtils {
    private static PingUtils instance = null;
    public static synchronized PingUtils getInstance() {
        if (instance == null) {
            instance = new PingUtils();
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

    public static void main(String[] args) {
        Boolean aBoolean = PingUtils.getInstance().ipDetection("192.168.155.0", 3000);
        System.out.println(aBoolean);
    }
}
