package com.zhibei.subpub;

import com.zhibei.utils.Log;
import com.zhibei.utils.Redis;
import redis.clients.jedis.Pipeline;


public class Publish implements Runnable{

    @Override
    public void run() {
        Pipeline pipelined = Redis.getInstance().pipelined();
        Log.debug("发布消息开始 time: ["+System.currentTimeMillis()+"]");
        for (int i = 0; i < 1000000; i++){
            pipelined.publish("mychannel", "https://www.baidu.com/file.html?aaa=bbb&ccc=ddd");
        }
        Log.debug("发布消息完成 time: ["+System.currentTimeMillis()+"]");
        pipelined.sync();
    }

}
