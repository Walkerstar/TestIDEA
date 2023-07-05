package mcw.test.leetcode.bzhan;

/**
 * 袋子中装有一些物品，每个物品上都标记着数字 1 、0 或 -1 。
 * <p>
 * 给你四个非负整数 numOnes 、numZeros 、numNegOnes 和 k 。
 * <p>
 * 袋子最初包含：
 * <p>
 * numOnes 件标记为 1 的物品。
 * numZeroes 件标记为 0 的物品。
 * numNegOnes 件标记为 -1 的物品。
 * 现计划从这些物品中恰好选出 k 件物品。返回所有可行方案中，物品上所标记数字之和的最大值。
 * <p>
 * 示例 1：
 * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
 * 输出：2
 * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 2 件标记为 1 的物品，得到的数字之和为 2 。
 * 可以证明 2 是所有可行方案中的最大值。
 * <p>
 * 示例 2：
 * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
 * 输出：3
 * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 3 件标记为 1 的物品，1 件标记为 0 的物品，得到的数字之和为 3 。
 * 可以证明 3 是所有可行方案中的最大值。
 * <p>
 * 提示：
 * <p>
 * 0 <= numOnes, numZeros, numNegOnes <= 50
 * 0 <= k <= numOnes + numZeros + numNegOnes
 *
 * @author MCW 2023/7/5
 */
public class leetCode2600 {
    /**
     * 方法一：贪心
     * 题目的物品分为 1， 0 和 −1 三种，要想选出 k 件物品，使得和最大，那么贪心地选择前 k 大的物品是最优的。根据 k 的取值，有情况：
     * 1. k≤numOnes，可以全部选择 k 个 1，最大和为 k。
     * 2. numOnes<k≤numOnes+numZeros，可以选择全部的 1 和部分 0，最大和为 numOnes。
     * 3. numOnes+numZeros<k，可以选择全部的 1 和 0，选择部分的 −1（数目为 k−numOnes−numZeros），最大和为 numOnes−(k−numOnes−numZeros)。
     */
    public int KItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (k <= numOnes) {
            return k;
        } else if (k <= numOnes + numZeros) {
            return numOnes;
        } else {
            return numOnes - (k - numOnes - numZeros);
        }
    }
}
