package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\30 0030-16:29
 * Search in Rotated Sorted Array II
 * 33题变种
 */
public class leetCode81 {
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0;
        int end = nums.length - 1;
        //当两个指针相邻时，跳出循环
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;

            if (nums[mid] > nums[start]) {
                if (nums[mid] >= target && nums[start] <= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]) {
                if (nums[mid] <= target && nums[end] >= target) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else start++;
        }
        return nums[start] == target || nums[end] == target;
    }
}
