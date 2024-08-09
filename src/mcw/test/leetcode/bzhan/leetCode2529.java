package mcw.test.leetcode.bzhan;

/**
 * 2529. 正整数和负整数的最大计数
 * <p>
 * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
 * <p>
 * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
 * 注意：0 既不是正整数也不是负整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,-1,-1,1,2,3]
 * 输出：3
 * 解释：共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-3,-2,-1,0,0,1,2]
 * 输出：3
 * 解释：共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [5,20,66,1314]
 * 输出：4
 * 解释：共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2000
 * -2000 <= nums[i] <= 2000
 * nums 按 非递减顺序 排列。
 * <p>
 * 进阶：你可以设计并实现时间复杂度为 O(log(n)) 的算法解决此问题吗？
 *
 * @author MCW 2024/4/9
 */
public class leetCode2529 {

    /**
     * 方法一：遍历
     * 思路与算法
     * <p>
     * 遍历整个数组，用两个变量分别统计正数和负数的个数，最后返回较大值即可。
     */
    public int maximumCount(int[] nums) {
        int pos = 0, neg = 0;
        for (int num : nums) {
            if (num > 0) {
                pos++;
            } else if (num < 0) {
                neg++;
            }
        }
        return Math.max(pos, neg);
    }

    /**
     * 方法二：二分查找
     * 思路与算法
     * <p>
     * 由于数组呈现非递减顺序，因此可通过二分查找定位第一个数值大于等于 0 的位置 pos1 及第一个数值大于等于 1 的下标 pos2。
     * 假定 n 表示数组长度，且数组下标从 0，则负数的个数为 pos1，正数的个数为 n−pos2，返回这两者的较大值即可。
     * <p>
     * 二分的实现思路可以参考题目「34. 在排序数组中查找元素的第一个和最后一个位置」的题解。
     */
    public int maximumCount2(int[] nums) {
        int pos1 = lowerBound(nums, 0);
        int pos2 = lowerBound(nums, 1);

        return Math.max(pos1, nums.length - pos2);
    }

    public int lowerBound(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] >= val) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
