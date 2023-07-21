package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * <p>
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * <p>
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * <p>
 * 示例 2：
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * <p>
 * 示例 3：
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 * @author MCW 2023/7/20
 */
public class leetCode918 {

    /**
     * 方法一
     * 动态规划
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        // 对坐标为 0 处的元素单独处理，避免考虑子数组为空的情况
        leftMax[0] = nums[0];
        int leftSum = nums[0];
        int pre = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }
        // 从右到左枚举后缀，固定后缀，选择最大前缀
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMax[i - 1]);
        }
        return res;
    }

    /**
     * 方法二
     * 取反
     * 思路与算法
     * <p>
     * 对于第二种情况，即环形数组的最大子数组和为  nums[0:i] 和 nums[j:n]，我们可以找到普通数组最小的子数组  nums[i:j] 即可。
     * 而求解普通数组最小子数组和的方法与求解最大子数组和的方法完全相同。
     * <p>
     * 令  maxRes 是普通数组的最大子数组和， minRes 是普通数组的最小子数组和，我们可以将  maxRes 与
     * n
     * ∑   nums[i]−minRes 取最大作为答案。
     * i=0
     * <p>
     * 需要注意的是，如果 maxRes < 0，数组中不包含大于等于 0 的元素， minRes 将包括数组中的所有元素，导致我们实际取到的子数组为空。
     * 在这种情况下，我们只能取 maxRes 作为答案。
     */
    public int maxSubarraySumCircular2(int[] nums) {
        int n = nums.length;
        int preMax = nums[0], maxRes = nums[0];
        int preMin = nums[0], minRes = nums[0];
        int sum = nums[0];
        for (int i = 0; i < n; i++) {
            preMax = Math.max(preMax + nums[i], nums[i]);
            maxRes = Math.max(maxRes, preMax);
            preMin = Math.min(preMin + nums[i], nums[i]);
            minRes = Math.min(minRes, preMin);
            sum += nums[i];
        }
        if (maxRes < 0) {
            return maxRes;
        } else {
            return Math.max(maxRes, sum - minRes);
        }
    }

    /**
     * 方法三
     * 单调队列
     */
    public int maxSubarraySumCircular3(int[] nums) {
        int n = nums.length;
        Deque<int[]> queue = new ArrayDeque<>();
        int pre = nums[0], res = nums[0];
        queue.offerLast(new int[]{0, pre});
        for (int i = 1; i < 2 * n; i++) {
            while (!queue.isEmpty() && queue.peekFirst()[0] < i - n) {
                queue.pollFirst();
            }
            pre += nums[i % n];
            res = Math.max(res, pre - queue.peekFirst()[1]);
            while (!queue.isEmpty() && queue.peekFirst()[1] >= pre) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{i, pre});
        }
        return res;
    }
}
