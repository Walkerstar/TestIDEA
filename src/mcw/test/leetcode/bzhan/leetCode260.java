package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 *
 * @author MCW 2023/10/16
 */
public class leetCode260 {

    /**
     * 方法一：哈希表
     * 思路与算法
     * <p>
     * 我们可以使用一个哈希映射统计数组中每一个元素出现的次数。
     * 在统计完成后，我们对哈希映射进行遍历，将所有只出现了一次的数放入答案中。
     */
    public int[] singleNumber(int[] nums) {
        var freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }

    /**
     * 方法二：位运算
     * 思路与算法
     * <p>
     * 在理解如何使用位运算解决本题前，读者需要首先掌握「136. 只出现一次的数字」中的位运算做法。
     * <p>
     * 这样一来，我们就可以把 nums 中的所有元素分成两类，其中一类包含所有二进制表示的第 l 位为 0 的数，另一类包含所有二进制表示的第 l 位为 1 的数。可以发现：
     * <p>
     * 对于任意一个在数组 nums 中出现两次的元素，该元素的两次出现会被包含在同一类中；
     * <p>
     * 对于任意一个在数组 nums 中只出现了一次的元素，即 x1 和 x2 ，它们会被包含在不同类中。
     * <p>
     * 因此，如果我们将每一类的元素全部异或起来，那么其中一类会得到 x1 ，另一类会得到 x2。这样我们就找出了这两个只出现一次的元素。
     */
    public int[] singleNumbers(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }
}
