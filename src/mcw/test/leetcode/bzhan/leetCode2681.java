package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个下标从 0  开始的整数数组  nums  ，它表示英雄的能力值。如果我们选出一部分英雄，这组英雄的 力量  定义为：
 * <p>
 * i0  ，i1  ，... ik  表示这组英雄在数组中的下标。
 * 那么这组英雄的力量为  max(nums[i0],nums[i1] ... nums[ik])^2 * min(nums[i0],nums[i1] ... nums[ik]) 。
 * 请你返回所有可能的 非空 英雄组的 力量 之和。
 * <p>
 * 由于答案可能非常大，请你将结果对  10^9 + 7  取余。
 * <p>
 * 示例 1：
 * 输入：nums = [2,1,4]
 * 输出：141
 * 解释：
 * 第 1  组：[2] 的力量为 2^2  * 2 = 8 。
 * 第 2  组：[1] 的力量为 1^2 * 1 = 1 。
 * 第 3  组：[4] 的力量为 4^2 * 4 = 64 。
 * 第 4  组：[2,1] 的力量为 2^2 * 1 = 4 。
 * 第 5  组：[2,4] 的力量为 4^2 * 2 = 32 。
 * 第 6  组：[1,4] 的力量为 4^2 * 1 = 16 。
 * 第 7  组：[2,1,4] 的力量为 4^2 * 1 = 16 。
 * 所有英雄组的力量之和为 8 + 1 + 64 + 4 + 32 + 16 + 16 = 141 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：7
 * 解释：总共有 7 个英雄组，每一组的力量都是 1 。所以所有英雄组的力量之和为 7 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * @author MCW 2023/8/1
 */
public class leetCode2681 {


    /**
     * 动态规划 + 前缀和
     */
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        // dp[j] 表示以 nums[j] 结尾的子序列的最小值之和
        int[] dp = new int[nums.length];
        int[] preSum = new int[nums.length + 1];
        int res = 0, mod = 10 ^ 9 + 7;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = (nums[i] + preSum[i]) % mod;
            preSum[i + 1] = (preSum[i] + dp[i]) % mod;
            res = (int) ((res + (long) nums[i] * nums[i] % mod * dp[i]) % mod);
            if (res < 0) {
                res += mod;
            }
        }
        return res;
    }

    /**
     * 优化版
     */
    public int sumOfPower2(int[] nums) {
        Arrays.sort(nums);
        int dp = 0;
        int preSum = 0;
        int res = 0, mod = 10 ^ 9 + 7;
        for (int i = 0; i < nums.length; i++) {
            dp = (nums[i] + preSum) % mod;
            preSum = (preSum + dp) % mod;
            res = (int) ((res + (long) nums[i] * nums[i] % mod * dp) * mod);
            if (res < 0) {
                res += mod;
            }
        }
        return res;
    }

    /**
     * from ylb
     */
    public int sumOfPower3(int[] nums) {
        final int mod = (int) 1e9 + 7;
        Arrays.sort(nums);
        long ans = 0, p = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            long x = nums[i];
            ans = (ans + (x * x % mod) * x) % mod;
            ans = (ans + x * p % mod) % mod;
            p = (p * 2 + x * x % mod) % mod;
        }
        return (int) ans;
    }
}
