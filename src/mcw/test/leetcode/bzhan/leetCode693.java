package mcw.test.leetcode.bzhan;

/**
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * @author mcw 2022/3/28 19:57
 */
public class leetCode693 {

    /**
     * 从最低位至最高位，我们用对 2 取模再除以 2 的方法，依次求出输入的二进制表示的每一位，并与前一位进行比较。
     */
    public boolean hasAlternatingBits(int n) {
        int prev = 2;
        while (n != 0) {
            int cur = n % 2;
            if (cur == prev) {
                return false;
            }
            prev = cur;
            n /= 2;
        }
        return true;
    }

    public boolean hasAlternatingBits2(int n) {
        //当且仅当输入 n 为交替位二进制数时，a 的二进制表示全为 1（不包括前导 0）
        int a = n ^ (n >> 1);
        //将 a 与 a + 1按位与，当且仅当 a 的二进制表示全为 1 时，结果为 0
        return (a & (a + 1)) == 0;
    }
}
