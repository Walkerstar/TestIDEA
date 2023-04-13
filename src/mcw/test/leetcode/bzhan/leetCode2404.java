package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，返回出现最频繁的偶数元素。
 * <p>
 * 如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,2,4,4,1]
 * 输出：2
 * 解释：
 * 数组中的偶数元素为 0、2 和 4 ，在这些元素中，2 和 4 出现次数最多。
 * 返回最小的那个，即返回 2 。
 * 示例 2：
 * <p>
 * 输入：nums = [4,4,4,9,2,4]
 * 输出：4
 * 解释：4 是出现最频繁的偶数元素。
 * 示例 3：
 * <p>
 * 输入：nums = [29,47,21,41,13,37,25,7]
 * 输出：-1
 * 解释：不存在偶数元素。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2000
 * 0 <= nums[i] <= 10^5
 *
 * @author mcw 2023/4/13 10:39
 */
public class leetCode2404 {

    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
        }
        //当前遍历的元素
        int res = -1;

        //当前元素出现的次数
        int ct = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (res == -1 || entry.getValue() > ct || entry.getValue() == ct && res > entry.getKey()) {
                res = entry.getKey();
                ct = entry.getValue();
            }
        }
        return res;
    }

}
