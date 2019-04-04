package com.example.sortapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.TreeSet;

public class SortDemo {

    public static void main(String[] args) {
        new SortDemo();
    }

    public SortDemo() {
//        testArrays();
//        testCollections();
        testTreeset();
    }

    public void testArrays() { //对数组进行排序
        int[] intArrays = new int[]{5, 7, 3, 2, 9};
        Arrays.sort(intArrays);
        System.out.println("array after sorted : " + Arrays.toString(intArrays));
        //使用二分查找时需要保证数组有序
        int index = Arrays.binarySearch(intArrays, 5);
        System.out.println("index : " + index);
        String[] strArray = {"ecb", "ccc", "aaa", "has"};
//        Arrays.sort(strArray); //使用string的默认排序器
        Arrays.sort(strArray, new Comparator<String>() { //不使用string的默认排序器
            @Override
            public int compare(String s, String t1) {
                return t1.compareTo(s); //降序
            }
        });
        System.out.println("str arr after sorted : " + Arrays.toString(strArray));

        //对象排序,使用自定义的比较器
        Person p1 = new Person("cc", 31);
        Person p2 = new Person("aa", 15);
        Person p3 = new Person("bb", 55);
        Person[] people = {p1, p2, p3};
//        Arrays.sort(people); //使用Person内部的比较器
//        Arrays.sort(people, new PersonComparator()); //自定义外部比较器，覆盖Person内部的比较器
        List<Person> list = new ArrayList<Person>(); //对集合排序，可以先将集合转化为数组
        list.add(p1);
        list.add(p2);
        list.add(p3);
        Person[] listToArray =  list.toArray(new Person[0]); //new Person[0] 此参数会强转
        Arrays.sort(listToArray);
        System.out.println("str arr after sorted : ");
        for (Person person : listToArray) {
            System.out.print(person.toString() + "->");
        }


    }

    public void testCollections() { //对集合进行排序
        Person p1 = new Person("cc", 31);
        Person p2 = new Person("aa", 15);
        Person p3 = new Person("bb", 55);
        List<Person> list = new ArrayList<Person>(); //对集合排序，可以先将集合转化为数组
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Collections.sort(list, new PersonComparator()); //内部使用的时Arrays.sort方法
//        Collections.reverse(list);//对集合反序
        System.out.println("str arr after sorted : ");
        for (Person person : list) {
            System.out.print(person.toString() + "->");
        }

        Integer[] intArrays = {5, 7, 3, 2, 9}; //对数组反序
        List<Integer> list1 = Arrays.asList(intArrays);
        Collections.reverse(list1);
        Collections.sort(list1);
        int index = Collections.binarySearch(list1, 5);
        System.out.println("index : " + index);
        System.out.println(list1);
    }

    public void testTreeset() {
        Integer[] intArrays = {5, 7, 3, 2, 9};
        List<Integer> list1 = Arrays.asList(intArrays);
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.addAll(list1); //使用红黑二叉树
        Iterator<Integer> integerIterator = treeSet.descendingIterator(); //遍历时采用中序遍历
        while (integerIterator.hasNext()) {
            System.out.print(integerIterator.next() + " -> ");
        }
    }

    class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person person, Person t1) {
            if (person.name.compareTo(t1.name) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    class Person implements Comparable<Person> {
        int age;
        String name;

        public Person(String name, int age) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            Person that = o;
            return (this.age - that.age) > 0 ? 1 : -1;
        }

        @Override
        public String toString() {
            return "[name = " + name + ",age = " + age + "]";
        }
    }

}
