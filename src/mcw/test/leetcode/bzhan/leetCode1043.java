package mcw.test.leetcode.bzhan;

/**
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * <p>
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：数组变为 [15,15,15,9,10,10,10]
 * 示例 2：
 * <p>
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * <p>
 * 输入：arr = [1], k = 1
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^9
 * 1 <= k <= arr.length
 *
 * @author mcw 2023/4/19 11:31
 */
public class leetCode1043 {
    /**
     * 方法一：动态规划
     * 思路与算法
     * <p>
     * 我们需要将 arr 分割成若干个子数组，每个子数组的长度都不超过 k。
     * 分割后每个元素都将变成其所属子数组中的最大值。现考虑如何使数组和最大。
     * <p>
     * 我们很难同时分割所有元素，如果能一次只考虑分割一组，然后利用之前分割得到的信息，任务就会变得简单。
     * 试想当前枚举到了 i，我们把 i 当做这一组的末尾，然后在 [i - k, i - 1] 的范围内枚举 j，[j + 1, i] 这一段可以当做新的一组。
     * 这时我们需要利用以 j 为结尾分割的最大和，可以发现如果将这个问题的答案提前计算并存储下来，以 i 为结尾的问题就可以迎刃而解。
     * <p>
     * 具体地，我们设 d[i] 为以 i 结尾分割的最大和，求解时倒序枚举 ( j ∈ [ i−k, i−1] )，那么转移方程有：
     * d[i] = max{ d[j] + maxValue × (i−j) }
     * 其中 maxValue = max{ arr[j+1],⋯,arr[i] }。
     * <p>
     * 答案为 d[n]，n是 arr 的长度。
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int maxValue = arr[i - 1];
            for (int j = i - 1; j >= 0 && j >= i - k; j--) {
                d[i] = Math.max(d[i], d[j] + maxValue * (i - 1));
                if (j > 0) {
                    maxValue = Math.max(maxValue, arr[j - 1]);
                }
            }
        }
        return d[n];
    }
}
