package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10^n 。
 *
 * @author MCW 2022/4/11
 */
public class leetCode357 {

    /**
     * 排列组合
     * C(9,1) * A(9,n-1)
     * <p>
     * 首先考虑两种边界情况：
     * 当 n = 0 时，0 ≤ x < 1，x 只有 1 种选择，即 0。
     * 当 n = 1 时，0 ≤ x < 10，x 有 10 种选择，即 0∼9。
     * 当 n = 2 时，0 ≤ x < 100，x 的选择可以由两部分构成：只有一位数的 x 和有两位数的 x。
     * <p>
     * 只有一位数的 x 可以由上述的边界情况计算。
     * 有两位数的 x 可以由组合数学进行计算：第一位的选择有 9 种，即 1∼9，第二位的选择也有 9 种，即 0∼9 中除去第一位的选择。
     * <p>
     * 更一般地，含有 d( 2 ≤ d ≤ 10 )位数的各位数字都不同的数字 x 的个数可以由公式 9×A(9,d-1)计算。
     * 再加上含有小于 d 位数的各位数字都不同的数字 x 的个数，即可得到答案。
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }

    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
        }
        return dp[n];
    }
}
