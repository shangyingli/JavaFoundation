package com.example.concurrentbase;

public class SingleInstance {

    //双重检查锁定与延迟初始化

//    private static volatile SingleInstance singleInstance; //使用volatile防止指令重排,确保类正确初始化后返回该应用

//    public static SingleInstance getInstance() {
//        if (singleInstance == null) {
//            synchronized (SingleInstance.class) {
//                if (singleInstance == null) {
//                    singleInstance = new SingleInstance();
//                }
//            }
//        }
//        return singleInstance;
//    }


    //方法2
    //类初始化时，线程会持有该类的锁对象
    private static class InstanceHolder {
        static SingleInstance instance = new SingleInstance();
    }

    public static SingleInstance getInstance() {
        return InstanceHolder.instance;
    }

}
