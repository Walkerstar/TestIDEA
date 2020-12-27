package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\4 0004-15:15
 * Minimum Path Sum
 * given a ( m x n ) grid filed with non-negative numbers.find a path form top
 * left to bottom right which minizes the sum of all numbers along its path.
 */
public class leetCode64 {
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // dp[i][j]: min Sum form (0,0) to  (i,j)
        int[][] dp = new int[m][n];
        //init
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        //function: dp[i][j]=min(dp[i][j-1],dp[i-1][j])+grid[i][j]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
