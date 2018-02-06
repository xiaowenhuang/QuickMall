package com.sven.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by sven on 2018/1/15.
 */
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    private static final String salt = "1a2b3c4d";
    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    public static String inputPassToDbPass(String input,String saltDB){
        String formPass = inputPassToFormPass(input);
        return formPassToDBPass(formPass,saltDB);
    }
    public static void main(String[] args) {
//dd3d33c9776d8358a9d5e5146de4c64c
        String x = inputPassToFormPass("123456");
        System.out.println(x);
        System.out.println(formPassToDBPass(x, salt));
    }
}
