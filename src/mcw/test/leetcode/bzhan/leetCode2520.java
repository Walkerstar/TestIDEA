package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数 num ，返回 num 中能整除 num 的数位的数目。
 * <p>
 * 如果满足 nums % val == 0 ，则认为整数 val 可以整除 nums 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 7
 * 输出：1
 * 解释：7 被自己整除，因此答案是 1 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：num = 121
 * 输出：2
 * 解释：121 可以被 1 整除，但无法被 2 整除。由于 1 出现两次，所以返回 2 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：num = 1248
 * 输出：4
 * 解释：1248 可以被它每一位上的数字整除，因此答案是 4 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 10^9
 * num 的数位中不含 0
 *
 * @author MCW 2023/10/26
 */
public class leetCode2520 {

    /**
     * 方法一：模拟
     * 思路
     * <p>
     * 根据题目要求，从低位到高位，依次判断除 nums 的余数是否为 0。统计所有余数为 0 的次数后返回。
     */
    public int countDigits(int num) {
        int t = num;
        int res = 0;
        while (t != 0) {
            if (num % (t % 10) == 0) {
                res++;
            }
            t /= 10;
        }
        return res;
    }
}
