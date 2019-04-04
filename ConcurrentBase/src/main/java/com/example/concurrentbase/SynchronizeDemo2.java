package com.example.concurrentbase;

/**
 * synchronize为重量级锁，具备可重入性
 */

public class SynchronizeDemo2 {

    public static void main(String[] args) {
        new SynchronizeDemo2();
    }

    public SynchronizeDemo2() {

        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
    }

    class Task1 implements Runnable {
        @Override
        public void run() {
            get();
        }
    }

    class Task2 implements Runnable {
        @Override
        public void run() {
            get();
        }
    }

    public synchronized void set() {
        System.out.println("set方法, 当前线程为 : " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void get() {
        System.out.println("get方法, 当前线程为 : " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        set();
    }

}
