package com.atguigu.java;

/**
 * @author mcw
 * @date 2019\11\16 0016-17:48
 */
public class Customer {
    public static void main(String[] args){
        System.out.println("这是一个顾客");
        StringBuffer str=new StringBuffer("we are app ");
        str.append("wahh,is just like");
        replaceStr(str);

    }


    public  static void replaceStr(StringBuffer buffer){
        String s = buffer.toString();
        String s1 = s.replaceAll("\\s", "%20");
        System.out.println(s1);

    }
}
