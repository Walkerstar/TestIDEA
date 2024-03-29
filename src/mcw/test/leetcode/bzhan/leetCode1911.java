package mcw.test.leetcode.bzhan;

/**
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 * <p>
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 * <p>
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 * <p>
 * 示例 1：
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 * <p>
 * 示例 2：
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 * <p>
 * 示例 3：
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * @author MCW 2023/7/11
 */
public class leetCode1911 {

    /**
     * 思路与算法
     * <p>
     * 我们将题目给出大小为 n 的数组 nums。现在我们需要返回 nums 中任意子序列的「最大交替和」，\
     * 其中「最大交替和」定义为该序列偶数下标元素和减去奇数下标元素和（序列的下标从 0 开始）。
     * <p>
     * 设 dp[i][0] 和 dp[i][1] 分别为在 nums 的前缀 nums[0],nums[1],…,nums[i] 中选择一个子序列，
     * 并且选择的子序列的最后一个元素的下标为偶数和奇数的「最大交替和」。现在我们来思考如何进行状态转移。
     * <p>
     * 对于 dp[i][0] 为以下两者中的较大值：
     * 若 nums[i] 被选择： dp[i][0]=dp[i−1][1]+nums[i]。
     * 否则： dp[i][0]=dp[i−1][0]。
     * <p>
     * 对于 dp[i][1] 为以下两者中的较大值：
     * 若 nums[i] 被选择： dp[i][1]=dp[i−1][0]−nums[i]。
     * 否则： dp[i][0]=dp[i−1][1]。
     * <p>
     * 以上的讨论都在 i>0 的基础上，当 i=0 时：dp[0][0]=nums[0]， dp[0][1]=0。
     * 最终我们返回 dp[n−1][0] 和 dp[n−1][1] 中的较大值即可，又因为可以分析得到「最大交替和」一定不会为 dp[n−1][1]，
     * 因为最终选择的序列最后一个元素一定不可能位于奇数下标（因为奇数下标对应着减去该元素的值，我们可以不选择该元素）。
     * 所以最终返回 dp[n−1][0] 即可。
     * <p>
     * 因为 dp[i] 的求解只与  dp[i−1] 有关，所以在实现的过程中我们可以通过「滚动数组」的方式来进行空间优化。
     */
    public long maxAlternatingSum(int[] nums) {
        long even = nums[0], odd = 0;
        for (int i = 1; i < nums.length; i++) {
            even = Math.max(even, odd + nums[i]);
            odd = Math.max(odd, even - nums[i]);
        }
        return even;
    }
}
