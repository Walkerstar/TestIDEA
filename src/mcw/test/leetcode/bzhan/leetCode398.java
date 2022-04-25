package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * <p>
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 *
 * @author mcw 2022/4/25 10:04
 */
public class leetCode398 {
    /**
     * 哈希表
     */
    class Solution {
        Map<Integer, List<Integer>> pos;
        Random random;

        public Solution(int[] nums) {
            pos = new HashMap<>();
            random = new Random();
            for (int i = 0; i < nums.length; i++) {
                pos.putIfAbsent(nums[i], new ArrayList<>());
                pos.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> indices = pos.get(target);
            return indices.get(random.nextInt(indices.size()));
        }
    }

    /**
     * 水塘抽样
     */
    class Solution1 {
        int[] nums;
        Random random;

        public Solution1(int[] nums) {
            this.random = new Random();
            this.nums = nums;
        }

        public int pick(int target) {
            int ans = 0;
            for (int i = 0, cnt = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    //第 cnt 次遇到target
                    ++cnt;
                    if (random.nextInt(cnt) == 0) {
                        ans = i;
                    }
                }
            }
            return ans;
        }
    }
}
