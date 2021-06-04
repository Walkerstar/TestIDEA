package mcw.test.leetcode.bzhan;

/**
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
 *
 * @author mcw 2021/5/31 10:15
 */
public class leetCode342 {

    /**
     * 方法一：二进制表示中 1 的位置<br>
     * 如果 n 是 4 的幂，那么 n 的二进制表示中有且仅有一个 1，并且这个 1 出现在从低位开始的第偶数个二进制位上（这是因为这个 1 后面必须有偶数个 0）
     * 因此我们可以构造一个整数 mask，它的所有偶数二进制位都是 0，所有奇数二进制位都是 1。这样一来，我们将 n 和 mask 进行按位与运算，
     * 如果结果为 0，说明 n 二进制表示中的 1 出现在偶数的位置，否则说明其出现在奇数的位置。
     * mask 的二进制表示为：mask = (10101010101010101010101010101010)_2
     * mask 的16 进制的形式，使其更加美观：mask = (AAAAAAAA)_16
     */
    public boolean isPoewrofFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & (0xaaaaaaaa)) == 0;
    }


    /**
     * 方法二：取模性质<br>
     * 通过 n 除以 3 的余数是否为 1 来判断 n 是否是 4 的幂
     */
    public boolean isPowerOfFour1(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }
}
