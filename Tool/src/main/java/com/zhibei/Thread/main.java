package com.zhibei.Thread;

public class main {
    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            new MyThread().start();
        }
    }
}
