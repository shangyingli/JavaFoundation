package com.example.concurrentbase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *信号量,控制并发个数
 */

public class SephoreDemo {

    private static final int THREAD_NUM = 30;
    ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
    private Semaphore semaphore = new Semaphore(10); //控制并发数为10

    public static void main(String[] args) {
        new SephoreDemo();
    }

    public SephoreDemo() {
        for (int i = 0; i < THREAD_NUM; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("current thread : " + Thread.currentThread().getName() + "write data");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release(); //释放了之后， 其他线程才能进来
                }
            });
        }
    }
}
