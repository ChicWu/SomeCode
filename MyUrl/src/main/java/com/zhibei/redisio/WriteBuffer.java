package com.zhibei.redisio;

import com.zhibei.utils.Log;
import com.zhibei.utils.Redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import java.util.Map;
import java.util.Set;

public class WriteBuffer implements Runnable {
    private Set<String> set;
    private static Jedis jedis;

    static {
        jedis = Redis.getInstance();
    }

    public WriteBuffer(Set<String> set) {
        this.set = set;
    }

    @Override
    public void run() {
        Pipeline p = jedis.pipelined();
        for (String url:set) {
            p.set(url,"");
        }
        p.sync();
        long user = System.currentTimeMillis() - Log.start;
        Log.debug("write redis used time ["+user+"]");
    }
}
