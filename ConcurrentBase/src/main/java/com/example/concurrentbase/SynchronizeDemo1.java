package com.example.concurrentbase;

/**
 * synchronized
 */
public class SynchronizeDemo1 {

    public static void main(String[] args) {
        new SynchronizeDemo1();
    }

    public SynchronizeDemo1() {
        TicketTask ticketTask = new TicketTask();
        TicketTask ticketTask1 = new TicketTask();

        Thread window1 = new Thread(ticketTask);
        Thread window2 = new Thread(ticketTask1);
        window1.start();
        window2.start();
    }

    static class TicketTask implements Runnable {

        private static int ticketNum = 100; //静态变量是线程共享的
        private static Object lockObject = new Object(); //锁必须是同一把锁才行

        @Override
        public void run() {
            while (ticketNum > 0) {
//                saleTicket();
//                saleTicket2();
//                saleTicket3();
//                saleTicket4();
                saleTicket5();
            }
        }

        /**
         * 实例方法 此时锁为this
         */
        public synchronized void saleTicket() {
            if (ticketNum > 0) {
                System.out.println("窗口 ： " + Thread.currentThread().getName() + "出售第 " + (100 - ticketNum-- + 1) + "张票");
            }
        }

        /**
         * 同步代码块
         * 锁为this,当前对象
         */
        public synchronized void saleTicket2() {
            synchronized (this) {
                if (ticketNum > 0) {
                    System.out.println("窗口 ： " + Thread.currentThread().getName() + "出售第 " + (100 - ticketNum-- + 1) + "张票");
                }
            }
        }

        /**
         * 同步代码块
         * 锁为类
         */

        public synchronized void saleTicket3() {
            synchronized (SynchronizeDemo1.class) {
                if (ticketNum > 0) {
                    System.out.println("窗口 ： " + Thread.currentThread().getName() + "出售第 " + (100 - ticketNum-- + 1) + "张票");
                }
            }
        }

        /**
         * 同步代码块
         * 锁为任意对象,锁必须为同一把锁
         */

        public synchronized void saleTicket4() {
            synchronized (lockObject) {
                if (ticketNum > 0) {
                    System.out.println("窗口 ： " + Thread.currentThread().getName() + "出售第 " + (100 - ticketNum-- + 1) + "张票");
                }
            }
        }

        /**
         * 静态方法， 锁为class
         * 类锁
         */

        public static synchronized void saleTicket5() {
            if (ticketNum > 0) {
                System.out.println("窗口 ： " + Thread.currentThread().getName() + "出售第 " + (100 - ticketNum-- + 1) + "张票");
            }
        }


    }


}
