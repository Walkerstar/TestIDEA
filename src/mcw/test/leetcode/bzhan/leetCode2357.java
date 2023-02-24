package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个非负整数数组 nums 。在一步操作中，你必须：
 * <p>
 * 选出一个正整数 x ，x 需要小于或等于 nums 中 最小 的 非零 元素。
 * nums 中的每个正整数都减去 x。
 * 返回使 nums 中所有元素都等于 0 需要的 最少 操作数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,0,3,5]
 * 输出：3
 * 解释：
 * 第一步操作：选出 x = 1 ，之后 nums = [0,4,0,2,4] 。
 * 第二步操作：选出 x = 2 ，之后 nums = [0,2,0,0,2] 。
 * 第三步操作：选出 x = 2 ，之后 nums = [0,0,0,0,0] 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * @author mcw 2023/2/24 11:28
 */
public class leetCode2357 {

    /**
     * 首先将数组 nums 按升序排序，然后从左到右遍历排序后的数组 nums。
     * 对于每个遍历到的非零元素，该元素是数组中的最小非零元素，将该元素记为 x，将数组中的每个非零元素都减 x，将操作数加 1。
     * 遍历结束之后，即可得到最少操作数。
     */
    public int minimumOperations(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                subtract(nums, nums[i], i);
                ans++;
            }
        }
        return ans;
    }

    public void subtract(int[] nums, int x, int startIndex) {
        int length = nums.length;
        for (int i = startIndex; i < length; i++) {
            nums[i] -= x;
        }
    }

    /**
     * 哈希集合
     */
    public int minimumOperations2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        return set.size();
    }
}
