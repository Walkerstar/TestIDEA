package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，和两个整数 limit 与 goal 。数组 nums 有一条重要属性：abs(nums[i]) <= limit 。
 * <p>
 * 返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。
 * <p>
 * 注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
 * <p>
 * 提示：
 *
 * <li>1 <= nums.length <= 105</li>
 * <li>1 <= limit <= 106</li>
 * <li>-limit <= nums[i] <= limit</li>
 * <li>-109 <= goal <= 109</li>
 *
 * @author mcw 2022/12/16 15:44
 */
public class leetCode1785 {

    /**
     * 方法一：上取整
     * 思路与算法
     * <p>
     * 我们用 sum 表示 nums 中所有元素的和，用 diff=∣sum−goal∣ 表示「当前总和」与「目标总和」的差距。
     * <p>
     * 由于添加的元素所需要满足的范围是关于 0 对称的，所以当 sum > goal 时添加负数的情况可以被当做 sum < goal 时添加正数来处理。
     * <p>
     * 接下来计算需要使用多少个不超过 limit 的数字来凑齐 diff，分两种情况：
     * <p>
     * 若 limit 整除 diff，答案是 ⌊ diff / limit ⌋。
     * 若 limit 不整除 diff，答案是 ⌊ diff / limit ⌋ + 1。
     * 以上两种情况可以使用一个表达式来计算：⌊ (diff+limit−1) / limit ⌋。
     */
    public int minElements(int[] nums, int limit, int goal) {
        long sum = Arrays.stream(nums).sum();
        long diff = Math.abs(sum - goal);
        return (int) ((diff + limit - 1) / limit);
    }

}
