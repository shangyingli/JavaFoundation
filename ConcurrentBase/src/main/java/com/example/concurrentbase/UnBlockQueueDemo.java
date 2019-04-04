package com.example.concurrentbase;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 非阻塞队列实现生产者消费者模型
 * 需要结合 wait, notify
 */

public class UnBlockQueueDemo {

    public static void main(String[] args) {
        new UnBlockQueueDemo();
    }

    public UnBlockQueueDemo() {

//        Queue courrentLinkQueue = new ConcurrentLinkedQueue(); //非阻塞无界
        Queue concurrentQue = new PriorityQueue(10); //非阻塞队列
        for (int i = 0; i < 20; i++) {
            concurrentQue.offer("i= " + i);
        }
        Iterator iterator = concurrentQue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " - > ");
        }

    }


}
