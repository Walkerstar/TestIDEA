package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
 * 请你找出所有出现 两次 的整数，并以数组形式返回。
 * <p>
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 *
 * @author MCW 2022/5/8
 */
public class leetCode442 {

    /**
     * 方法一：将元素交换到对应的位置
     * 思路与算法
     * <p>
     * 由于给定的 n 个数都在 [1, n] 的范围内，如果有数字出现了两次，就意味着 [1,n] 中有数字没有出现过。
     * <p>
     * 因此，我们可以尝试将每一个数放在对应的位置。由于数组的下标范围是 [0, n-1]，我们需要将数 i 放在数组中下标为 i-1 的位置：
     * <li>如果 i 恰好出现了一次，那么将 i 放在数组中下标为 i-1的位置即可；</li>
     * <li>如果 i 出现了两次，那么我们希望其中的一个 i 放在数组下标中为 i-1 的位置，另一个 i 放置在任意「不冲突」的位置 j。也就是说，数 j+1 没有在数组中出现过。</li>
     * 这样一来，如果我们按照上述的规则放置每一个数，那么我们只需要对数组进行一次遍历。当遍历到位置 i 时，如果 nums[i]−1 ≠ i，
     * 说明 nums[i] 出现了两次（另一次出现在位置 num[i]−1），我们就可以将 num[i] 放入答案。
     */
    public static List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        // 我们对数组进行一次遍历。当遍历到位置 i 时，我们知道 nums[i] 应该被放在位置 nums[i]−1。
        // 因此我们交换 num[i] 和 nums[nums[i]−1] 即可，直到待交换的两个元素相等为止。
        for (int i = 0; i < n; i++) {
            //如果当前值没出现在应该出现的索引的位置，则继续交换
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 != i) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }

    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    /**
     * 方法二：使用正负号作为标记
     * 思路与算法
     * <p>
     * 我们也可以给 nums[i] 加上「负号」表示数 i+1 已经出现过一次。具体地，我们首先对数组进行一次遍历。
     * 当遍历到位置 i 时，我们考虑 nums[nums[i]−1] 的正负性：
     *
     * <li>如果 nums[nums[i]−1] 是正数，说明 nums[i] 还没有出现过，我们将 nums[nums[i]−1] 加上负号；</li>
     * <li>如果 nums[nums[i]−1] 是负数，说明 nums[i] 已经出现过一次，我们将 nums[i] 放入答案。</li>
     * <p>
     * 细节
     * <p>
     * 由于 nums[i] 本身可能已经为负数，因此在将 nums[i] 作为下标或者放入答案时，需要取绝对值。
     * <p>
     * 假设 nums，按从小到大的顺序排列 且每个数字只出现一次，那么 nums[i] - i = 1；
     * 遍历数组时，如果当前数值是 nums[i], 可得 nums[i] - 1 = j, 此时将nums[j]置为负数,标记 索引 j 出现一次，
     * 如果 后续 nums[j] 的值 小于 0 ，则说明 nums[i] 已出现过一次。
     * <p>
     * 输入的数组: 4,3,2,7,8,2,3,1
     * <p>
     * 假想的数组: 1,2,3,4,5,6,7,8
     *             2 3
     *    index: 0,1,2,3,4,5,6,7
     */
    public static List<Integer> findDuplicates2(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]);
            if (nums[x - 1] > 0) {
                nums[x - 1] = -nums[x - 1];
            } else {
                ans.add(x);
            }
        }
        return ans;
    }

    public static List<Integer> findDuplicates3(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            if (visited[nums[i]]) {
                ans.add(nums[i]);
            } else {
                visited[nums[i]] = true;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDuplicates3(nums));
    }
}
