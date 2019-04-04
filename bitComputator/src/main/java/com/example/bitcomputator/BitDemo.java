package com.example.bitcomputator;

import java.util.Base64;

public class BitDemo {

    public static void main(String[] args) {
        new BitDemo();
    }

    public BitDemo() {
//        bitTest1();
//        testAscii();
//        isOddByShift();
        isOddByBitOperator();
    }

    /**
     * 移位操作符
     * 左移乘2，右移除2
     */
    private void bitTest1() {
        int i = 19;
//        System.out.println(" origin 10 decimal is : " + i + "binary decimal is : " + Integer.toHexString());
        int res = i >> 3;
        System.out.println(res);

    }

    /**
     * 使用移位操作符判断奇数偶数
     */
    private void isOddByShift() {
        int i = -19;
        if (i >> 1 << 1 != i) { //相当于先除2在乘以2
            System.out.println(i + " is odd");
        } else {
            System.out.println(i + " is even");
        }
    }


    /**
     * 使用位与判断奇数偶数性
     * （i & 1) == 1 说明i转化为2进制时，最后一位必为1,则必为奇数
     */
    private void isOddByBitOperator() {
        int i = -19;
        if ((i & 1) == 1) {
            System.out.println(i + " is odd");
        } else {
            System.out.println(i + " is even");
        }
    }

    private void testAscii() {
        //字符的2进制
        char c = 'l';
        System.out.println("the ascii of '" + c + "' is : " +  Integer.toBinaryString(c) + "hex value : " + Integer.toHexString(c)); //1101100

        //字符串的2进制
        String str = "AB";
        char[] chars = str.toCharArray();
        String res = "";
        for (int i = 0; i < chars.length; i++) {
            res += Integer.toBinaryString(chars[i]);
        }
        System.out.println("the ascii of '" + str + "' is : " +  res);
    }

}
