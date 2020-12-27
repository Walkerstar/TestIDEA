package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\2 0002-21:00
 * Jump Game II
 * given an array of non-negative integers.you are initially positioned at the first index of the array.
 * each element in the array represents your maxmimum jump length at the position.
 * your goal is to reach the last index in the minimum number of jump.
 *
 * fro example;
 * A={2,3,1,1,4}
 * the mimimum number of jump to reach the last index is 2.(jump 1 step form index 0 to 1,then 3 steps to the last index)
 */
public class leetCode45 {
    public static int jump(int[] nums) {
        if ( nums == null || nums.length <= 1 ) return 0;
        int currMax = 0;
        int nextMax = 0;
        int step = 0;
        int index = 0;
        while (index <= currMax) {
            while (index <= currMax) {
                nextMax = Math.max(nextMax, index + nums[index]);
                index++;
            }
            currMax = nextMax;
            step++;
            if (currMax >= nums.length - 1) return step;
        }
        return 0;
    }

}
