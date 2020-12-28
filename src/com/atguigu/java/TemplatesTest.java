package com.atguigu.java;

import java.util.ArrayList;

/**
 * @author mcw
 * @date 2019\11\16 0016-17:46
 *
 * 1.IDEA中的模板所处的位置：settings-Editor-Live Templates/ Postfix Completion
 * 2.常用模板
 */
public class TemplatesTest {
    //模板六:prsf:可生成 private static final
    private static final Customer cust=new Customer();

    //变形:psf / psfi /psfs
    public static final int NUM=1;

    //模板一:psvm(已修改为main)
    public static void main(String[] args) {

        //模板二:sout(即syso)
        System.out.println("hello!");
        //变形:soutp / soutm / soutv / xxx.sout
        System.out.println("args = [" + args + "]");
        System.out.println("TemplatesTest.main");
        int num=10;
        System.out.println("num = " + num);
        int num2=20;
        System.out.println("num2 = " + num2);
        System.out.println(num);

        //模板三:fori
        String[]  arr=new String[]{"1","2","3"};
        for (int i = 0; i <arr.length ; i++) {
            System.out.println(arr[i]);
        }
        //变形:iter / itar
        for (String s : arr) {
            System.out.println(s);
        }
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            System.out.println(s);
        }


        //模板四:list.for
        ArrayList list=new ArrayList();
        list.add(123);
        list.add(456);
        list.add(789);

        for (Object o : list) {

        }
        //变形:list.fori / list.forr

        for (int i = 0; i < list.size(); i++) {

        }

        for (int i = list.size() - 1; i >= 0; i--) {

        }

    }

    public void method() {
        System.out.println("TemplatesTest.method");

        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(789);

        //模板五:ifn
        if (list == null) {

        }
        //inn
        if (list != null) {

        }

        //变形:xxx.nn / xxx.null
    }
}
