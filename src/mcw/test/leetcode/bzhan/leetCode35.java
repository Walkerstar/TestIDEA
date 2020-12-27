package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\11 0011-13:59
 * search insert position
 * 给定一个排序数组和一个目标值，如果找到，返回 target的索引，否则，返回 target 应该插入的位置索引
 * {1,3,5,6} 2-->1
 */
public class leetCode35 {
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                return mid;
            }
        }
        if (nums[end] < target) {
            return end + 1;
        } else if (nums[start] >= target) {
            return start;
        } else {
            return end;
        }
    }
}
