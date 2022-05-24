package com.zhibei.main;

import com.zhibei.subpub.Publish;
import com.zhibei.subpub.SubThread;
import com.zhibei.utils.ThreadPool;

public class Main0 {
    public static void main(String[] args) {
//        ThreadPool.submit(new SubThread());
        ThreadPool.submit(new Publish());
    }
}
