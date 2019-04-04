package com.example.concurrentbase;

/**
 * join方法的使用 控制线程执行优先级, 让出当前线程的时间片给其他线程
 * 调用线程等待子线程执行完毕后才运行
 * 线程执行顺序 t1 -> t2 -> t3;
 */
public class Join {

    public static void main(String[] args) {
        new Join();
    }

    public Join() {
        t1.start();
        t2.start();
        t3.start();
    }

    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行了");
        }
    }, "线程1");

    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                t1.join(2000);//让t1线程先执行, t1在1s后仍然没执行完则不再等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行了");

        }
    }, "线程2");

    Thread t3 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                t2.join(3000); //让t2线程先执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行了");
        }
    }, "线程3");

}
