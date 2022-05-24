package com.zhibei.subpub;

import com.zhibei.Queue.UrlQueueList;
import com.zhibei.Queue.QueueLestion;
import com.zhibei.utils.Log;
import com.zhibei.utils.ThreadPool;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;


public class Subscription extends JedisPubSub {
    private int a = 0;
    private static List<LinkedBlockingDeque<String>> urlQueueList = new ArrayList<LinkedBlockingDeque<String>>();
//    private  static LinkedBlockingDeque<String> UrlQueue = new LinkedBlockingDeque<>();
    static {
        for (int i = 0;i<20;i++){
            urlQueueList.add(new LinkedBlockingDeque<String>());
            ThreadPool.submit(new QueueLestion(urlQueueList.get(i)));
        }
    }
    @Override
    public void onMessage(String channel, final String message) {       //收到消息会调用
        int b = a%20;
        urlQueueList.get(b).offer(message);
        if (a >= 19) {
            a = 0;
        } else {
            a++;
        }
//        ThreadPool.submit(new Runnable() {
//            @Override
//            public void run() {
//                UrlCheck.check(message);
//            }
//        });

    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用

        Log.info(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
        Log.info(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }
}
