package com.zhibei.redisio;

import com.zhibei.utils.Redis;
import redis.clients.jedis.Jedis;

public class SimpleWrite implements Runnable {
    private String url;
    private static Jedis jedis;

    public SimpleWrite(String url) {
        this.url = url;
        jedis = Redis.getInstance();
    }

    @Override
    public void run() {
        jedis.set(url,"");
    }
}
