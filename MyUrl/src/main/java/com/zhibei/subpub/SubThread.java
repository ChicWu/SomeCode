package com.zhibei.subpub;

import com.zhibei.utils.Redis;
import com.zhibei.utils.ThreadPool;
import redis.clients.jedis.Jedis;

public class SubThread implements Runnable {
    private final Subscription subscriber = new Subscription();
    private final String channel = "mychannel";

    @Override
    public void run() {
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = Redis.getInstance(); //取出一个连接
            jedis.subscribe(subscriber, channel); //通过subscribe 的api去订阅，入参是订阅者和频道名
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
