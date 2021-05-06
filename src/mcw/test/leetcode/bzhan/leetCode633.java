package mcw.test.leetcode.bzhan;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 * @author mcw 2021\4\28 0028-15:39
 */
public class leetCode633 {

    /**
     * 方法一：使用 sqrt 函数
     * 在枚举 a 的同时，使用 sqrt 函数找出 bb。注意：本题 c 的取值范围在 [0,2^31−1]，因此在计算的过程中可能会发生 int 型
     * 溢出的情况，需要使用 long 型避免溢出。
     */
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }


    /**
     * 方法二：双指针
     * 不失一般性，可以假设 a≤b。初始时 a=0，b= √c（庚号），进行如下操作：
     * <p>
     * 如果 a^2 + b^2 = c，我们找到了题目要求的一个解，返回 true；
     * 如果 a^2 + b^2 < c，此时需要将 a 的值加 1，继续查找；
     * 如果 a^2 + b^2 > c，此时需要将 b 的值减 1，继续查找。
     * <p>
     * 当 a = b 时，结束查找，此时如果仍然没有找到整数 a 和 b 满足 a^2 + b^2 = c，则说明不存在题目要求的解，返回 false。
     */
    public boolean judgeSquareSum1(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    /**
     * 方法三：数学
     * 费马平方和定理告诉我们：
     * 一个非负整数 c 如果能够表示为两个整数的平方和，当且仅当 c 的所有形如 4k + 3 的质因子的幂均为偶数。
     */
    public boolean judgeSquareSum2(int c) {
        for (int base = 2; base * base <= c; base++) {
            //如果不是因子，枚举下一个
            if (c % base != 0) {
                continue;
            }
            //计算 base 的幂
            int exp = 0;
            while (c % base == 0) {
                c /= base;
                exp++;
            }

            //根据 Sum of two squares theorem 验证
            if (base % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }
        //例如 11 这样的用例，由于上面的 for 循环里 base * base <= c,
        //base == 11 的时候不会进入循环体,因此在退出循环以后需要再做一次判断
        return c % 4 != 3;
    }
}
