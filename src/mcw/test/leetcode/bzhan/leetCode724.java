package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * <p>
 * 数组 中心索引 是数组的一个索引，其左侧所有元素相加的和等于右侧所有元素相加的和。<br>
 * 如果数组不存在中心索引，返回 -1 。如果数组有多个中心索引，应该返回最靠近左边的那一个。<br>
 * 注意：中心索引可能出现在数组的两端。<p>
 * 示例：
 * 输入：nums = [0, 0, 0, 0, 1]
 * 输出：4
 * 解释： 索引 4 左侧数之和为 0 ；右侧不存在元素，视作和为 0 ，二者相等。
 *
 * @author mcw 2021/1/28 19:43
 */
public class leetCode724 {

    /**
     * 记数组的全部元素之和为 total，当遍历到第 i 个元素时，设其左侧元素之和为 sum，则其右侧元素之和为 total−nums−sum。
     * 左右侧元素相等即为 sum=total−nums[i]−sum，即 2*sum+nums[i]=total。
     * <p>
     * 当中心索引左侧或右侧没有元素时，即为零个项相加，这在数学上称作「空和」(empty sum)。在程序设计中我们约定「空和是零」。
     * @param nums 数组
     * @return 中心索引
     */
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
