package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\6 0006-13:48
 * serach in rotated sorted array (在旋转排序数组中搜索)
 */
public class leetCode33 {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        //当两个指针相邻时，跳出循环
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && nums[mid] >= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[end]) {
                if (nums[end] >= target && nums[mid] <= target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}
