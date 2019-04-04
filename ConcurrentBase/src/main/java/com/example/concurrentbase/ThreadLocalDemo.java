package com.example.concurrentbase;

/**
 * threadLocal为线程的局部变量
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        new ThreadLocalDemo();
    }

    public ThreadLocalDemo() {
        Task task = new Task();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }

    class Task implements Runnable {

        ThreadLocal<Integer> localNum = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                int num = localNum.get();
                System.out.println("thread : " + Thread.currentThread().getName() + " num = " + num);
                localNum.set(++num);
            }
        }
    }
}
