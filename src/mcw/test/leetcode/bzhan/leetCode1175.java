package mcw.test.leetcode.bzhan;

/**
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 * <p>
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * <p>
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 *
 * @author mcw 2022/6/30 14:25
 */
public class leetCode1175 {
    static final int MOD = 1000000007;

    /**
     * 方法一：质数判断 + 组合数学
     * 思路
     * <p>
     * 求符合条件的方案数，使得所有质数都放在质数索引上，所有合数放在合数索引上，质数放置和合数放置是相互独立的，
     * 总的方案数即为「所有质数都放在质数索引上的方案数」×「所有合数都放在合数索引上的方案数」。
     * 求「所有质数都放在质数索引上的方案数」，即求质数个数 numPrimes 的阶乘。「所有合数都放在合数索引上的方案数」同理。
     * 求质数个数时，可以使用试除法。最后注意计算过程中需要对 10^9+7 取模。
     */
    public int numPrimeArrangements(int n) {
        int numPrimes = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                numPrimes++;
            }
        }
        return (int) (factorial(numPrimes) * factorial(n - numPrimes) % MOD);
    }

    public boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public long factorial(int n) {
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            res %= MOD;
        }
        return res;
    }
}
