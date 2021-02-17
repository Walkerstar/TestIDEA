package mcw.test.leetcode.bzhan;

/**
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 * @author mcw 2021/2/15 20:40
 */
public class leetCode485 {

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
}
