package com.example.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {

        new SetDemo();
    }


    //hash表保证元素唯一
    //不保证元素有序
    public void testHashSet() {

        Set<String> hashSet = new HashSet<String>();
        hashSet.add("hello");
        hashSet.add("java");
        hashSet.add("wold");
        hashSet.add("java");
        hashSet.add("world");

        for (String vsl : hashSet) {
            System.out.print(vsl + " ");
        }
    }

    //哈希表保证元素的唯一性
    //链表保证元素有序， 存储和读取顺序一致  通过accessOrder保证
    public void testLinkHashSet() {

        Set<String> hashSet = new LinkedHashSet<>();
        hashSet.add("hello");
        hashSet.add("java");
        hashSet.add("wold");
        hashSet.add("java");
        hashSet.add("world");

        for (String vsl : hashSet) {
            System.out.print(vsl + " ");
        }
    }

    /**
     *底层使用treemap,而treemap底层使用红黑二叉树
     */
    private void testTreeSet() {

        Set<Integer> ts = new TreeSet<>();
        Set<String> tsStr = new TreeSet<>();
        ts.add(20);
        ts.add(18);
        ts.add(23);
        ts.add(22);
        ts.add(17);
        ts.add(24);
        ts.add(19);
        ts.add(18);
        ts.add(24);

        tsStr.add("hello");
        tsStr.add("java");
        tsStr.add("wold");
        tsStr.add("java");
        tsStr.add("world");

        for (Integer val : ts) {
            System.out.print(val + " ");

        }

        System.out.println();
        for (String valStr : tsStr) {
            System.out.print(valStr + " ");

        }

        //获取降序后的结果
        Iterator iterator = ((TreeSet<Integer>) ts).descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + "->");
        }

    }

    public SetDemo() {

//        testHashSet();
//        testLinkHashSet();
        testTreeSet();
    }

}
