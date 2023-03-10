package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
 * <p>
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
 * <p>
 * 子数组 定义为原数组中连续的一组元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1,4,2], p = 6
 * 输出：1
 * 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [6,3,5,2], p = 9
 * 输出：2
 * 解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3], p = 3
 * 输出：0
 * 解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
 * 示例  4：
 * <p>
 * 输入：nums = [1,2,3], p = 7
 * 输出：-1
 * 解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
 * 示例 5：
 * <p>
 * 输入：nums = [1000000000,1000000000,1000000000], p = 3
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= p <= 109
 *
 * @author mcw 2023/3/10 11:24
 */
public class leetCode1590 {

    /**
     * 前缀和
     */
    public int minSubarray(int[] nums, int p) {
        int x = 0;
        // 求 数组和 对 p 的余数
        for (int num : nums) {
            x = (x + num) % p;
        }
        if (x == 0) {
            return 0;
        }
        Map<Integer, Integer> index = new HashMap<>();
        int y = 0, res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            // f[i] mod p = y ,因此哈希表记录 y 对应的下标为 i
            // 记录 前缀和 对 p 的余数，以及 以nums[i] 结尾的前缀和的 下标位置 i
            index.put(y, i);
            y = (y + nums[i]) % p;
            //有些编程语言对负数进行取余时，余数为负数，因此计算 f(i+1) − x 除以 p 的余数时，使用 f(i+1) − x + p 替代。
            //如果 余数 已经出现过一次，则说明 该余数的上一次索引到现在的索引之间的子数组是可删除的，此时记录最小数组的长度
            if (index.containsKey((y - x + p) % p)) {
                res = Math.min(res, i - index.get((y - x + p) % p) + 1);
            }
        }
        return res == nums.length ? -1 : res;
    }
}
