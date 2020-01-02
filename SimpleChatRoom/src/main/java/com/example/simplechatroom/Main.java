package com.example.simplechatroom;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * author: xpf
 * date: 2020/1/2 20:31
 * description: 工具类
 */
public class Main {
    public static void main(String[] args){
        System.out.println("ip: " + getLocalIpAddress());
    }


    public static String getLocalIpAddress() {
        Enumeration<NetworkInterface> networks = null;
        try {
            // 获取网卡设备
            networks = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        Enumeration<InetAddress> addrs;
        // 遍历网卡设备
        while (networks.hasMoreElements()) {
            addrs = networks.nextElement().getInetAddresses();
            while (addrs.hasMoreElements()) {
                ip = addrs.nextElement();
                if (ip != null && ip instanceof InetAddress && ip.isSiteLocalAddress()) {
                    if (ip.getHostAddress() == null || "".equals(ip.getHostAddress())) {
                        System.out.println("获取到的客户端内网ip为空，从配置文件读取本地ip。");
                        return null;
                    }
                    return ip.getHostAddress();// 客户端ip
                }
            }
        }
        return null;
    }
}
