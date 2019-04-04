package com.example.concurrentbase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读的时候不能写，写的时候不能读， 写的时候不能写
 * 但读的时候可以读
 */

public class ReadWriteLock {

    private Map<String, String> map = new HashMap<>();
    ReentrantReadWriteLock.ReadLock readLock;
    ReentrantReadWriteLock.WriteLock writeLock;

    public static void main(String[] args) {
        new ReadWriteLock();
    }

    public ReadWriteLock() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
        WriteThread writeThread = new WriteThread();
        WriteThread writeThread1 = new WriteThread();
        ReadThread readThread = new ReadThread();
        ReadThread readThread1 = new ReadThread();
        writeThread.start();
        writeThread1.start();
//        readThread.start();
//        readThread1.start();
    }

    class ReadThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                get(i + "");
            }

        }
    }

    class WriteThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                put(i + "", i + "");
            }
        }
    }

    private  void put(String key, String value) {
        writeLock.lock();
        System.out.println("Thread : " + Thread.currentThread().getName() + "写 key " + key + "开始");
        try {
            Thread.sleep(100);
            map.put(key, value);
            System.out.println("Thread : " + Thread.currentThread().getName() + "写 key " + key + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }

    private  String get(String key) {
        readLock.lock();
        System.out.println("Thread : " + Thread.currentThread().getName() + "读 key" + key + "开始");
        try {
            Thread.sleep(1000);
            String value = map.get(key);
            System.out.println("Thread : " + Thread.currentThread().getName() + "读取key " + key + " value : " + value + "结束");
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return null;
    }
}
