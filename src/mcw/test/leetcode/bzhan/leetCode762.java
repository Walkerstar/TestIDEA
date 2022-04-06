package mcw.test.leetcode.bzhan;

/**
 * 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
 * <p>
 * 计算置位位数 就是二进制表示中 1 的个数。
 *
 * @author MCW 2022/4/5
 */
public class leetCode762 {

    /**
     * 方法一 : 数学 + 位运算
     */
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int x = left; x <= right; x++) {
            if (isPrime(Integer.bitCount(x))) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean isPrime(int x) {
        if (x <= 2) {
            return false;
        }
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二：判断质数优化
     * 注意到 right ≤ 10^6 < 2^20 ，因此二进制中 1 的个数不会超过 19，而不超过 19 的质数只有
     * 2, 3, 5, 7, 11, 13, 17, 19
     * 我们可以用一个二进制数 mask=665772=10100010100010101100 来存储这些质数，其中 mask 二进制的
     * 从低到高的第 i 位为 1 表示 i 是质数，为 0 表示 i 不是质数。
     * <p>
     * 设整数 x 的二进制中 1 的个数为 c，若 mask 按位与 2^c 不为 0，则说明 c 是一个质数。
     */
    public int countPrimeSetBits2(int left, int right) {
        int ans = 0;
        for (int x = left; x <= right; x++) {
            if (((1 << Integer.bitCount(x)) & 665772) != 0) {
                ++ans;
            }
        }
        return ans;
    }
}
