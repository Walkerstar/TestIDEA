package mcw.test.leetcode.bzhan;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 例：输入: [1, 5, 11, 5]    输出: true      解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * @author mcw 2020/10/11 11:55
 */
public class leetCode416 {
    /**
     * 动态规划（参考0-1背包问题）
     * @param nums 非空数组
     * @return 判断能否分成两个子集且元素和相等
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        //计算数组的元素和，判断数组能否分割，同时找出最大的元素
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        //如果是元素和是奇数，返回false
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        /*for (int num : nums) {
            if (target + 1 - num >= 0){
                System.arraycopy(dp, 0, dp, num, target + 1 - num);
            }
        }*/
        return dp[target];
    }
}
