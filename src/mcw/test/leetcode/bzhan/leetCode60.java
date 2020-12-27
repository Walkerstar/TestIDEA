package mcw.test.leetcode.bzhan;

import java.util.ArrayList;

/**
 * @author mcw 2020\6\3 0003-15:51
 * Permutation Sequence( 排列顺序 )
 * 给定 包含 N 个数字的集合，在按循序的所有排列中，返回 第 K 个 数字
 * 例如：{1,2,3} ，他一共有6个排列：{123,132,213,231,312,321}, 返回 第 4 个，即输出 231
 */
public class leetCode60 {

    public static String getPermutation(int n, int k) {
        char[] result = new char[n];
        ArrayList<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n];
        factorial[0]=1;

        //求阶乘的结果，找出每一组对应的有多少个数字
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        //构建可用数字的 list
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        //把第几个数字变成坐标
        k--;

        //从最高位 一位一位的确定数字
        for (int i = 0; i < n; i++) {
            result[i] = Character.forDigit(nums.remove(k / factorial[n - 1 - i]), 10);
            k = k % factorial[n - 1 - i];
        }
        return new String(result);
    }
}

