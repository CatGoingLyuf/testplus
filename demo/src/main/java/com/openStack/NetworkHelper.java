package com.openStack;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试网络连通性
 *
 */
public class NetworkHelper {
    private static Logger log = LoggerFactory.getLogger(NetworkHelper.class);
    private static NetworkHelper instance = null;

    public static synchronized NetworkHelper getInstance() {
        if (instance == null) {
            instance = new NetworkHelper();
        }
        return instance;
    }

    /**
     * 测试本地能否ping ip
     * @param ip
     * @return
     */
    public boolean isReachIp(String ip) {
        boolean isReach = false;
        try {
            InetAddress address = InetAddress.getByName(ip);// ping this IP
            if (address instanceof java.net.Inet4Address) {
                log.info(ip + " is ipv4 address");
            } else if (address instanceof java.net.Inet6Address) {
                log.info(ip + " is ipv6 address");
            } else {
                log.info(ip + " is unrecongized");
            }
            if (address.isReachable(5000)) {
                isReach = true;
                log.info("SUCCESS - ping " + ip  + " with no interface specified");
            } else {
                isReach = false;
                log.info("FAILURE - ping " + ip  + " with no interface specified");
            }
        } catch (Exception e) {
            log.error("error occurs:" + e.getMessage());
        }
        return isReach;
    }

    /**
     *  测试本地能否ping ip：port
     *
     */
    public Boolean ipDetection(String ip,int port,Integer timeout){
        boolean isReach = false;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(InetAddress.getByName(ip), port), timeout);
            isReach = true;
            log.info("SUCCESS - ping " + ip  + ":" + port );
        } catch (IOException e) {
            log.info("FAILURE - ping " + ip  + ":" + port);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isReach;
    }

    /**
     * @param ipAddress 待检测IP地址
     * @param timeout   检测超时时间（超时应该在3钞以上）
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
     * 测试本地所有的网卡地址都能ping通 ip
     * @param ip
     * @return
     */
    public boolean isReachNetworkInterfaces(String ip) {
        boolean isReach = false;
        try {
            InetAddress address = InetAddress.getByName(ip);// ping this IP
            if (address instanceof java.net.Inet4Address) {
                log.info(ip + " is ipv4 address");
            } else if (address instanceof java.net.Inet6Address) {
                log.info(ip + " is ipv6 address");
            } else {
                log.info(ip + " is unrecongized");
            }
            if (address.isReachable(5000)) {
                isReach = true;
                log.info("SUCCESS - ping " + ip + " with no interface specified");
            } else {
                isReach = false;
                log.info("FAILURE - ping " + ip + " with no interface specified");
            }
            if (isReach) {
                log.info("-------Trying different interfaces--------");

                /**
                 * NetworkInterface.getNetworkInterfaces()
                 * 返回此机器上的所有接口。 {@code Enumeration}包含至少一个元素，可能表示一个环回接口，该接口只支持实体之间的通信
                 * 注意:可以使用getNetworkInterfaces()+ getInetaddresses()获取该节点的所有IP地址
                 */
                Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = netInterfaces.nextElement();
                    log.info("Checking interface, DisplayName:" + ni.getDisplayName() + ", Name:" + ni.getName());
                    if (address.isReachable(ni, 0, 5000)) {
                        isReach = true;
                        log.info("SUCCESS - ping " + ip);
                    } else {
                        isReach = false;
                        log.info("FAILURE - ping " + ip);
                    }
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        log.info("IP: " + ips.nextElement().getHostAddress());
                    }
                    log.info("-----------------check now NetworkInterface is done--------------------------");
                }
            }
        } catch (Exception e) {
            log.error("error occurs:" + e.getMessage());
        }
        return isReach;
    }

    /**
     * 获取能与远程主机指定端口建立连接的本机ip地址
     * @param remoteAddr
     * @param port
     * @return
     */
    public String getReachableIP(InetAddress remoteAddr, int port) {
        String retIP = null;
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
                while (localAddrs.hasMoreElements()) {
                    InetAddress localAddr = localAddrs.nextElement();
                    if (isReachable(localAddr, remoteAddr, port, 5000)) {
                        retIP = localAddr.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            log.error("Error occurred while listing all the local network addresses:" + e.getMessage());
        }
        if (retIP == null) {
            log.info("NULL reachable local IP is found!");
        } else {
            log.info("Reachable local IP is found, it is " + retIP);
        }
        return retIP;
    }



    /**
     * 获取能与远程主机指定端口建立连接的本机ip地址
     * @param remoteIp
     * @param port
     * @return
     */
    public String getReachableIP(String remoteIp, int port) {
        String retIP = null;
        InetAddress remoteAddr = null;
        Enumeration<NetworkInterface> netInterfaces;
        try {
            remoteAddr = InetAddress.getByName(remoteIp);
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
                while (localAddrs.hasMoreElements()) {
                    InetAddress localAddr = localAddrs.nextElement();
                    if (isReachable(localAddr, remoteAddr, port, 5000)) {
                        retIP = localAddr.getHostAddress();
                        break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            log.error("Error occurred while listing all the local network addresses:" + e.getMessage());
        } catch (SocketException e) {
            log.error("Error occurred while listing all the local network addresses:" + e.getMessage());
        }
        if (retIP == null) {
            log.info("NULL reachable local IP is found!");
        } else {
            log.info("Reachable local IP is found, it is " + retIP);
        }
        return retIP;
    }

    /**
     * 测试localInetAddr能否与远程的主机指定端口建立连接相连
     * @param localInetAddr
     * @param remoteInetAddr
     * @param port
     * @param timeout
     * @return
     */
    public boolean isReachable(InetAddress localInetAddr, InetAddress remoteInetAddr, int port, int timeout) {
        boolean isReachable = false;
        Socket socket = null;
        try {
            socket = new Socket();            // 端口号设置为 0 表示在本地挑选一个可用端口进行连接
            SocketAddress localSocketAddr = new InetSocketAddress(localInetAddr, 0);
            socket.bind(localSocketAddr);
            InetSocketAddress endpointSocketAddr = new InetSocketAddress(remoteInetAddr, port);
            socket.connect(endpointSocketAddr, timeout);
            log.info("SUCCESS - connection established! Local: " + localInetAddr.getHostAddress() + " remote: " + remoteInetAddr.getHostAddress() + " port" + port);
            isReachable = true;
        } catch (IOException e) {
            log.error("FAILRE - CAN not connect! Local: " + localInetAddr.getHostAddress() + " remote: " + remoteInetAddr.getHostAddress() + " port" + port);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error("Error occurred while closing socket:" + e.getMessage());
                }
            }
        }
        return isReachable;
    }

    /**
     * 测试localIp能否与远程的主机指定端口建立连接相连
     * @param localIp
     * @param remoteIp
     * @param port
     * @param timeout
     * @return
     */
    public boolean isReachable(String localIp, String remoteIp, int port, int timeout) {
        boolean isReachable = false;
        Socket socket = null;
        InetAddress localInetAddr = null;
        InetAddress remoteInetAddr = null;
        try {
            localInetAddr = InetAddress.getByName(localIp);
            remoteInetAddr = InetAddress.getByName(remoteIp);
            socket = new Socket();            // 端口号设置为 0 表示在本地挑选一个可用端口进行连接
            SocketAddress localSocketAddr = new InetSocketAddress(localInetAddr, 0);
            socket.bind(localSocketAddr);
            InetSocketAddress endpointSocketAddr = new InetSocketAddress(remoteInetAddr, port);
            socket.connect(endpointSocketAddr, timeout);
            log.info("SUCCESS - connection established! Local: " + localInetAddr.getHostAddress() + " remote: " + remoteInetAddr.getHostAddress() + " port" + port);
            isReachable = true;
        } catch (IOException e) {
            log.error("FAILRE - CAN not connect! Local: " + localInetAddr.getHostAddress() + " remote: " + remoteInetAddr.getHostAddress() + " port" + port);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error("Error occurred while closing socket:" + e.getMessage());
                }
            }
        }
        return isReachable;
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

//        if (NetworkHelper.getInstance().isReachIp("192.168.155.28")) {
//            log.info("=======本机可以ping通ip：" + "192.168.155.51");
//        } else {
//            log.info("=======本机ping不通ip：" + "192.168.155.51");
//        }
//        if (NetworkHelper.getInstance().isReachNetworkInterfaces("www.baidu.com")) {
//            log.info("=======本机所有网卡可以ping通ip：" + "192.168.126.128");
//        } else {
//            log.info("=======本机所有网卡ping不通ip：" + "192.168.126.128");
//        }
//        String localIp = NetworkHelper.getInstance().getReachableIP("192.168.155.28", 22);
//        if (!StringUtils.isBlank(localIp)) {
//            log.info("=======本机可以与ip：" + "192.168.155.28:22"  + "建立连接的IP：" + localIp);
//        } else {
//            log.info("=======本机不能与ip：" + "192.168.155.28:22"  + "建立连接的IP");
//        }

//        try {
//            InetAddress address = InetAddress.getByName("192.168.154.14");
//            String localIp2 = NetworkHelper.getInstance().getReachableIP(address, 8500);
//            if (!StringUtils.isBlank(localIp2)) {
//                log.info("=======本机可以与ip：" + "192.168.154.14:8500"  + "建立连接的IP：" + localIp2);
//            } else {
//                log.info("=======本机不能与ip：" + "192.168.154.14:8500"  + "建立连接的IP");
//            }
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

    }
}