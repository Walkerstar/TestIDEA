package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，数组 relations 中， relations[i] = [xi, yi]  表示一个先修课的关系，也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。
 * <p>
 * 在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。
 * <p>
 * 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
 * <p>
 * 示例 1：
 * 输入：n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
 * 输出：3
 * 解释：上图展示了题目输入的图。在第一个学期中，我们可以上课程 2 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。
 * <p>
 * 示例 2：
 * 输入：n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * 输出：4
 * 解释：上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。
 * <p>
 * 示例 3：
 * 输入：n = 11, relations = [], k = 2
 * 输出：6
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 15
 * 1 <= k <= n
 * 0 <= relations.length <= n * (n-1) / 2
 * relations[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 所有先修关系都是不同的，也就是说 relations[i] != relations[j] 。
 * 题目输入的图是个有向无环图。
 *
 * @author MCW 2023/6/16
 */
public class leetCode1494 {

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int[] need = new int[1 << n];
        for (int[] edge : relations) {
            need[(1 << (edge[1] - 1))] |= 1 << (edge[0] - 1);
        }
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            need[i] = need[i & (i - 1)] | need[i & (-i)];
            // i 中有任意一门课程的前置课程没有完成学习
            if ((need[i] | i) != i) {
                continue;
            }
            // 当前学期可以进行学习的课程集合
            int valid = i ^ need[i];
            // 如果个数小于 k ，则可以全部学习，不在枚举子集
            if (Integer.bitCount(valid) < k) {
                dp[i] = Math.min(dp[i], dp[i ^ valid] + 1);
            } else {
                // 否则枚举当前学期需要进行学习的课程
                for (int sub = valid; sub > 0; sub = (sub - 1) & valid) {
                    dp[i] = Math.min(dp[i], dp[i ^ sub] + 1);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
