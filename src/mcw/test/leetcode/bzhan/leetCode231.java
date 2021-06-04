package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 *
 * @author mcw 2021/5/30 10:33
 */
public class leetCode231 {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
//        return n > 0 && (n & -n) == n;
    }

    /**
     * 方法二：判断是否为最大 2 的幂的约数
     * <p>
     * 在题目给定的 3232 位有符号整数的范围内，最大的 22 的幂为 2^{30} = 10737418242。我们只需要判断 n 是否是 2^{30}的约数即可。
     */
    static final int BIG = 1 << 30;

    public boolean isPowerOfTwo1(int n) {
        return n > 0 && BIG % n == 0;
    }

}
