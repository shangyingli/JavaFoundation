package com.example.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 通过反射修改final变量的值
 */
public class ReflectDemo {

    public static void main(String[] args) {
        new ReflectDemo();
    }

    public ReflectDemo() {
        Person person = new Person();
        System.out.println("origin name is : " + person.getName());
        modify(person, "name", "shanyili");
//        modifyHashmap();
        modifyList();
    }

    /**
     * 通过反射修改final类型变量
     * @param person
     * @param fieldName
     * @param newVlaue
     */
    private void modify(Person person, String fieldName, String newVlaue) {
        try {
            Field field = person.getClass().getDeclaredField(fieldName);
            System.out.println(" field : " + field.toString());
            field.setAccessible(true);
            field.set(person, newVlaue);
            System.out.println("filed name is : " + field.get(person));
            System.out.println("person name is : " + person.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射向hashmap添加数据， 绕过泛型检查
     */
    private void modifyHashmap() {
        HashMap<String, String> hashMap = new HashMap<>();
        String key = "key1";
        hashMap.put(key, "a");
//        hashMap.put("b", 13);
        Class c = hashMap.getClass();
        try {
            Method method = c.getDeclaredMethod("put", Object.class, Object.class);
            method.invoke(hashMap, key, 1);
            System.out.println("map is : " + hashMap);
            System.out.println(" the value of " + key + "is : " + hashMap.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
//        arrayList.add("aa");
        try {
            Method method = arrayList.getClass().getDeclaredMethod("add", Object.class);
            method.invoke(arrayList, "liyishan");
            System.out.println("list is : " + arrayList);
            System.out.println("list item : " + arrayList.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
