package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 * <p>
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [4,4,2,4,3]
 * 输出：3
 * 解释：下面列出的三元组均满足题目条件：
 * - (0, 2, 4) 因为 4 != 2 != 3
 * - (1, 2, 4) 因为 4 != 2 != 3
 * - (2, 3, 4) 因为 2 != 4 != 3
 * 共计 3 个三元组，返回 3 。
 * 注意 (2, 0, 4) 不是有效的三元组，因为 2 > 0 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：不存在满足条件的三元组，所以返回 0 。
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 *
 * @author MCW 2023/6/13
 */
public class leetCode2475 {

    /**
     * 方法一：枚举
     * 记数组 nums 的大小为 n，使用三重循环，枚举所有 0 ≤ i < j < k < n 的三元组，
     * 如果三元组 (i,j,k) 满足 nums[i] ≠ nums[j]、  nums[i] ≠ nums[k] 且nums[j] ≠ nums[k]，
     * 那么将结果加  1，枚举结束后返回最终结果。
     */
    public int unequalTriplets(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] != nums[j] && nums[i] != nums[k] && nums[j] != nums[k]) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 方法二：排序
     * 由题意可知，数组元素的相对顺序不影响结果，因此我们可以将数组  nums 从小到大进行排序。
     * 排序后，数组中的相同元素一定是相邻的。
     * 当我们以某一堆相同的数 [i,j) 作为三元组的中间元素时，这堆相同的元素的左边元素数目为  i，右边元素数目为 n−j，
     * 那么符合条件的三元组数目为：
     * i × (j−i) × (n−j)
     * <p>
     * 对以上结果求和并返回最终结果。
     */
    public int unequalTriplets2(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && nums[j] == nums[i]) {
                j++;
            }
            res += i * (j - i) * (n - j);
        }
        return res;
    }

    /**
     * 方法三：哈希表
     * 类似于方法二，我们可以使用哈希表 count 记录各个元素的数目，然后遍历哈希表（此时数组元素按照哈希表的遍历顺序进行排列），
     * 记当前遍历的元素数目 v，先前遍历的元素总数目为 t，那么以当前遍历的元素为中间元素的符合条件的三元组数目为：
     * t × v × (n−t−v)
     * 对以上结果求和并返回最终结果。
     */
    public int unequalTriplets3(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }
        int res = 0, n = nums.length, t = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            res += t * entry.getValue() * (n - t - entry.getValue());
            t += entry.getValue();
        }
        return res;
    }
}
