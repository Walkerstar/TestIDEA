package mcw.test.leetcode.bzhan;

/**
 * 2575. 找出字符串的可整除数组
 * <p>
 * 给你一个下标从 0 开始的字符串 word ，长度为 n ，由从 0 到 9 的数字组成。另给你一个正整数 m 。
 * <p>
 * word 的 可整除数组 div  是一个长度为 n 的整数数组，并满足：
 * <p>
 * 如果 word[0,...,i] 所表示的 数值 能被 m 整除，div[i] = 1
 * 否则，div[i] = 0
 * 返回 word 的可整除数组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：word = "998244353", m = 3
 * 输出：[1,1,0,0,0,1,1,0,0]
 * 解释：仅有 4 个前缀可以被 3 整除："9"、"99"、"998244" 和 "9982443" 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：word = "1010", m = 10
 * 输出：[0,1,0,1]
 * 解释：仅有 2 个前缀可以被 10 整除："10" 和 "1010" 。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * word.length == n
 * word 由数字 0 到 9 组成
 * 1 <= m <= 10^9
 *
 * @author MCW 2024/3/7
 */
public class leetCode2575 {


    /**
     * 方法一：模运算
     * 思路与算法
     * <p>
     * 一个整数可表示为 a×10+b：
     * <p>
     * ( a × 10 + b ) mod m = ( a mod m × 10 + b ) mod m
     * <p>
     * 所以我们可以按照上面的递推式，根据当前表示整数的余数，算出包含下一位字符所表示的整数的余数。
     * <p>
     * 当余数为零时即为可整除数组，否则不是。最后返回结果即可
     */
    public int[] divisibilityArray(String word, int m) {
        int[] res = new int[word.length()];
        long cur = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur = (cur * 10 + (c - '0')) % m;
            res[i] = cur == 0 ? 1 : 0;
        }
        return res;
    }
}
