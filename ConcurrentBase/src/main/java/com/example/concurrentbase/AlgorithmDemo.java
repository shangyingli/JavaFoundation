package com.example.concurrentbase;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印0~100的奇数偶数
 * 使用wait和notify
 */

public class AlgorithmDemo {

    private int num = 100;
    private int i = 0;
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new AlgorithmDemo();
    }

    public AlgorithmDemo() {
        OddThread oddThread = new OddThread(num, "奇数线程");
        EvenThread evenThread = new EvenThread(num, "偶数线程");
        oddThread.start();
        evenThread.start();
    }

    class OddThread extends Thread {

        private int num;

        public OddThread(int num, String name) {
            super(name);
            this.num = num;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (i <= num) {
                    if ((i & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + i++);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }

    class EvenThread extends Thread {

        private int num;

        public EvenThread(int num, String name) {
            super(name);
            this.num = num;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (i <= num) {
                    if ((i & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + i++);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

}
