package mcw.test.leetcode.bzhan;

/**
 * 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
 * 如果当前数字为偶数，则将其除以 2 。
 * 如果当前数字为奇数，则将其加上 1 。
 * @author mcw 2020/10/18 14:30
 */
public class leetCode1404 {

    /**
     *     bit    Carry  sumResult  Operation
     * 一   1      1       10        >>,with new_carry=0
     * 二   1      0       01        +1,go #1(equivalent to situation 1 that bit =1 and carry=1)
     * 三   0      1       01        +1,go #1(equivalent to situation 1 that bit =1 and carry=1)
     * 四   0      0       00        >>,with new_carry=0
     *
     * 第一第四情况下的操作为：1. 右移一位 2.carry值不变  3.操作数+1
     * 第二第三情况下的操作为：1. 右移一位 2.carry变 1   3. 操作数+2（自己+1一次，接着情况一+1一次）
     * 当我们从后向前遍历s中当字符时，仅需要关注第三第四情况。
     * 还有一个特殊情况为，当遍历到首位的时候，如果此刻carry为0，就需要停止任何操作，break循环
     */
    public int numSteps(String s) {
        if (s.length() == 0) return 0;
        int ans = 0;
        int carry = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i == 0 && carry == 0) break;
            if (s.charAt(i) - '0' != carry) {
                carry = 1;
                ans += 1;
            }
            ans += 1;
        }
        return ans;
    }
}