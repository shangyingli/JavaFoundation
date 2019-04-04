package com.example.concurrentbase;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 乐观锁 AtomicInteger
 * 底层采用cas无锁机制
 * 与期望值(内存中的version)不同时会重试
 */

public class AtoIntegerDemo {


    public static void main(String[] args) {
        new AtoIntegerDemo();
    }

    public AtoIntegerDemo() {
        for (int i = 0; i < 1000; i++) {
            new TaskThread().start();
        }
    }

}

class TaskThread extends Thread {

    private volatile static int count = 0; //类变量，所有线程共享
//    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread : " + Thread.currentThread().getName() + "count is : " + getCount());

    }

    private static  int getCount() {
        return count++; //非原子操作， 使用volatile无法避免线程安全问题
    }
}
