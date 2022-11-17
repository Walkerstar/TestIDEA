package mcw.test.leetcode.bzhan;

/**
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 * <p>
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 *
 * <li>0 <= i < j < n</li>
 * <li>nums[i] > nums[j]</li>
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 *
 * <li>0 <= i < n - 1</li>
 * <li>nums[i] > nums[i + 1]</li>
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 *
 * @author mcw 2022/11/16 15:36
 */
public class leetCode775 {


    /**
     * 方法一：维护后缀最小值
     * <p>
     * 一个局部倒置一定是一个全局倒置，因此要判断数组中局部倒置的数量是否与全局倒置的数量相等，只需要检查有没有非局部倒置就可以了。
     * 这里的非局部倒置指的是 nums[i] > nums[j]，其中 i < j−1。
     */
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        int minSuffix = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > minSuffix) {
                return false;
            }
            minSuffix = Math.min(minSuffix, nums[i + 1]);
        }
        return true;
    }

    /**
     * 方法二：归纳证明
     * 思路与算法
     * <p>
     * nums 是一个由 0∼n−1 组成的排列，设不存在非局部倒置的排列为「理想排列」。
     * 由于非局部倒置表示存在一个 j>i+1 使得 nums[i]>nums[j] 成立，所以对于最小的元素 0 来说，它的下标不能够大于等于 2。所以有：
     * <p>
     * 若 nums[0]=0，那么问题转换为 [1,n−1] 区间的一个子问题。
     * 若 nums[1]=0，那么 nums[0] 只能为 1，因为如果是大于 1 的任何元素，都将会与后面的 1 构成非局部倒置。此时，问题转换为了 [2,n−1] 区间的一个子问题。
     * 根据上述讨论，不难归纳出「理想排列」的充分必要条件为对于每个元素 nums[i] 都满足 ∣nums[i]−i∣ ≤ 1。
     */
    public boolean isIdealPermutation2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }
}
