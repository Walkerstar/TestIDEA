package mcw.test.leetcode.bzhan;

/**
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 *
 * @author MCW 2022/4/16
 */
public class leetCode479 {

    /**
     * 我们可以从大到小枚举回文数，由于确定了回文数的左半部分，其右半部分也就确定了，因此我们只需要枚举左半部分，
     * 同时由于两个 n 位整数的乘积至多是个 2n 位数，我们可以从 10^n - 1开始枚举回文数的左半部分。
     * <p>
     * 得到回文数 p 后，需要判断其能否分解成两个 n 位整数。
     * 我们可以从 10^n - 1 开始从大到小枚举 x，若 x 能整除 p 且 x 和 p/x 均为 n 位整数，则 p 就是我们要找的答案。
     * <p>
     * 代码实现时，在枚举 xx 时枚举到 ⌈ √p⌉ 即可，因为继续枚举的话有 x<(p/x)，
     * 若 x 为 p 的因子则说明更大的 p/x 也是 p 的因子，但是前面枚举 x 的过程中并没有找到 p 的因子，矛盾。
     * <p>
     * 实际结果表明，上述算法在 n>1 时总能找到答案，而 n=1 时的答案为 9，是个 1 位数，需要特判这种情况。
     */
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int upper = (int) Math.pow(10, n) - 1;
        int ans = 0;

        //枚举回文数的左半部分
        for (int left = upper; ans == 0; --left) {
            long p = left;
            //翻转左半部分到其自身末尾，构造回文数p
            for (int x = left; x > 0; x /= 10) {
                p = p * 10 + x % 10;
            }
            for (long x = upper; x * x >= p; --x) {
                // x 是 p 的因子
                if (p % x == 0) {
                    ans = (int) (p % 1337);
                    break;
                }
            }
        }
        return ans;
    }
}
