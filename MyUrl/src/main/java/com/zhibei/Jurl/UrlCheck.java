package com.zhibei.Jurl;

import com.anthonynsimon.url.URL;
import com.zhibei.utils.Log;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class UrlCheck{
    private static int a = 1;

    /**
     * 进行sql解析并异步的进行修改操作
     * isInt:[0-9]+ ,isABC:[A-Za-z]+ ,isUrl:^((http)://)?\\w+\\.\\w+\\.\\w+
     * @param url
     */
    public static void check(String url){
        try {
            URL parse = URL.parse(url);
            //对path进行处理

            String path = parse.getPath();
            if (!Objects.equals(path,null)){
                // 添加到查询的map
            }

            //对fragmetn进行处理
            String fragment = parse.getFragment();
            if (!Objects.equals(fragment,null)) {
                // 添加到查询列表
            }

            //对query进行处理
            Map<String, Collection<String>> queryPairs = parse.getQueryPairs();
            if (!queryPairs.isEmpty()){
                for (Map.Entry<String, Collection<String>> entry:queryPairs.entrySet()){
                    Collection<String> valueCol = entry.getValue();
                    if (!valueCol.isEmpty()&&valueCol.size()==1){
                        String value = valueCol.toString();
                        //TODO 如果为url可以选择进行递归
                        // 添加到查询列表
                    }
                }
            }
        }catch (Exception e){
            Log.error("url校验异常：" + e.getMessage());
            Log.error("校验异常url：" + url);
        }
        aaa();
    }

    private static synchronized void aaa(){
        if (a<50000){
            a++;
        }else {
            Log.debug(System.currentTimeMillis());
            a = 1;
        }

    }



}
