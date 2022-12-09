package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
 * <p>
 * 对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。
 *
 * @author mcw 2022/12/9 15:11
 */
public class leetCode1780 {

    /**
     * 我们可以将 n 转换成 3 进制。如果 n 的 3 进制表示中每一位均不为 2，那么答案为 True，否则为 False。
     * <p>
     * 例如当 n=12 时，12 = (110)_3，满足要求；当 n=21 时，21 = (210)_3，不满足要求。
     *
     * 如果一个数 n 可以表示成若干个“不同的”三的幂之和, 意味着 3^x 不能相同，即不能出现 3^2 +3^2 这样类似的幂。所以 转换为3进制后，不能出现 2
     */
    public static boolean checkPowersOfThree(int n) {
        while (n != 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkPowersOfThree(12));
    }
}
