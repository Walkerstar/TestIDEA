package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
 * 使得从 1 到 n 的 min(ai, bi) 总和最大。   返回该 最大总和 。
 *
 * @author mcw 2021/2/16 20:37
 */
public class leetCode561 {
    public int arrayPairSum(int[] nums){
        Arrays.sort(nums);
        int ans=0;
        for (int i = 0; i < nums.length; i+=2) {
            ans+=nums[i];
        }
        return ans;
    }
}
