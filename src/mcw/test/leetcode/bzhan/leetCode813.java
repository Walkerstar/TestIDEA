package mcw.test.leetcode.bzhan;

/**
 * 给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。
 * <p>
 * 注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
 * <p>
 * 返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。
 * <p>
 * 提示:
 * <li>1 <= nums.length <= 100</li>
 * <li>1 <= nums[i] <= 10^4</li>
 * <li>1 <= k <= nums.length</li>
 *
 * @author mcw 2022/11/28 14:45
 */
public class leetCode813 {

    /**
     * 动态规划
     *
     * dp[i][j] 表示 nums 在区间 [0,i−1] 被切分成 j 个子数组的最大平均值和，
     *
     * 假设数组 nums 的长度为 n，那么 dp[n][k] 表示数组 nums 分成 k 个子数组后的最大平均值和，即最大分数。
     *
     */
    public static double largestSumOfAverage(int[] nums, int k) {
        int n = nums.length;
        //前缀和
        double[] prefix = new double[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        //二维数组
        double[][] dp = new double[n + 1][k + 1];
        //子数组个数为 1 时，每个数组的平均值
        for (int i = 1; i <= n; i++) {
            dp[i][1] = prefix[i] / i;
        }
        //子数组个数
        for (int i = 2; i <= k; i++) {
            for (int j = i; j <= n; j++) {
                for (int x = i - 1; x < j; x++) {
                    dp[j][i] = Math.max(dp[j][i], dp[x][i - 1] + (prefix[j] - prefix[x]) / (j - x));
                }
            }
        }
        return dp[n][k];

        //或者一维数组
        //double[] dp = new double[n + 1];
        //for (int i = 1; i <= n; i++) {
        //    dp[i] = prefix[i] / i;
        //}
        //for (int j = 2; j <= k; j++) {
        //    for (int i = n; i >= j; i--) {
        //        for (int x = j - 1; x < i; x++) {
        //            dp[i] = Math.max(dp[i], dp[x] + (prefix[i] - prefix[x]) / (i - x));
        //        }
        //    }
        //}
        //return dp[n];
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6,7};
        System.out.println(largestSumOfAverage(arr, 4));
    }
}
