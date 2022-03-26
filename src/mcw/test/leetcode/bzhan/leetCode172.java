package mcw.test.leetcode.bzhan;

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * @author mcw 2022/3/25 18:41
 */
public class leetCode172 {

    /**
     * n! 尾零的数量即为 n! 中因子 10 的个数，而 10=2×5，因此转换成求 n! 中质因子 2 的个数和质因子 5 的个数的较小值。
     * 由于质因子 5 的个数不会大于质因子 2 的个数（具体证明见方法二），我们可以仅考虑质因子 5 的个数。
     * 而 n! 中质因子 5 的个数等于 [1,n] 的每个数的质因子 5 的个数之和，我们可以通过遍历 [1,n] 的所有 5 的倍数求出。
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 实际上就是计算1-n之中有多少个5的因数。以130为例：
     * 第一次除以5时得到26，表明存在26个包含 [一] 个因数5的数；
     * 第二次除以5得到5，表明存在5个包含 [二] 个因数5的数(这些数字的一个因数5已经在第一次运算的时候统计了)；
     * 第三次除以5得到1，表明存在1个包含 [三] 个因数5的数(这些数字的两个因数5已经在前两次运算的时候统计了)；
     * 得到从1-n中所有5的因数的个数
     */
    public int trailingZeroes2(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new leetCode172().trailingZeroes2(130));
    }
}
