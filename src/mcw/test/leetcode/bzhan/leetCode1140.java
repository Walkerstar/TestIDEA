package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 * <p>
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
 * <p>
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * <p>
 * 游戏一直持续到所有石子都被拿走。
 * <p>
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
 * 示例 2:
 * <p>
 * 输入：piles = [1,2,3,4,5,100]
 * 输出：104
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10^4
 *
 * @author mcw 2023/2/22 14:11
 */
public class leetCode1140 {

    /**
     * 记忆化搜索
     */
    public int stoneGameII(int[] piles) {
        int[] prefixSum = new int[piles.length + 1];
        for (int i = 0; i < piles.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + piles[i];
        }
        Map<Integer, Integer> memo = new HashMap<>();
        return (prefixSum[piles.length] + dp(memo, piles, prefixSum, 0, 1)) / 2;
    }

    public int dp(Map<Integer, Integer> memo, int[] piles, int[] prefixSum, int i, int m) {
        if (i == piles.length) {
            return 0;
        }
        int key = i * 201 + m;
        if (!memo.containsKey(key)) {
            int maxVal = Integer.MIN_VALUE;
            for (int x = 1; x <= 2; x++) {
                if (i + x > piles.length) {
                    break;
                }
                maxVal = Math.max(maxVal, prefixSum[i + x] - prefixSum[i] - dp(memo, piles, prefixSum, i + x, Math.max(m, x)));
            }
            memo.put(key, maxVal);
        }
        return memo.get(key);
    }


    /**
     * 动态规划
     * <p>
     * <p>
     * 递推公式
     * 定义dp数组：
     * dp[i][j]表示剩余[i : len - 1](意思是： 剩余 i ~ (len-1) 堆石子 )堆时，M = j的情况下，先取的人能获得的最多石子数
     * <p>
     * 1. i + 2M >= len, dp[i][M] = sum[i : len - 1], 剩下的堆数能够直接全部取走，那么最优的情况就是剩下的石子总和
     * 2. i + 2M < len, dp[i][M] = max(dp[i][M], sum[i : len - 1] - dp[i + x][max(M, x)]), 其中 1 <= x <= 2M，剩下的堆数不能全部取走，
     * 那么最优情况就是让下一个人取的更少。对于我所有的x取值，下一个人从x开始取起，M变为max(M, x)，所以下一个人能取dp[i + x][max(M, x)]，
     * 我最多能取sum[i : len - 1] - dp[i + x][max(M, x)]。
     */
    public int stoneGameII2(int[] piles) {
        int len = piles.length;
        int sum = 0;
        int[][] dp = new int[len][len + 1];
        for (int i = len - 1; i >= 0; i++) {
            sum += piles[i];
            for (int M = 1; M <= len; M++) {
                if (i + 2 * M >= len) {
                    dp[i][M] = sum;
                } else {
                    for (int x = 1; x <= 2 * M; x++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }


}
