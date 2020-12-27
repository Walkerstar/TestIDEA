package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * Search for a range(搜索范围)
 * 给定一组排好序的数组，找出目标值的索引范围,没有就返回{-1,-1}？
 * {5,7,7,8,8,10}  返回： {3,4}
 *
 * @author mcw 2020\5\6 0006-21:36
 */
public class leetCode34 {

    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) return res;
        int st_point = -1, end_point = -1;
        int start = 0, end = nums.length - 1;
        //find the start point
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            st_point = start;
        } else if (nums[end] == target) {
            st_point = end;
        }
        if (st_point == -1) return res;

        start = 0;
        end = nums.length - 1;
        //find the end point
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) {
            end_point = end;
        } else if (nums[start] == target) {
            end_point = start;
        }
        res[0] = st_point;
        res[1] = end_point;
        return res;
    }
    
}
