package com.example.hideandoveride;

public class SuperClass {

    public static int i = 1;
    public int j = 2;
    public final int k = 3;

    public static void method1() {
        System.out.println("SuperClass Method1");
    }

    public void method2() {
        System.out.println("SuperClass method2");
    }

    public final void method3() {
        System.out.println("SuperClass method3");
    }
}
