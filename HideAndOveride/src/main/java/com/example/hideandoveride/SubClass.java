package com.example.hideandoveride;

public class SubClass extends SuperClass {

    public static int i  = 2;
    public static int j = 1;
    public final int k = 4;

    public static void method1() {
        System.out.println("SubClass Method1");
    }

    @Override
    public void method2() {
        System.out.println("SubClass method2");
    }

    // final method can't override
//    public final void method3() {
//        System.out.println("SuperClass method3");
//    }

}
