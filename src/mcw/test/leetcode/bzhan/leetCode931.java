package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * <p>
 * 示例 1：
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * <p>
 * 示例 2：
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 * <p>
 * 提示：
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 *
 * @author MCW 2023/7/13
 */
public class leetCode931 {

    /**
     * 方法一：动态规划
     * 思路
     * <p>
     * 题目需要求出矩阵的和最小下降路径，可以求出末行每个元素的和最小下降路径，然后找到其中和最小的那一条路径即可。
     * <p>
     * 而根据题意，每个坐标仅可以通过它的上一排紧邻的三个坐标到达（左上，正上，右上），
     * 根据贪心思想，每个坐标的和最小下降路径长度即为它的上一排紧邻的三个坐标的和最小下降路径长度的最小值，再加上当前坐标的和。
     * <p>
     * 用 dp 表示和最小下降路径长度的话，即为 dp[i][j] = matrix[i][j] + min(dp[i−1][j−1],dp[i−1][j],dp[i−1][j+1])，
     * 第一列和最后一列有边界情况，需要特殊处理。
     * 而第一行每个元素的和最小下降路径长度为它们本身的值。
     * <p>
     * 最后返回最后一行的和最小下降路径长度的最小值即可。
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        // 新建一个空间存储 最小路径下降长度 结果
        int[][] dp = new int[n][n];
        //复制第一行
        System.arraycopy(matrix[0], 0, dp[0], 0, n);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int mn = dp[i - 1][j];
                // 边界判断
                if (j > 0) {
                    mn = Math.min(mn, dp[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mn = Math.min(mn, dp[i - 1][j + 1]);
                }
                // 每一个格子的下降值 都等于正上方三个格子中的最小值 + 当前格子的值
                dp[i][j] = mn + matrix[i][j];
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }

}
