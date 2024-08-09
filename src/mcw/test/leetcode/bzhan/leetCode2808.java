package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 2808. 使循环数组所有元素相等的最少秒数
 * <p>
 * 给你一个下标从 0 开始长度为 n 的数组 nums 。
 * <p>
 * 每一秒，你可以对数组执行以下操作：
 * <p>
 * 对于范围在 [0, n - 1] 内的每一个下标 i ，将 nums[i] 替换成 nums[i] ，nums[(i - 1 + n) % n] 或者 nums[(i + 1) % n] 三者之一。
 * 注意，所有元素会被同时替换。
 * <p>
 * 请你返回将数组 nums 中所有元素变成相等元素所需要的 最少 秒数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,1,2]
 * 输出：1
 * 解释：我们可以在 1 秒内将数组变成相等元素：
 * - 第 1 秒，将每个位置的元素分别变为 [nums[3],nums[1],nums[3],nums[3]] 。变化后，nums = [2,2,2,2] 。
 * 1 秒是将数组变成相等元素所需要的最少秒数。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,1,3,3,2]
 * 输出：2
 * 解释：我们可以在 2 秒内将数组变成相等元素：
 * - 第 1 秒，将每个位置的元素分别变为 [nums[0],nums[2],nums[2],nums[2],nums[3]] 。变化后，nums = [2,3,3,3,3] 。
 * - 第 2 秒，将每个位置的元素分别变为 [nums[1],nums[1],nums[2],nums[3],nums[4]] 。变化后，nums = [3,3,3,3,3] 。
 * 2 秒是将数组变成相等元素所需要的最少秒数。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [5,5,5,5]
 * 输出：0
 * 解释：不需要执行任何操作，因为一开始数组中的元素已经全部相等。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * @author MCW 2024/1/30
 */
public class leetCode2808 {

    /**
     * 方法一：哈希表
     * 思路与算法
     * <p>
     * 我们首先用哈希表，统计 nums 中相同的数所出现的位置，mp[x] 表示 x 所出现的位置。
     * <p>
     * 然后我们研究，使得数组全部变为 x 所需要的时间，这个时间取决于 nums 中，相邻 x 的最大距离。
     * 我们依次枚举所有相邻（包括头尾）x 的索引值，找到最大的距离。最大距离除以二并向下取整，就是使得数组全部变为 x 所需要的时间。
     * <p>
     * 最后我们对所有 nums 中 的数，都找到所需的时间，返回其中的最小值即可。
     */
    public int minimumSeconds(List<Integer> nums) {
        var mp = new HashMap<Integer, List<Integer>>();
        int n = nums.size();
        int res = n;
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> positions : mp.values()) {
            int mx = positions.get(0) + n - positions.get(positions.size() - 1);
            for (int i = 1; i < positions.size(); i++) {
                mx = Math.max(mx, positions.get(i) - positions.get(i - 1));
            }
            res = Math.min(res, mx / 2);
        }
        return res;
    }

}
