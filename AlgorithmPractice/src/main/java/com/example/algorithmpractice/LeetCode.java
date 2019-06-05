package com.example.algorithmpractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode {

    public static void main(String[] args) {
        new LeetCode();
    }

    public LeetCode() {

//        String str = "A man, a plan, a canal:Panama";
//        if (isSymmetric(str)) {
//            System.out.println(str + "是回文串");
//        } else {
//            System.out.println(str + "不是回文串");
//        }

//        String symStr = "baaba";
//        List<List<String>> lists = splitSymmetricStr(symStr);

//        String str = "abcdef";
//        System.out.println("after reverse : " + reverseStr(str));

        System.out.println("str to integer : " + strToInteger("-a9783"));
    }

    /**
     * 判断str是否为回文串
     *
     * @param str
     */
    private boolean isSymmetric(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            } else if (Character.toLowerCase(str.charAt(left)) == Character.toLowerCase(str.charAt(right))) { //忽略大小写
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 分割回文串
     * ep:  aaba -> ["a", "a", "b", "a"],["a", "aba"]
     */
    private List<List<String>> splitSymmetricStr(String str) {
        ArrayList<String> temp = new ArrayList<>(); //存放所有分割的情况
        if (str.isEmpty()) return res;
        dfs(str, temp, 0);
        return res;
    }

    private ArrayList<List<String>> res = new ArrayList<>();

    /**
     * 使用深度优先搜索分割并寻找回文串
     *
     * @param str
     * @param temp
     * @param left
     */
    private void dfs(String str, List<String> temp, int left) {
        if (left == str.length()) {
            res.add(temp);
            System.out.println("res : " + res);
            return;
        }
        for (int right = left; right < str.length(); right++) {
            if (isSymmetricStrSimple(str, left, right)) {
                temp.add(str.substring(left, right + 1));
                dfs(str, temp, right + 1);
                temp.remove(temp.size() - 1); //移除最后一个元素，以便存放新的回文字串
            }
        }
    }

    /**
     * 判断字串是否为回文串，字串中不包含除数字和字母的其他字串
     *
     * @param left  str的左下标
     * @param right str的右下标
     * @return
     */
    private boolean isSymmetricStrSimple(String str, int left, int right) {
        while (left < right && str.charAt(left) == str.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }

    /**
     * 使用两个指针遍历并交换
     * 将字符串反转
     * @param str
     * @return
     */
    private String reverseStr(String str) {
        char[] charArray = str.toCharArray(); //先将字符串转化为字符数组,底层使用了System.arrayCopy
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            swap(charArray, left, right);
            left++;
            right--;
        }
        return new String(charArray);
    }

    /**
     * 交换数组中指定位置元素
     * @param chars
     * @param left
     * @param right
     */
    private void swap(char[] chars, int left, int right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }

    /**
     * 字符串转化为整数
     *
     * @param str
     * @return
     */
    private int strToInteger(String str) {
        if (str == null || str.isEmpty()) throw new IllegalArgumentException("str can't be empty size!"); //空字符串
        int res = 0;
        boolean isNegative = str.charAt(0) == '-'; //判断是否位负数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) { //若第一位为符号位
                continue;
            }

            if (c < '0' || c > '9') { //非法字符抛出异常
                throw new IllegalArgumentException("str contains none digit character!");
            }
            res = res * 10 + (c - '0');
        }
        return isNegative ? -res : res;
    }

}
