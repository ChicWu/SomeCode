package com.chicwu.net;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpsToolTest  {
    private String url = "";
    private String map = "";
    private String charset = "utf-8";

    @Test
    void doPost() {
        System.out.println("发送httpsPost请求结果："+HttpsTool.doPost(url,map,charset));
    }

    @Test
    void doGet() {
        System.out.println("发送httpsGet请求结果："+HttpsTool.doGet(url,charset));
    }
}