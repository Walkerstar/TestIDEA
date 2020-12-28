package com.atguigu.java;

import java.util.*;

/**
 * @author mcw 2020\3\26 0026-14:18
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            long n = scanner.nextInt();
            long m = scanner.nextInt();
            long k = scanner.nextInt();

            long left = 0;
            long right =m*n;
            long mid = right/2;

            while(left<=right){
                mid = (left+right)/2;

                if(calSum(mid,n,m)<k){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
            System.out.println(left);
            break;
        }
    }

    public static long calSum(long k, long n, long m) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (k >= m * i) ? m : k / i;
        }
        return sum;
    }
}

