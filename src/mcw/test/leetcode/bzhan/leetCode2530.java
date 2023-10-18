package mcw.test.leetcode.bzhan;

import java.util.PriorityQueue;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你的 起始分数 为 0 。
 * <p>
 * 在一步 操作 中：
 * <p>
 * 选出一个满足 0 <= i < nums.length 的下标 i ，
 * 将你的 分数 增加 nums[i] ，并且
 * 将 nums[i] 替换为 ceil(nums[i] / 3) 。
 * 返回在 恰好 执行 k 次操作后，你可能获得的最大分数。
 * <p>
 * 向上取整函数 ceil(val) 的结果是大于或等于 val 的最小整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,10,10,10,10], k = 5
 * 输出：50
 * 解释：对数组中每个元素执行一次操作。最后分数是 10 + 10 + 10 + 10 + 10 = 50 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,10,3,3,3], k = 3
 * 输出：17
 * 解释：可以执行下述操作：
 * 第 1 步操作：选中 i = 1 ，nums 变为 [1,4,3,3,3] 。分数增加 10 。
 * 第 2 步操作：选中 i = 1 ，nums 变为 [1,2,3,3,3] 。分数增加 4 。
 * 第 3 步操作：选中 i = 2 ，nums 变为 [1,1,1,3,3] 。分数增加 3 。
 * 最后分数是 10 + 4 + 3 = 17 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length, k <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * @author MCW 2023/10/18
 */
public class leetCode2530 {
    /**
     * 方法一：贪心 + 优先队列
     * 思路与算法
     * <p>
     * 在一次操作中，我们会将 nums[i] 变成 ⌈nums[i] / 3⌉，并且增加 nums[i] 的得分。由于：
     * <p>
     * 数组中其它的元素不会变化；
     * 对于两个不同的元素 nums[i] 和 nums[j]，如果 nums[i]≤nums[j]，在对它们都进行一次操作后，nums[i]≤nums[j] 仍然成立；
     * 这就说明，我们每一次操作都应当贪心地选出当前最大的那个元素。
     * <p>
     * 因此，我们可以使用一个大根堆（优先队列）来维护数组中所有的元素。
     * 在每一次操作中，我们取出堆顶的元素 x，将答案增加 x，再将 ⌈x/3⌉ 放回大根堆中即可。
     * <p>
     * 细节
     * <p>
     * 为了避免浮点数运算，我们可以用 (x+2)/3 等价 ceil⌈ x/3 ⌉，其中 / 表示整数除法。
     */
    public long maxKelements(int[] nums, int k) {
        var q = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int num : nums) {
            q.offer(num);
        }
        long ans = 0;
        for (int i = 0; i < k; i++) {
            int x = q.poll();
            ans += x;
            q.offer((x + 2) / 3);
        }
        return ans;
    }
}
