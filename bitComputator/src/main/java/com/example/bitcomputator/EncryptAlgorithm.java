package com.example.bitcomputator;

import java.util.Base64;

public class EncryptAlgorithm {

    public static void main(String[] args) {
        new EncryptAlgorithm();
    }

    public EncryptAlgorithm() {
        testBase6();
    }

    /**
     * base64 编码与解码
     */
    private void testBase6() {
        String name = "liyishan";
        String encodeStr = Base64.getEncoder().encodeToString(name.getBytes());
        System.out.println("the base64 of " + name + " is : " + encodeStr);
        byte[] decodeByte = Base64.getDecoder().decode(encodeStr);
        String decodeStr = new String(decodeByte); //将字节数组转化为string
        System.out.println("decode is : " + decodeStr);
    }

}
