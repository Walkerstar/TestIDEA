package mcw.test.leetcode.bzhan;

/**
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 *
 * @author mcw 2021/12/31 14:47
 */
public class leetCode507 {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int d = 2; d * d <= num; ++d) {
            if (num % d == 0) {
                sum += d;
                //加上另外一个因子
                if (d * d < num) {
                    sum += num / d;
                }
            }
        }
        return sum == num;
    }

    /**
     * 根据欧几里得-欧拉定理，每个偶完全数都可以写成 2^(p-1)*(2^p-1) 的形式，
     * 其中 p 为素数且 (2^p-1) 为素数。
     * 由于目前奇完全数还未被发现，因此题目范围 [1,10^8] 内的完全数都可以写成上述形式。
     * 这一共有如下 5 个：
     * 6, 28, 496, 8128, 33550336
     */
    public boolean checkPerfectNumber2(int num) {
        return num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336;
    }

}
