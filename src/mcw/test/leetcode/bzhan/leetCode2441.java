package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个 不包含 任何零的整数数组 nums ，找出自身与对应的负数都在数组中存在的最大正整数 k 。
 * <p>
 * 返回正整数 k ，如果不存在这样的整数，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,2,-3,3]
 * 输出：3
 * 解释：3 是数组中唯一一个满足题目要求的 k 。
 * 示例 2：
 * <p>
 * 输入：nums = [-1,10,6,7,-7,1]
 * 输出：7
 * 解释：数组中存在 1 和 7 对应的负数，7 的值更大。
 * 示例 3：
 * <p>
 * 输入：nums = [-10,8,6,7,-2,-3]
 * 输出：-1
 * 解释：不存在满足题目要求的 k ，返回 -1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 *
 * @author MCW 2023/5/13
 */
public class leetCode2441 {

    /**
     * 方法一：暴力枚举
     * 遍历整数数组  nums，使用整数  k 记录符合条件的最大整数，
     * 假设当前访问的元素为  x，如果 −x 存在于整数数组  nums 中，
     * 我们使用  x 更新 k。（不需要判断  x 的正负，因为一对相反数都会被用来更新  k）
     */
    public int findMaxK(int[] nums) {
        int k = -1;
        for (int x : nums) {
            for (int y : nums) {
                if (-x == y) {
                    k = Math.max(k, x);
                }
            }
        }
        return k;
    }

    /**
     * 方法二：哈希表
     * 使用哈希表保存数组  nums 的所有元素，遍历整数数组  nums，使用整数  k 记录符合条件的最大整数，
     * 假设当前访问的元素为  x，如果  −x 存在于哈希表中，我们使用  x 更新  k。（不需要判断  x 的正负，因为一对相反数都会被用来更新  k）
     */
    public int findMaxK2(int[] nums) {
        int k = -1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int x : nums) {
            if (set.contains(-x)) {
                k = Math.max(k, x);
            }
        }
        return k;
    }

    /**
     * 方法三：排序 + 双指针
     * 我们将数组  nums 从小到大进行排序，然后用指针  j 从大到小对数组  nums 进行遍历，
     * 同时用指针  i 从小到大查找值等于  −nums[j] 的元素。
     * 因为  −nums[j] 随  j 减小而增大，所以上一步获得的  i 值可以直接作为下一步的  i 值。
     */
    public int findMaxK3(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0, j = nums.length - 1; i < j; j--) {
            while (i < j && nums[i] < -nums[j]) {
                i++;
            }
            if (nums[i] == -nums[j]) {
                return nums[j];
            }
        }
        return -1;
    }
}
