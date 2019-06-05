package com.example.algorithmpractice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lys on 2019/4/5
 * 构造一个栈，可以o(1)的时间复杂度获取到栈中最小元素
 */
public class MinStcak<T extends Comparable<T>> {

    private ArrayList<T> data = new ArrayList<>(); //存放push进来的数据
    private ArrayList<Integer> min = new ArrayList<>(); //栈顶存放栈中最小值

    public MinStcak() {

    }

    public void push(T value) {
        data.add(value);
        if (min.size() == 0) {
            min.add(indexFor(value)); //存储最小元素在data栈中的索引
        } else {
            T minValue = data.get(getMinIndex());
            if (value.compareTo(minValue) < 0) {
                min.add(indexFor(value)); //存储最小元素在data栈中的索引
            }
        }
    }

    private int indexFor(T value) {
        return data.indexOf(value);
    }

    public T pop() {
        if (data.size() == 0) throw new IllegalStateException("栈空");
        if (data.size() - 1 == min.size() - 1 ) { //要出出栈元素的索引 == min栈中最小元素的索引
            min.remove(min.size() - 1);
        }
        return data.remove(data.size() - 1); //出栈

    }

    private int getMinIndex() {
        if (min.size() == 0) throw new IllegalStateException("栈空");
        return min.get(min.size() - 1); //获取栈顶索引，即data栈中最小元素对应索引
    }

    public T getMinValue() {
        if (min.size() == 0) throw new IllegalStateException("栈空");
        return data.get(getMinIndex());
    }

    public static void main(String[] args) {
        MinStcak<Integer> integerMinStcak = new MinStcak<>();
        integerMinStcak.push(2);
        integerMinStcak.push(1);
        integerMinStcak.push(1);
        integerMinStcak.push(1);
        integerMinStcak.push(1);
        integerMinStcak.push(1);
        integerMinStcak.push(1);

        System.out.println(" min value is : " + integerMinStcak.getMinIndex());
        List<Integer> min = integerMinStcak.min;
        System.out.println("打印data栈 : " + integerMinStcak.data);
        System.out.println("打印min栈 ; " + min);
        integerMinStcak.pop();
        integerMinStcak.pop();
        integerMinStcak.pop();
        integerMinStcak.pop();
        integerMinStcak.pop();
        integerMinStcak.pop();


        System.out.println("打印data栈 : " + integerMinStcak.data);
        System.out.println(" min value is : " + integerMinStcak.getMinValue());
        System.out.println("打印min栈 ; " + min);

    }
}
