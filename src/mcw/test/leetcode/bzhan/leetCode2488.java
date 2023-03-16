package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。另给你一个正整数 k 。
 * <p>
 * 统计并返回 nums 中的 中位数 等于 k 的非空子数组的数目。
 * <p>
 * 注意：
 * <p>
 * 数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
 * 例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。
 * 子数组是数组中的一个连续部分。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,2,1,4,5], k = 4
 * 输出：3
 * 解释：中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,3,1], k = 3
 * 输出：1
 * 解释：[3] 是唯一一个中位数等于 3 的子数组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i], k <= n
 * nums 中的整数互不相同
 *
 * @author mcw 2023/3/16 10:53
 */
public class leetCode2488 {
    /**
     * 前缀和
     * 由于数组 nums 的长度是 n，数组由从 1 到 n 的不同整数组成，因此数组中的元素各不相同，满足 1 ≤ k ≤ n 的正整数 k 在数组中恰好出现一次。
     * <p>
     * 用 kIndex 表示正整数 k 在数组 nums 中的下标。根据中位数的定义，中位数等于 k 的非空子数组应满足以下条件：
     * <p>
     * 1.子数组的开始下标小于等于 kIndex 且结束下标大于等于 kIndex；
     * 2.子数组中的大于 k 的元素个数与小于 k 的元素个数之差为 0 或 1。
     * <p>
     * 为了计算每个子数组中的大于 k 的元素个数与小于 k 的元素个数之差，需要将原始数组做转换，
     * 将大于 k 的元素转换成 1，小于 k 的元素转换成 −1，等于 k 的元素转换成 0，
     * 转换后的数组中，每个子数组的元素和为对应的原始子数组中的大于 k 的元素个数与小于 k 的元素个数之差。
     * <p>
     * 为了在转换后的数组中寻找符合要求的子数组，可以计算转换后的数组的前缀和，根据前缀和寻找符合要求的子数组。
     * 规定空前缀的前缀和是 0 且对应下标 −1。
     * 如果存在下标 left 和 right 满足 −1 ≤ left < kIndex ≤ right < n 且下标 right 处的前缀和与下标 left 处的前缀和之差为 0 或 1，
     * 则等价于下标范围 [left+1,right] 包含下标 kIndex 且该下标范围的转换后的子数组的元素和为 0 或 1，对应该下标范围的原始子数组的中位数等于 k。
     * <p>
     * 根据上述分析，可以从左到右遍历数组 nums，遍历过程中计算转换后的数组的前缀和，并计算中位数等于 k 的非空子数组的数目。
     * <p>
     * 使用哈希表记录转换后的数组的每个前缀和的出现次数。由于空前缀的前缀和是 0，因此首先将前缀和 0 与次数 1 存入哈希表。
     * <p>
     * 用 sum 表示转换后的数组的前缀和，遍历过程中维护 sum 的值。对于 0 ≤ i < n，当遍历到下标 i 时，更新 sum 的值，然后执行如下操作：
     * <p>
     * 1.如果 i < kIndex，则下标 i+1 可以作为中位数等于 k 的非空子数组的开始下标，将 sum 在哈希表中的出现次数加 1；
     * 2.如果 i ≥ kIndex，则下标 i 可以作为中位数等于 k 的非空子数组的结束下标，从哈希表中获得前缀和 sum 的出现次数 prev0
     * 与前缀和 sum−1 的出现次数 prev1，则以下标 i 作为结束下标且中位数等于 k 的非空子数组的数目是 prev0+prev1 ，将答案增加 prev0+prev1
     */
    public int countSubArrays(int[] nums, int k) {
        int n = nums.length;
        int kIndex = -1;
        //因为只出现一次，所以找到后，就可返回
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                kIndex = i;
                break;
            }
        }

        int ans = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(0, 1);

        //转换后数组的前缀和
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sign(nums[i] - k);
            if (i < kIndex) {
                counts.put(sum, counts.getOrDefault(sum, 0) + 1);
            } else {
                int prev0 = counts.getOrDefault(sum, 0);
                int prev1 = counts.getOrDefault(sum - 1, 0);
                ans += prev0 + prev1;
            }
        }
        return ans;
    }

    public int sign(int num) {
        if (num == 0) {
            return 0;
        }
        return num > 0 ? 1 : -1;
    }
}
