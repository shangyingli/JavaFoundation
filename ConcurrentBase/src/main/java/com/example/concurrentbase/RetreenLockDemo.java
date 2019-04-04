package com.example.concurrentbase;

import java.util.concurrent.locks.ReentrantLock;

/**
 *ReentrantLock,和synchronize一样为可重入锁
 */
public class RetreenLockDemo {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new RetreenLockDemo();
    }

    public RetreenLockDemo() {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
//        t2.start();
    }

    class Task1 implements Runnable {
        @Override
        public void run() {
            set();
        }
    }

    class Task2 implements Runnable {
        @Override
        public void run() {
            get();
        }
    }

    public  synchronized void set() {
        try {
            Thread.sleep(3000);
            lock.lock();
            System.out.println("set方法, 当前线程为 : " + Thread.currentThread().getName());
            get();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            Thread.sleep(3000);
            synchronized (this) {
                System.out.println("get方法, 当前线程为 : " + Thread.currentThread().getName());
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
