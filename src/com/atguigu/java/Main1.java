package com.atguigu.java;

import jdk.internal.dynalink.beans.StaticClass;

import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author mcw 2020\3\26 0026-19:18
 */
public class Main1 {
    public static void main(String[] args) {
//        long value=0L; int i=0;
//        int[] replace=new int[9];
//        boolean flag=true;
//        Scanner scanner=new Scanner(System.in);
//        while (flag&&scanner.hasNext() ){
//           value = scanner.nextLong();
//            replace[0]=scanner.nextInt();
//            replace[1]=scanner.nextInt();
//            replace[2]=scanner.nextInt();
//            replace[3]=scanner.nextInt();
//            replace[4]=scanner.nextInt();
//            replace[5]=scanner.nextInt();
//            replace[6]=scanner.nextInt();
//            replace[7]=scanner.nextInt();
//            replace[8]=scanner.nextInt();
//            long val= repleall(value, replace);
//            System.out.println(val);
//
//        }
        //数字替换
        /*System.out.println(repleall(73598793378342493L,
                new int[]{1,3,6,1,6,8,9,1,3}));*/

        //求圆环面积
        /*int[] actual = insertIntoArray(100000);
        long start = System.currentTimeMillis();
        System.out.println(mainij(100000,actual));
        long end = System.currentTimeMillis();
        System.out.println(end-start);*/


    }

    public static long repleall(long value,int[] array){
        String s = String.valueOf(value);
        StringBuffer str=new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='-'){
                continue;
            }
            Integer integer = Integer.valueOf(Character.toString(c));
            int a = array[integer-1];
            //String s1 = String.valueOf(a);
            str.append(a);
        }
        Long aLong = new Long(String.valueOf(str));
        //long hh=Integer.valueOf(new String(str));
        return aLong;
    }


    public static double mainij(int n,int[] array){
        double s=0.0f;
        Arrays.sort(array);
        if(n%2==0) --n;
        for(int i=n-1;i>=1;i-=2){

            double v = Math.pow(array[i], 2) * Math.PI - Math.pow(array[i - 1], 2) * Math.PI;
            s+=v;
            System.out.println( "a["+i+"]的面积："+Math.pow(array[i], 2) * Math.PI+"-"+ "a["+(i-1)+"]的面积："+ Math.pow(array[i-1], 2) * Math.PI+"的值为："+v+"\ts的值："+s);

            if ((i-2)==0){
                s+=Math.pow(array[0], 2) * Math.PI;
            }
        }
        //四舍五入的两种方式：
        DecimalFormat format = new DecimalFormat("#.00000");
        double v = new BigDecimal(s).setScale(5, RoundingMode.UP).doubleValue();
        return Double.parseDouble(format.format(s));
    }

    public static int[] insertIntoArray(int n) {
        int[] array = new int[n];
        Random r = new Random(n);
        array[0] = 0;
        //循环遍历
        for (int i = 0; i < n; i++) {
            array[i] = r.nextInt(n) + 1;
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]) {
                    i--;
                    break;
                }
            }
        }
        return array;
    }

    public static void MaxPerson(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int sum=0,max=0,i=0;
        while(i<n && scanner.hasNext()){
            int down=scanner.nextInt();
            int up=scanner.nextInt();
            sum+=up-down;
            if(max<sum) max=sum;
            i++;
        }
        System.out.println(max);
    }

    public static void appleCount(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        if(n>9 || n<1){
            System.out.println("只能有9个熊，请重输---");
        }

        //假设第N个熊有1个苹果
        int sum=1;
        for(int i=0;i<n;i++){
            sum=sum*n;
        }
        System.out.println(sum-n+1);
    }


}
