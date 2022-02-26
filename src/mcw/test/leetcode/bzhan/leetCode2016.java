package mcw.test.leetcode.bzhan;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，
 * 其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 *
 * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 * @author MCW 2022/2/26
 */
public class leetCode2016 {

    /**
     * 方法一：前缀最小值<br/>
     * 当我们固定 j 时，选择的下标 i 一定是满足 0 ≤ i < j 并且 nums[i] 最小的那个 i。因此我们可以使用循环对 j 进行遍历，
     * 同时维护 nums[0..j−1] 的前缀最小值，记为 preMin。这样一来：
     * <p>
     * 如果 nums[i]>premin，那么就用 nums[i]−preMin 对答案进行更新；
     * <p>
     * 否则，用 nums[i] 来更新前缀最小值 preMin。
     */
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int ans = -1, preMin = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > preMin) {
                ans = Math.max(ans, nums[i] - preMin);
            } else {
                preMin = nums[i];
            }
        }
        return ans;
    }
}
