package com.example.concurrentbase;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestVolitate {

//    public volatile int inc = 0; //volatile关键字可以保证可见性和禁止指令重排， 但无法保证原子性

//    public int inc = 0;

    public AtomicInteger inc = new AtomicInteger(0);



    Lock lock = new ReentrantLock();

    public  void increase() {
//        lock.lock();
//        try {
//            inc++; //非原子性操作
//        } finally {
//            lock.unlock();
//        }
        inc.getAndIncrement(); //内部使用了cas保证原子性
    }

    public static void main(String[] args) {
//        final TestVolitate test = new TestVolitate();
//        for (int i = 0; i < 10; i++) {
//            new Thread() {
//                public void run() {
//                    for (int j = 0; j < 1000; j++)
//                        test.increase();
//                }
//            }.start();
//        }
//
//        while (Thread.activeCount() > 2)  //保证前面的线程都执行完
//            Thread.yield();//使当前线程变为可运行状态
//        System.out.println(test.inc);

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[]info = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : info) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.
                    getThreadName());
        }

    }
}
