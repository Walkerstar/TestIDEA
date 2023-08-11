package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 * <p>
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 * <p>
 * abs(x) 定义如下：
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * <p>
 * 示例 2：
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 * @author MCW 2023/8/8
 */
public class leetCode1749 {

    /**
     * 方法一：动态规划 + 分情况讨论
     * 思路
     * <p>
     * 一个变量绝对值的最大值，可能是这个变量的最大值的绝对值，也可能是这个变量的最小值的绝对值。
     * 题目要求任意子数组和的绝对值的最大值，可以分别求出子数组和的最大值 positiveMax 和子数组和的最小值 negativeMin，
     * 因为子数组可以为空，所以 positiveMax >= 0 ，negativeMin <= 0 。
     * 最后返回 max(positiveMax,−negativeMin) 即为任意子数组和的绝对值的最大值。
     * <p>
     * 而求子数组和的最大值，可以参照「53. 最大子数组和」的解法，运用动态规划求解。
     * 而求子数组和的最小值，也是类似的思路，遍历时记录全局最小值 negativeMin 和当前子数组负数和并更新，遍历完即可得到子数组和的最小值。
     */
    public int maxAbsoluteSum(int[] nums) {
        int positiveMax = 0, negativeMin = 0;
        int positiveSum = 0, negativeSum = 0;
        for (int num : nums) {
            positiveSum += num;
            positiveMax = Math.max(positiveMax, positiveSum);
            positiveSum = Math.max(0, positiveMax);

            negativeSum += num;
            negativeMin = Math.min(negativeMin, negativeSum);
            negativeSum = Math.min(0, negativeSum);
        }
        return Math.max(positiveMax, -negativeMin);
    }
}
