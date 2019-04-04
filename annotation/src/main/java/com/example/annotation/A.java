package com.example.annotation;

@MyAnnatation(id = 1,msg = "我是类test")
public class A {

    @MyAnnatation(id = 2, msg = "我是变量a")
    int a;

    @MyAnnatation(id  = 3, msg = "我是方法b")
    public void bMethod() {
        System.out.println("bMethod 正常运作 ; " +  (1 + 1));
    }

    public void bMethod2() {
        System.out.println("bMethod1 正常运作 ; " + (1 - 1));
    }

    @MyAnnatation(id = 4)
    public void bMethod3() {
        System.out.println("bMethod1 正常运作 ; " + (1 / 0));
    }


}
