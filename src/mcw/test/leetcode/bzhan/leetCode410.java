package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 410. 分割数组的最大值
 * <p>
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组。
 * <p>
 * 设计一个算法使得这 k 个子数组各自和的最大值最小。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [7,2,5,10,8], k = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。
 * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：9
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1,4,4], k = 3
 * 输出：4
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 *
 * @author MCW 2024/1/21
 */
public class leetCode410 {

    /**
     * 动态规划
     */
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }

        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }

    /**
     * 方法二：二分查找 + 贪心
     * 思路及算法
     * <p>
     * 「使……最大值尽可能小」是二分搜索题目常见的问法。
     * <p>
     * 本题中，我们注意到：当我们选定一个值 x，我们可以线性地验证是否存在一种分割方案，满足其最大分割子数组和不超过 x。策略如下：
     * <p>
     * 贪心地模拟分割的过程，从前到后遍历数组，用 sum 表示当前分割子数组的和，cnt 表示已经分割出的子数组的数量（包括当前子数组），
     * 那么每当 sum 加上当前值超过了 x，我们就把当前取的值作为新的一段分割子数组的开头，并将 cnt 加 1。遍历结束后验证是否 cnt 不超过 m。
     * <p>
     * 这样我们可以用二分查找来解决。二分的上界为数组 nums 中所有元素的和，下界为数组 nums 中所有元素的最大值。
     * 通过二分查找，我们可以得到最小的最大分割子数组和，这样就可以得到最终的答案了。
     */
    public int splitArray2(int[] nums, int m) {
        // 左边界初始为数组中最大的数
        // 右边界初始为数组之和
        int left = 0, right = 0;
        for (int num : nums) {
            right += num;
            if (left < num) {
                left = num;
            }
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int num : nums) {
            if (sum + num > x) {
                cnt++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return cnt <= m;
    }
}
