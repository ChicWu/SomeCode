package com.zhibei.Thread;

public class MyThread extends Thread {
    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            new MyThread().start();
        }
    }
    public void run() {
        Thread currentThread = Thread.currentThread();
        System.out.println("sql getCache currentThread id: ["+currentThread.getId()+"]");
    }
}
