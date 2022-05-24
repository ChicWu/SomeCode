package com.chicwu.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class HttpsTool {
    /**
     * 关于java发生https请求的工具类
     * */
    public static void main(String[] args) {

    }

    /**
     * 发送https Post请求
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public static String doPost(String url, String map, String charset) {
        org.apache.http.client.HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = SSLClient.sslClient();
            httpPost = new HttpPost(url);
            //设置参数
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity stringEntity = new StringEntity(map);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 发送https Get请求
     * @param url
     * @param charset
     * @return
     */
    public static String doGet(String url, String charset){
        org.apache.http.client.HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = SSLClient.sslClient();
            httpGet = new HttpGet(url);
            //设置参数
            httpGet.addHeader("Accept", "application/json");
            httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
