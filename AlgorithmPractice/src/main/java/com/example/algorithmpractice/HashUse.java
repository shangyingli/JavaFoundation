package com.example.algorithmpractice;

import java.util.HashMap;

public class HashUse {

    public static void main(String[] args) {
        new HashUse();
    }

    public HashUse() {
//        int[] arr = {2, 7, 11, 15};
//        int sum = 13;
//        int[] indexs = twoSum(arr, sum);
//        if (indexs != null) {
//            System.out.println("indexes ars : " + indexs[0] + ", " + indexs[1]);
//        }

        String str = "abcaecbdah";
        int maxLength = getLongSubStrNoneRepeat(str);
        System.out.println("max length is : " + maxLength);
        //根据最大长度找出对应的left，从而找出最大长度对应字串
        int left = indexs.get(maxLength);
        String subLongStr = str.substring(left, maxLength + left);
        System.out.println("subLongStr : " + subLongStr);
    }

    /**
     * 找出数组中任意两个元素之后为sum的元素对应下标
     *
     * @param arr
     * @param sum
     */
    public int[] twoSum(int[] arr, int sum) {
        HashMap<Integer, Integer> temp = new HashMap<>();
        int[] res = new int[]{0, 0};
        for (int i = 0; i < arr.length; i++) {
            int completed = sum - arr[i];
            if (temp.containsKey(completed)) {
                res[0] = temp.get(completed);
                res[1] = i;
                return res;
            }
            temp.put(arr[i], i);
        }
        return null;
    }


    /**
     * 获取最长无重复字串
     *
     * @param str
     * @return
     */
    HashMap<Integer, Integer> indexs = new HashMap<>();
    public int getLongSubStrNoneRepeat(String str) {
        String res = "";
        HashMap<Character, Integer> temp = new HashMap<>();
        int left = -1;
        int maxLength = 0;
        for (int i = 0; i < str.length(); i++) {
            if (temp.containsKey(str.charAt(i))) {
                left = temp.get(str.charAt(i));
            }
            temp.put(str.charAt(i), i);
            maxLength = Math.max(maxLength, i - left);
            indexs.put(maxLength, left); //记录maxLength对应的left
        }
        return maxLength;
    }



}
