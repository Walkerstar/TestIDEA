package mcw.test.leetcode.bzhan;

import mcw.test.offer.Test30;

/**
 * @author mcw 2020\6\3 0003-15:11
 * Maximum Subarray (求最大连续子序列和 )
 */
public class leetCode53 {
    public static int maxSubArray(int[] nums) {
        int maxToCurr = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxToCurr = Math.max(maxToCurr + nums[i], nums[i]);
            sum = Math.max(sum, maxToCurr);
        }
        return sum;
    }


    public static int maxValue(int[] array){
        int maxvalue=0;
        int sum=0;
        for (int value : array) {
            if (sum < 0) {
                sum = value;
            } else {
                sum += value;
            }
            maxvalue = Math.max(maxvalue, sum);
        }
        return maxvalue;
    }

}
