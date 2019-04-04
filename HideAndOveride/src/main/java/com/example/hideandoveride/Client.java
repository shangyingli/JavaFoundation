package com.example.hideandoveride;

/**
 * java继承中隐藏和覆盖
 */

public class Client {

    public static void main(String[] args) {

        SuperClass sc = new SubClass();
        System.out.println("i = " + sc.i);
        System.out.println("j = " + sc.j);
        System.out.println("k = " + sc.k);
        sc.method1();
        sc.method2();
        sc.method3();
        System.out.println("---------");

        SubClass sbc = new SubClass();
        System.out.println("i = " + sbc .i);
        System.out.println("j = " + sbc .j);
        System.out.println("k = " + sbc .k);
        sbc .method1();
        sbc.method2();
        sbc.method3();

    }
}
