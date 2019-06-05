package com.example.stackoverflowdemo;

import java.util.ArrayList;

public class MyClass {

    private int index = 1;

    public static void main(String[] args) {
        new MyClass();
        //模拟OOM ERROR
        ArrayList list = new ArrayList();
        while (true) {
            list.add(new MyClass());
        }
    }

    public MyClass() {
//        testStackOverFlowError();
    }

    private void method() {
        index++;
        method();
    }

    /**
     * 模拟StackOverFlowError
     */
    private void testStackOverFlowError() {
        try {
            method();
        } catch (Exception e) {
            System.out.println("程序所允许的栈大小 > 允许的栈深度 : " + index);
            e.printStackTrace();
        }
    }

}
