package com.zhibei.Jurl;

import com.zhibei.utils.Log;
import redis.clients.jedis.Response;
import java.util.Map;

public class JurlExtends implements Runnable{
    private Map<String, Response<String>> map;
    public JurlExtends(Map<String, Response<String>> map) {
        this.map = map;
    }

    @Override
    public void run() {
        if (map.isEmpty()){
            return;
        }
        //TODO 进行url校验
        int sum = 0;
        for (Map.Entry<String, Response<String>> entry :map.entrySet()){
            String url = entry.getValue().get();
            UrlCheck.check(url);
            sum++;
        }
        long used = System.currentTimeMillis()- Log.start;
        Log.debug("Check ["+sum+"] url used time ["+used+"]");
    }
}
