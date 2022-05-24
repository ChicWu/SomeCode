package Utils;

import java.io.IOException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.*;

public class HttpUtil {

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000).setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000).build();

    public static String post(String uri, Map<String, String> maps) {
//		Logger.d(" Http post :" + uri);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(requestConfig);
        String responseContent = null;
        try {
            // 创建参数队列
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (String key : maps.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }


    public static String get(String uri) {
//		Logger.d(" Http post :" + uri);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(uri);
        String responseContent = null;
        try {

            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

//    public static void main(String[] args) {
//        String str = get("http://192.168.11.136:8090/db/metadata/show?database=NETOA2");
//        JSONObject jsonObject = (JSONObject) JSONObject.parseObject(str, Feature.OrderedField)
//                .getJSONObject("NETOA2")
//                .getJSONObject("EMP");
//        System.out.println(jsonObject.toString());
//        int i = 0;
//        for (Object map : jsonObject.entrySet()) {
//            try {
//                String str1 = ((Map.Entry) map).getValue().toString();
//                System.out.print(JSONObject.parseObject(str1).get("status"));
//                System.out.println(":"+i++);
//            }catch(Exception e){
//            }
//        }
//
//    }
}

