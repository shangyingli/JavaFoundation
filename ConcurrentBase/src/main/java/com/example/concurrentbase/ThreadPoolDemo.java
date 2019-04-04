package com.example.concurrentbase;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池API
 */

public class ThreadPoolDemo {

    public static void main(String[] args) {
        new ThreadPoolDemo();
    }

    public ThreadPoolDemo() {
//        cacheThreadPool();
//        fixedThreadPool();
//        scheduleThreadPoll();
//        singleThreadPool();
        threadPoolExe();
    }

    private class Task implements Runnable {

        private int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程为 : " + Thread.currentThread().getId() + ",当前任务 : " + taskId);
        }
    }

    /**
     * 没有设置核心线程数， 但会复用已经创建的线程
     */
    private void cacheThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20 ; i++) {
            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }

    /**
     * 只会产生固定个线程， 然后复用这几个线程
     * 底层为无界的linkBlockQueue
     */
    private void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 20 ; i++) {
            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }

    /**
     * 可延迟执行任务的线程池
     * 底层为DelayWorkQueue, 是一个无界队列
     * 因此和FixedThreadPool相似
     *
     */
    private void scheduleThreadPoll() {
        ExecutorService executorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10 ; i++) {
            ((ScheduledExecutorService) executorService).schedule(new Task(i), 2, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }

    /**
     * 单个线程执行任务， 因此会顺序执行
     */
    private void singleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10 ; i++) {
            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }

    /**
     * 自定义线程池
     *
     */
    private void threadPoolExe() {
        ExecutorService executorService = new ThreadPoolExecutor(3, 10,
                0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(8));
        for (int i = 0; i < 20 ; i++) {
            if (i == 18) { //延迟执行第18个任务， 等待任务从队列移除
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            executorService.execute(new Task(i));
        }
        executorService.shutdown();

    }

}
