package com.example.algorithmpractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lys on 2019/4/5
 * 构造一个栈，可以o(1)的时间复杂度获取到栈中最小元素
 */
public class MinStcak<T extends Comparable<T>> {

    private ArrayList<T> data = new ArrayList<>(); //存放push进来的数据
    private ArrayList<T> min = new ArrayList<>(); //栈顶存放栈中最小值

    public MinStcak() {

    }

    public void push(T value) {
        data.add(value);
        if (min.size() == 0) {
            min.add(value);
        } else {
            T minValue = getMin();
            if (value.compareTo(minValue) < 0) {
                min.add(value);
            }
        }
    }

    public T pop() {
        if (data.size() == 0) throw new IllegalStateException("栈空");
        if (data.get(data.size() - 1) == min.get(min.size() - 1)) {
            min.remove(min.size() - 1);
        }
        return data.remove(data.size() - 1); //出栈

    }

    public T getMin() {
        if (min.size() == 0) throw new IllegalStateException("栈空");
        return min.get(min.size() - 1); //获取栈顶元素,即最小值
    }

    public static void main(String[] args) {
        MinStcak<Integer> integerMinStcak = new MinStcak<>();
        integerMinStcak.push(2);
        integerMinStcak.push(1);
        integerMinStcak.push(2);
        integerMinStcak.push(3);
        integerMinStcak.push(4);
        integerMinStcak.push(5);
        integerMinStcak.push(6);

        System.out.println(" min value is : " + integerMinStcak.getMin());
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
        System.out.println(" min value is : " + integerMinStcak.getMin());
        System.out.println("打印min栈 ; " + min);

    }
}
