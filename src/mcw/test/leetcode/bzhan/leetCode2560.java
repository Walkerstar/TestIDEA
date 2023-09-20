package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 * <p>
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
 * <p>
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 * <p>
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 * <p>
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
 * <p>
 * 返回小偷的 最小 窃取能力。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,5,9], k = 2
 * 输出：5
 * 解释：
 * 小偷窃取至少 2 间房屋，共有 3 种方式：
 * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
 * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
 * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
 * 因此，返回 min(5, 9, 9) = 5 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,7,9,3,1], k = 2
 * 输出：2
 * 解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= (nums.length + 1)/2
 *
 * @author MCW 2023/9/19
 */
public class leetCode2560 {

    /**
     * 方法一：二分查找
     * 题目需要获取窃取至少 k 间房屋时小偷的最小窃取能力，属于常见的最小化最大值问题。
     * 假设小偷偷取的房屋的最大金额为 y，显然 y ∈ [nums_min,nums_max]。记 f(y) 为在最大金额 y 的限制下，小偷可以偷取的最大房屋数目，
     * <p>
     * f(y) 的计算方式为：
     * <p>
     * 记当前偷取的房屋数目为 count，遍历数组 nums，假设当前遍历的房屋的金额为 x，如果 x ≤ y 成立，且上一遍历的房屋没有被偷取，
     * 那么令偷取的房屋数目 count 加 1，表示该房屋被偷取。 遍历结束后 f(y)=count，显然 f(y) 是非递减函数。
     * <p>
     * 那么我们可以使用二分查找的方法，找到满足 f(y) ≥ k的最小 y，即题目所求的小偷最小窃取能力。具体二分查找算法如下：
     * <p>
     * 1. 初始时 lower = nums_min ，upper = nums_max
     * 2. 令 middle=⌊ (lower + upper) / 2 ⌋，如果 f(middle) ≥ k，那么 upper = middle − 1；否则 lower = middle + 1。
     * 3. 当 lower ≤ upper 时，继续执行步骤 2；否则返回 lower 为结果。
     */
    public int minCapability(int[] nums, int k) {
        int lower = Arrays.stream(nums).min().getAsInt();
        int upper = Arrays.stream(nums).max().getAsInt();
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            int count = 0;
            boolean visited = false;
            for (int x : nums) {
                // visited 保证不能盗窃相邻的房间
                if (x <= middle && !visited) {
                    count++;
                    visited = true;
                } else {
                    visited = false;
                }
            }
            if (count >= k) {
                upper = middle - 1;
            } else {
                lower = middle - 1;
            }
        }
        return lower;
    }

}
