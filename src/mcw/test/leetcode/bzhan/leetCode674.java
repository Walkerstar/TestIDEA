package mcw.test.leetcode.bzhan;

/**
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *<P></P>
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 * @author mcw 2021/1/24 18:47
 */
public class leetCode674 {

    /**
     * 令 start 表示连续递增序列的开始下标，初始时 start=0，然后遍历数组 nums，进行如下操作。
     *<p></p>
     * 如果下标 i>0 且 nums[i]≤nums[i−1]，则说明当前元素小于或等于上一个元素，因此 nums[i−1] 和 nums[i] 不可能属于同一个连续递增序列，
     * 必须从下标 i 处开始一个新的连续递增序列，因此令 start=i。如果下标 i=0 或 nums[i]>nums[i−1]，则不更新 start 的值。
     *<p></p>
     * 此时下标范围 [start,i] 的连续子序列是递增序列，其长度为 i−start+1，使用当前连续递增序列的长度更新最长连续递增序列的长度。
     *
     */
    public int findLengthOfLCIS1(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    public int findLengthOfLCIS(int[] nums){
        if (nums.length==0){
            return 0;
        }
        int ans = 1, ret = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                ret++;
            } else {
                ret = 1;
            }
            ans = Math.max(ans, ret);
        }
        return ans;
    }
}
