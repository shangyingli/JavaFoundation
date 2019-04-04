package com.example.concurrentbase;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 非公平锁
 */

public class MyFairLock {

    private ReentrantLock lock = new ReentrantLock(false);

    public void testFail(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() +"获得了锁");
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        final MyFairLock fairLock = new MyFairLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "启动");
                fairLock.testFail();
            }
        };
        Thread[] threadArray = new Thread[10];
        for (int i=0; i<10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i=0; i<10; i++) {
            threadArray[i].start();
        }
    }
}
