package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\2 0002-20:47
 * Jump Game I
 * given an array of non-negative integers.you are initially positioned at the first index of the array.
 * each element in the array represents your maximum jump length at the position.
 * detemine if you are able to reach the last index.
 */
public class leetCode55 {
    public static boolean canJump(int[] nums) {
        if (nums.length < 2) return true;
        int reach = 0, i = 0;
        for (i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(nums[i] + i, reach);
            if (reach >= nums.length - 1) return true;
        }
        return false;
    }
}
