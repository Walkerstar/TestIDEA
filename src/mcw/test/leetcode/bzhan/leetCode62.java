package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\4 0004-14:49
 * Unique Paths
 * 只可以向下或向右移动，无障碍物
 */
public class leetCode62 {
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //initial
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //dp[i][j]=dp[i-1][j]+dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
