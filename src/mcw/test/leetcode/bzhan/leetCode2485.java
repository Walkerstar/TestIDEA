package mcw.test.leetcode.bzhan;

/**
 * 给你一个正整数 n ，找出满足下述条件的 中枢整数 x ：
 * <p>
 * 1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
 * 返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。
 * <p>
 * 示例 1：
 * 输入：n = 8
 * 输出：6
 * 解释：6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 是中枢整数，因为 1 = 1 。
 * <p>
 * 示例 3：
 * 输入：n = 4
 * 输出：-1
 * 解释：可以证明不存在满足题目要求的整数。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1000
 *
 * @author MCW 2023/6/26
 */
public class leetCode2485 {

    /**
     * 方法一：数学
     * 思路与算法
     * <p>
     * 记从正整数 x 加到正整数 y ≥ x 的和为 sum(x,y) = x + (x+1) + ⋯ + y
     * <p>
     * 由「等差数列求和公式」得
     * sum(x,y)= ( (x+y) × (y−x+1) )/ 2
     * <p>
     * 则题目等价于给定一个正整数 n，判断是否存在正整数 x 满足  sum(1,x) = sum(x,n)
     * 进一步将上式化简得到
     * x= √ ( (n^2 + n) / 2 )
     * <p>
     * 若 x 不为整数则返回 −1，否则得到「中枢整数」  x。
     */
    public int pivotInteger(int n) {
        int t = (n * n + n) / 2;
        int x = (int) Math.sqrt(t);
        if (x * x == t) {
            return x;
        }
        return -1;
    }
}
