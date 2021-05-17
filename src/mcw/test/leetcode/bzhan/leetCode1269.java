package mcw.test.leetcode.bzhan;

/**
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * <p>
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * <p>
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * <p>
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 *
 * @author mcw 2021\5\13 0013-9:26
 */
public class leetCode1269 {

    public int numWays(int steps, int arrLen) {
        final int MODULO = 1000000007;

        //由于一共执行 steps 步操作，因此指针所在下标一定不会超过 steps，
        //可以将 j 的取值范围进一步缩小到 0≤j≤min(arrLen−1,steps)
        int maxColum = Math.min(arrLen - 1, steps);

        //dp[i][j] 表示在 i 步操作之后，指针位于下标 j 的方案数
        int[][] dp = new int[steps + 1][maxColum + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxColum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - 1 >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MODULO;
                }
                if (j + 1 <= maxColum) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MODULO;
                }
            }
        }
        return dp[steps][0];
    }


    public int numWays1(int steps, int arrLen) {
        final int MODULO = 1000000007;
        int maxColumn = Math.min(arrLen - 1, steps/2+1);
        int[] dp = new int[maxColumn + 1];
        dp[0] = 1;
        for (int i = 1; i <= steps; i++) {
            int[] dpNext = new int[maxColumn + 1];
            for (int j = 0; j <= maxColumn; j++) {
                dpNext[j] = dp[j];
                if (j - 1 >= 0) {
                    dpNext[j] = (dpNext[j] + dp[j - 1]) % MODULO;
                }
                if (j + 1 <= maxColumn) {
                    dpNext[j] = (dpNext[j] + dp[j + 1]) % MODULO;
                }
            }
            dp = dpNext;
        }
        return dp[0];
    }
}
