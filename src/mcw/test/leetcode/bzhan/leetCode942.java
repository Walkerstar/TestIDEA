package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 * <p>
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 *
 * @author mcw 2022/5/9 10:58
 */
public class leetCode942 {

    /**
     * 如果 s[1]=‘I’，那么令 perm[1] 为剩余数字中的最小数；
     * 如果 s[1]=‘D’，那么令 perm[1] 为剩余数字中的最大数。
     * 如此循环直至剩下一个数，填入 perm[n] 中。
     */
    public static int[] diStringMatch(String s) {
        int n = s.length();
        //min,max为当前剩余数字中的最小数和最大数
        int min = 0, max = n;
        int[] prem = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prem[i] = s.charAt(i) == 'I' ? min++ : max--;
        }
        prem[n] = min;
        return prem;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(diStringMatch("IDIDDDD")));
    }
}
