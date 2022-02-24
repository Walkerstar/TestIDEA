package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 *
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
 *
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 * @author MCW 2022/2/22
 */
public class leetCode1994 {

    static final int[] PRIMES={2,3,5,7,11,13,17,19,23,29};
    static final int NUM_MAX=30;
    static final int MOD=1000000007;

    public int numberOfGoodSubsets(int[] nums) {
        int[] freq = new int[NUM_MAX + 1];
        for (int num : nums) {
            ++freq[num];
        }

        int[] f = new int[1 << PRIMES.length];
        f[0] = 1;
        for (int i = 0; i < freq[1]; i++) {
            f[0] = f[0] * 2 % MOD;
        }
        for (int i = 2; i <= NUM_MAX; i++) {
            if (freq[i] == 0) {
                continue;
            }
            //检查 i 的 每个质因数是否不超过 1 个
            int subset = 0, x = i;
            boolean check = true;
            for (int j = 0; j < PRIMES.length; j++) {
                int prime = PRIMES[j];
                if (x % (prime * prime) == 0) {
                    check = false;
                    break;
                }
            }
            if (!check) {
                continue;
            }
            //动态规划
            for (int mask = (1 << PRIMES.length) - 1; mask > 0; --mask) {
                if ((mask & subset) == subset) {
                    f[mask] = (int) ((f[mask] + (long) f[mask ^ subset]) * freq[i] % MOD);
                }
            }
        }
        int ans = 0;
        for (int mask = 1, maskMax = (1 << PRIMES.length); mask < maskMax; mask++) {
            ans = (ans + f[mask]) % MOD;
        }
        return ans;
    }
}
