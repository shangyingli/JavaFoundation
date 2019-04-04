package com.example.concurrentbase;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable{
    public static void main(String[] args) {
        Ticket ticket = new Ticket(20);
        Thread window01 = new Thread(ticket, "窗口01");
        Thread window02 = new Thread(ticket, "窗口02");
        Thread window03 = new Thread(ticket, "窗口03");
        window01.start();
        window02.start();
        window03.start();
    }

    private int num;//票数量
    private static int numms;
    Lock lock = new ReentrantLock();
//    AtomicInteger num;
    private boolean flag=true;//若为false则售票停止
    public Ticket(int num){
        this.num=num;
    }
    @Override
    public void run() {
        while(flag){
            ticket();
        }
    }

    private synchronized static void ticketss() {//类锁
//        if(numms<=0){
//            flag=false;
//            return;
//        }
        try {
            Thread.sleep(20);//模拟延时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出当前窗口号以及出票序列号
        try {
//            lock.lock();
            System.out.println(Thread.currentThread().getName()+"售出票序列号："+ numms);
        } finally {
//            lock.unlock();
        }

    }
    private synchronized void ticket(){ //对象锁
        if(num<=0){ //非原子操作
            flag=false;
            return;
        }
        try {
            Thread.sleep(20);//模拟延时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出当前窗口号以及出票序列号
        try {
            synchronized (this) { //同步块加到此处无效，因为改方法内其他地方也有最num变量的非原子操作
                synchronized (this) { //锁重入
                    System.out.println(Thread.currentThread().getName()+"售出票序列号："+ num--);//非原子操作
                }

            }
//            lock.lock();
        } finally {
//            lock.unlock();
        }
    }
}
