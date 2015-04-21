package com.feemung.binarycalculator.lib;

/**
 * Created by laocow on 2015/4/19.
 */
public class Transform {
    public static boolean checkIsEmpty(String str) {
        if(str==null||str.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public static String binary2decimal(String str) {
        return  String.valueOf( Long.parseLong(str,2));

    }
    public static String octal2decimal(String str) {
        return  String.valueOf( Long.parseLong(str,8));
    }
    public static String hexadecimal2decimal(String str) {
        return  String.valueOf( Long.parseLong(str,16));
    }
    public static String decimal2octal(String decimal) {
        return Long.toOctalString(Long.parseLong(decimal));
    }

    public static String decimal2hexadecimal(String decimal) {
        return Long.toHexString(Long.parseLong(decimal));
    }

    public static String decimal2binary(String decimal) {
        return Long.toBinaryString(Long.parseLong(decimal));
    }

}
