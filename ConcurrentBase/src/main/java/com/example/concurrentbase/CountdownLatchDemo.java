package com.example.concurrentbase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountdownLatchDemo {

    private CountDownLatch countDownLatch;

    public static void main(String[] args) {
        new CountdownLatchDemo();
    }

    public CountdownLatchDemo() {
        countDownLatch = new CountDownLatch(2);
        Step1Thread thread1 = new Step1Thread(countDownLatch);
        Step2Thread thread2 = new Step2Thread(countDownLatch);
        thread1.start();
        thread2.start();
        try {
            countDownLatch.await(4, TimeUnit.SECONDS); //当前线程等待， 直到count减为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3");
    }

    class Step1Thread extends Thread {
        private CountDownLatch countDownLatch;

        public Step1Thread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1"); //执行耗时任务
            countDownLatch.countDown();
        }
    }

    class Step2Thread extends Thread {
        private CountDownLatch countDownLatch;

        public Step2Thread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2"); //执行耗时任务
            countDownLatch.countDown();
        }
    }
}
