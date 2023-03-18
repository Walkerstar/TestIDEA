package mcw.test.leetcode.bzhan;


import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 * <p>
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
 * <p>
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 10^6
 *
 * @author MCW 2023/3/18
 */
public class leetCode2389 {


    /**
     * 方法一：二分查找
     * 由题意可知，nums 的元素次序对结果无影响，因此我们对 nums 从小到大进行排序。显然和有限的最长子序列由最小的前几个数组成。
     * 使用数组 f 保存 nums 的前缀和，其中 f[i] 表示前 i 个元素之和（不包括 nums[i]）。遍历 queries，
     * 假设当前查询值为 q，使用二分查找获取满足 f[i] > q 的最小的 i，那么和小于等于 q 的最长子序列长度为 i−1。
     */
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i] + nums[i];
        }
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            answer[i] = BinarySearch(f, queries[i] - 1);
        }

        return answer;
    }

    public int BinarySearch(int[] f, int target) {
        int low = 1, high = f.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (f[mid] > target) {
                high = target;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


}
