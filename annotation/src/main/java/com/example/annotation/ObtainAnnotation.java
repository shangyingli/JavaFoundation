package com.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObtainAnnotation {


    public static void main(String[] args) {

        new ObtainAnnotation();
    }

    public ObtainAnnotation() {
//        getAnnotation();
        testAnnoMethod();
    }

    public void getAnnotation() {
        //获取方法上的注解
        boolean hasAnno = A.class.isAnnotationPresent(MyAnnatation.class);
        if (hasAnno) {
            MyAnnatation annatation = A.class.getAnnotation(MyAnnatation.class);
            System.out.println("获取类A上的注解");
            System.out.println("a = " + annatation.id() + " msg = " + annatation.msg());
        }


        try {
            //获取变量a上的注解
            Field a = A.class.getDeclaredField("a");
            a.setAccessible(true);
            MyAnnatation variAnno = a.getAnnotation(MyAnnatation.class);
            if (variAnno != null) {
                System.out.println("获取变量A上的注解");
                System.out.println("a = " + variAnno.id() + " msg = " + variAnno.msg());
            }
            //获取方法bMethod上的注解
            Method bMethod = A.class.getDeclaredMethod("bMethod");
            bMethod.setAccessible(true);
            System.out.println("获取方法bMethod上的注解");
            Annotation[] methodAnno = bMethod.getAnnotations();
            for (int i = 0; i < methodAnno.length; i++) {
                System.out.println("注解: " + methodAnno[i].annotationType().getSimpleName());
                System.out.println("a = " + ((MyAnnatation)methodAnno[i]).id() + " msg = " + ((MyAnnatation)methodAnno[i]).msg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testAnnoMethod() {
        A a = new A();
        Method[] methods = a.getClass().getDeclaredMethods();
        for (Method method : methods) {
            //仅测试被MyAnnatation注解的方法
            if (method.isAnnotationPresent(MyAnnatation.class)) {
                method.setAccessible(true);
                try {
                    method.invoke(a);
                } catch (Exception e) {
                    e.printStackTrace();
                    //捕获方法异常，输出异常信息
                    System.out.println("A 类中出现的BUG");
                    System.out.println("出现bug的方法 : " + method.getName());
                    System.out.println("bug类型 ： " + e.getCause().getClass().getSimpleName());
                    System.out.println("bug信息 : " + e.getCause().getMessage());
                }
            }
        }
    }
}
