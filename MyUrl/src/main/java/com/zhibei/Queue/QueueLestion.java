package com.zhibei.Queue;

import com.zhibei.Jurl.UrlCheck;

import java.util.concurrent.BlockingQueue;

public class QueueLestion implements Runnable {
    private BlockingQueue<String> urlQueue;
    static {
        UrlCheck.check("https://www.baidu.com/file.html?aaa=bbb&ccc=ddd");
    }

    public QueueLestion(BlockingQueue<String> urlQueue) {
        this.urlQueue = urlQueue;
    }

    @Override
    public void run() {
        while (true){
            if (!urlQueue.isEmpty()){
                UrlCheck.check(urlQueue.poll());
            }else {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
