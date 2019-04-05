package com.example.sortapi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 对数字进行反转
 */

public class ReverseDigit {
    public static void main(String[] args) {

        new ReverseDigit();
    }

    public ReverseDigit() {
//        System.out.println(reverseByStr(1230));
//        System.out.println(revserseNormal(1230));
        System.out.println(reverseStr("abcdefg"));
    }

    /**
     * 使用队列实现数字反转
     */
    private int reverseByQueue(int n) {
        LinkedList<Integer> queue = new LinkedList<>();
        int mode = 0;
        do {
            mode = n % 10;
            queue.offer(mode);
            n = n / 10;

        } while (n > 0);

        int res = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            res += queue.poll() * Math.pow(10, size -1 );
        }
      return res;
    }

    /**
     * 将数字转化为字串
     * @param n
     * @return
     */
    private int reverseByStr(int n) {
        int res;
        //.使用stringBuffer
        String str = String.valueOf(n);
        StringBuffer sb = new StringBuffer(str);
//        String reverseStr = sb.reverse().toString();
//        res = Integer.parseInt(reverseStr);
        //2.将字符串转化为数组倒序输出
//        char[] charArr = str.toCharArray();
//        String reverse = "";
//        for (int i = charArr.length - 1; i >= 0; i--) {
//            reverse += charArr[i];
//        }
//        res = Integer.parseInt(reverse);
        //3.将字符串转化为集合使用Collecrion的reverse方法
        String[] strArr = str.split("");
        List<String> list = Arrays.asList(strArr);
        Collections.reverse(list);
        String reverse = "";
        for (String s : list) {
            reverse += s;
        }
        res = Integer.parseInt(reverse);
        return res;
    }

    /**
     * 每次对低位元素 * 10，并加上次低位元素
     * @param n
     * @return
     */
    private int revserseNormal(int n) {
        int result = 0;
        while (n > 0) {
            int div = n % 10;
            result = result * 10 + div;
            n = n / 10;
        }
        return result;
    }

    /**
     * 使用stringBuilder或者stringBuffer实现字符串反转
     * @param str
     * @return
     */
    private String reverseStr(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        return stringBuilder.reverse().toString();
    }
}
