package com.example.concurrentbase;

/**
 * 生产者消费者模型
 * wait notify
 * 必须和synchronize配合使用
 */

public class ProducerAndConsumer {

    class Person {
        private String name;
        private String sex;
        private boolean flag = false; //flag 为true 只能读，不能写
    }

    class WriteThread extends Thread {

        private Person person;

        public WriteThread(Person person) {
            this.person = person;
        }

        @Override
        public void run() {
            int count = 0;
            while (true) {
                synchronized (person) {
                    if (person.flag) { //不能写
                        try {
                            System.out.println(Thread.currentThread().getId() + "等待写");
                            person.wait(); //线程等待，且释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getId() + "正在写");
                    if (count == 0) {
                        person.name = "小红";
                        person.sex = "女";
                    } else {
                        person.name = "小军";
                        person.sex = "男";
                    }
                    count = (count + 1) % 2;
                    person.flag = true;
                    person.notify();
                }
            }
        }
    }

    class ReadTread extends Thread {

        private Person person;

        public ReadTread(Person person) {
            this.person = person;
        }

        @Override
        public void run() {
                while (true) {
                    synchronized (person) { //必须为同一把锁才行，即同一个对象
                        try {
                            if (!person.flag) { //flag为false, 不能读
                                System.out.println(Thread.currentThread().getId() + "等待读");
                                person.wait();
                            }
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getId() + "正在读");
                        System.out.println(person.name + " : " + person.sex);
                        person.flag = false;
                        person.notify();
                    }
                }
            }
    }

    public static void main(String[] args) {
        ProducerAndConsumer instance = new ProducerAndConsumer();
    }

    public ProducerAndConsumer() {

        Person person = new Person();
        WriteThread writeThread = new WriteThread(person);
        writeThread.start();
        ReadTread readTread = new ReadTread(person);
        readTread.start();
    }
}
