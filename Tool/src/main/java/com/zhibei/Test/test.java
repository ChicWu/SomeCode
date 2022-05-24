package com.zhibei.Test;

public class test {
    public static void main(String[] args) {
        for (int i = 0;i < 300 ; i++){
            long start = System.currentTimeMillis();
//            Thread.sleep();
            long user = System.currentTimeMillis() - start;
        }
    }
}
