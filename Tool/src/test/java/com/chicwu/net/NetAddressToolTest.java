package com.chicwu.net;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetAddressToolTest {

    @Test
    void getIpAddress() {
        System.out.println("本机IP地址为： "+NetAddressTool.getIpAddress());
    }

    @Test
    void getMacAddress() {
        System.out.println("本机Mac地址为: "+NetAddressTool.getMacAddress());
    }
}