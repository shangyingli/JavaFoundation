package com.example.collection;

import java.util.HashMap;

/**
 * hashmap底层是使用数组和单链表存储数据
 * 其中数组存放key的hash值不同的数据
 * 单链表存放key的hash值相同的数据
 * hash函数使用的是独特的hash方法
 * 解决冲突使用的是链地址法解决冲突
 *
 * @param <K>
 * @param <V>
 */

public class CustomHashMap<K, V> {

    public static void main(String[] args) {

        // TODO: 2019/2/28 代码有问题，扩容出现问题

        CustomHashMap<String, String> customHashMap = new CustomHashMap<>();
        Long aBeginTime = System.currentTimeMillis();//记录BeginTime
        for (int i = 0; i < 100; i++) {
            customHashMap.put("" + i, "" + i * 100);
        }
        Long aEndTime = System.currentTimeMillis();//记录EndTime
        System.out.println("insert time-->" + (aEndTime - aBeginTime));

        Long lBeginTime = System.currentTimeMillis();//记录BeginTime
        String res = customHashMap.get("" + 2);
        System.out.println("res : " + res);
        Long lEndTime = System.currentTimeMillis();//记录EndTime
        System.out.println("seach time--->" + (lEndTime - lBeginTime));
    }


    private Entry<K, V>[] container; //存放hash值不同的元素
    private int size; //当前hashmap中元素个数
    private static int INIT_CAPACITY = 256; //初始化容量
    private static float LOAD_FACTOR = 0.75f; //装载因子， 影响平均查找长度
    private int max; //数组中最大元素个数

    public CustomHashMap(int initCapacity, float loadFactor) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("非法容量");
        }
        if (loadFactor < 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("非法装载因子");
        }
        this.LOAD_FACTOR = loadFactor;
        max = (int) (initCapacity * loadFactor); //数组中最大元素个数
        container = new Entry[INIT_CAPACITY];
    }

    public CustomHashMap() {
        this(INIT_CAPACITY, LOAD_FACTOR);
    }

    public boolean put(K key, V value) {
        //计算key的hask值
        ///因为自己很难写出对不同的类型都适用的Hash算法，故调用JDK给出的hashCode()方法来计算hash值
        int hash = key.hashCode();
        System.out.println("  put KEY " + key + " HASH" + hash);
        Entry<K, V> temp = new Entry<>(key, value, hash);
        //先判断是否需要扩容
        if (size > container.length * LOAD_FACTOR) {
            System.out.println("need");
            reSize(container.length * 4);
        }
        if (setEntry(temp, container)) {
            size++;
            return true;
        }
        return false;
    }


    public V get(K k) {
        Entry<K, V> entry;
        int hashCode = k.hashCode();
        System.out.println(" get KEY "  +k + "HASH" + hashCode + "index : " + indexFor(hashCode, INIT_CAPACITY));
        int index = indexFor(hashCode, INIT_CAPACITY);
        entry = container[index];
        if (null == entry) {
            System.out.println("null == entry");
            return null;
        }

        while (null != entry) {
            if (k == entry.key || k.equals(entry.key)) {

                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * 扩容的方法
     *
     * @param newSize 新的容器大小
     */
    private void reSize(int newSize) {

        System.out.println("resize"  +newSize);
        // 1.声明新数组
        Entry<K, V>[] newTable = new Entry[newSize];
        max = (int) (newSize * LOAD_FACTOR);
        // 2.复制已有元素,即遍历所有元素，每个元素再存一遍
        for (int j = 0; j < container.length; j++) {
            Entry<K, V> entry = container[j];
            //因为每个数组元素其实为链表，所以…………
            while (null != entry) {
                Entry nextEntry = entry.next;
                setEntry(entry, newTable);
                entry = nextEntry;
            }
        }
        // 3.改变指向
        container = newTable;


    }

    //将数据插入hash表中
    private boolean setEntry(Entry<K, V> temp, Entry[] table) {
        //根据hash值找到在数组中的下标
        int index = indexFor(temp.hash, INIT_CAPACITY);
        System.out.println( " key " + temp.key + "index : " + index);
        Entry<K, V> entry = table[index];
        if (null != entry) {
            //如果存在判断是否和链表里面的数据相同， 相同则不存
            while (entry != null) {
                if ((temp.key == entry.key || temp.key.equals(entry.key))
                        && (temp.value == entry.value || temp.value.equals(entry.value))
                        && temp.hash == entry.hash) {
                    return false;
                } else if (temp.key != entry.key && temp.value != entry.value) {
                    if (null == entry.next) {
                        break;
                    }
                    entry = entry.next;
                }
            }
            //若遍历到最后都没有相同的数据，将temp数据存到链表尾
            addEntry2Last(temp, entry);
        }
        //不存在，直接加到数组中
        setFirstEntry(temp, index, table);
        return true;
    }

    private void addEntry2Last(Entry<K, V> temp, Entry<K, V> entry) {
        entry.next = temp;
    }

    private void setFirstEntry(Entry<K, V> entry, int index, Entry[] container) {
//        if (size > max) {
//            reSize(container.length * 4);
//        }
        container[index] = entry;
        entry.next = null;
    }

    private int indexFor(int hashCode, int containerLength) {
        return hashCode & (containerLength - 1);
    }

    private class Entry<K, V> {
        private Entry<K, V> next;
        private K key;
        private V value;
        private int hash;

        public Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }
}
