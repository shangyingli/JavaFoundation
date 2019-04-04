package com.example.concurrentbase;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用BlockQueue实现生产者和消费者
 */

public class BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        new BlockQueueDemo();
    }

    public BlockQueueDemo() {

        BlockingQueue arrayBlockQueue = new ArrayBlockingQueue(10, false); //队列满会阻塞,阻塞实现到后丢弃后面要入对的元素
//        BlockingQueue linkBlockQueue = new LinkedBlockingQueue(); //阻塞无界
        Producer<Person> personProducer = new Producer<>(arrayBlockQueue);
        Consumer<Person> personConsumer = new Consumer<>(arrayBlockQueue);
        Thread producerT = new Thread(personProducer);
        Thread consumerT = new Thread(personConsumer);
        producerT.start();
        consumerT.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        producerT.stop();
    }

    class Person {
        private String name;
        private String sex;
        private int num;

        public Person(String name, String sex, int num) {
            this.name = name;
            this.sex = sex;
            this.num = num;
        }

        @Override
        public String toString() {
            return "[ " + this.name + ", " + this.sex + "," + num + "]";
        }
    }

    class Producer<T> implements Runnable {

        private BlockingQueue<T> blockingQueue;
        private boolean flag = true;

        public Producer(BlockingQueue<T> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            System.out.println("生产者开始生产人");
            int num = 0;
            try {
                while (flag) {
                    Person person = new Person("小红", "女", ++num);
                    //使用ArrayBlockQueue时， 若生产速度快于消费速度， 则会存在生产的人无法入队列，会进行等待入队列
                    //直到被消费，
                    boolean succ = blockingQueue.offer((T) person, 5000, TimeUnit.MILLISECONDS); //超过1s后被丢弃
//                    blockingQueue.put((T) person); //put方法会一直等待，直到queue不满
//                    System.out.println("成功生产 : " + person.toString());
                    if (succ) {
                        System.out.println("成功生产 : " + person.toString());
                    } else {
                        System.out.println("对满， 等待1秒后仍然对满， 丢弃 : " + person.toString());
                    }
                    Thread.sleep(10); //延时生产
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者生产结束");
            }

        }
    }

    class Consumer<T> implements Runnable {

        private BlockingQueue<T> blockingQueue;
        private boolean flag = true;

        public Consumer(BlockingQueue<T> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            System.out.println("消费者开始获取人");
            try {
                while (flag) {
                    //如果等待时间过长， 而此时线程被终止， 会出现IllegalMonitorStateException
//                    T person = blockingQueue.poll(5, TimeUnit.SECONDS); //当前队列为空时等待阻塞5s
                       T person = blockingQueue.take(); //take方法会一直等待，直到queue不为空

                    if (person == null) {
                        System.out.println("消费者在5秒内没有获取到人");
                        flag = false;
                    } else {
                        System.out.println("消费成功 : " + person.toString());
                    }
                    Thread.sleep(6000); //延时消费
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("消费结束");
            }

        }
    }


}
