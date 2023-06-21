package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.List;

/**
 * 给你两组点，其中第一组中有 size1 个点，第二组中有 size2 个点，且 size1 >= size2 。
 * <p>
 * 任意两点间的连接成本 cost 由大小为 size1 x size2 矩阵给出，其中 cost[i][j] 是第一组中的点 i 和第二组中的点 j 的连接成本。如果两个组中的每个点都与另一组中的一个或多个点连接，则称这两组点是连通的。换言之，第一组中的每个点必须至少与第二组中的一个点连接，且第二组中的每个点必须至少与第一组中的一个点连接。
 * <p>
 * 返回连通两组点所需的最小成本。
 * <p>
 * 示例 1：
 * 输入：cost = [[15, 96], [36, 2]]
 * 输出：17
 * 解释：连通两组点的最佳方法是：
 * 1--A
 * 2--B
 * 总成本为 17 。
 * <p>
 * 示例 2：
 * 输入：cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
 * 输出：4
 * 解释：连通两组点的最佳方法是：
 * 1--A
 * 2--B
 * 2--C
 * 3--A
 * 最小成本为 4 。
 * 请注意，虽然有多个点连接到第一组中的点 2 和第二组中的点 A ，但由于题目并不限制连接点的数目，所以只需要关心最低总成本。
 * <p>
 * 示例 3：
 * 输入：cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
 * 输出：10
 * <p>
 * 提示：
 * <p>
 * size1 == cost.length
 * size2 == cost[i].length
 * 1 <= size1, size2 <= 12
 * size1 >= size2
 * 0 <= cost[i][j] <= 100
 *
 * @author MCW 2023/6/20
 */
public class leetCode1595 {

    public int connectTwoGroups(List<List<Integer>> cost) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();
        int m = 1 << size2;
        int[][] dp = new int[size1 + 1][m];
        for (int i = 0; i <= size1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= size1; i++) {
            for (int s = 0; s < m; s++) {
                for (int k = 0; k < size2; k++) {
                    if ((s & (1 << k)) == 0) {
                        continue;
                    }
                    dp[i][s] = Math.min(dp[i][s], dp[i][s ^ (1 << k)] + cost.get(i - 1).get(k));
                    dp[i][s] = Math.min(dp[i][s], dp[i - 1][s] + cost.get(i - 1).get(k));
                    dp[i][s] = Math.min(dp[i][s], dp[i - 1][s ^ (1 << k)] + cost.get(i - 1).get(k));
                }
            }
        }
        return dp[size1][m - 1];
    }

    public int connectTwoGroups2(List<List<Integer>> cost) {
        int size1 = cost.size(), size2 = cost.get(0).size(), m = 1 << size2;
        int[] dp1 = new int[m];
        Arrays.fill(dp1, Integer.MAX_VALUE / 2);
        int[] dp2 = new int[m];
        dp1[0] = 0;
        for (int i = 1; i <= size1; i++) {
            for (int s = 0; s < m; s++) {
                dp2[s] = Integer.MAX_VALUE / 2;
                for (int k = 0; k < size2; k++) {
                    if ((s & (1 << k)) == 0) {
                        continue;
                    }
                    dp2[s] = Math.min(dp2[s], dp2[s ^ (1 << k)] + cost.get(i - 1).get(k));
                    dp2[s] = Math.min(dp2[s], dp1[s] + cost.get(i - 1).get(k));
                    dp2[s] = Math.min(dp2[s], dp1[s ^ (1 << k)] + cost.get(i - 1).get(k));
                }
            }
            System.arraycopy(dp2, 0, dp1, 0, m);
        }
        return dp1[m - 1];
    }
}
