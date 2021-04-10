package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * @author mcw 2021\4\10 0010-20:03
 */
public class leetCode263 {

    /**
     * 对 n 反复除以 2,3,5，直到 n 不再包含质因数 2,3,5。若剩下的数等于 1，则说明 n 不包含其他质因数，是丑数；
     * 否则，说明 n 包含其他质因数，不是丑数
     */
    public boolean isUgly(int n) {
        if (n < 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
