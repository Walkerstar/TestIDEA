package mcw.test.leetcode.bzhan;

/**
 * 如果一个正方形矩阵满足下述 全部 条件，则称之为一个 X 矩阵 ：
 * <p>
 * 1.矩阵对角线上的所有元素都 不是 0
 * 2.矩阵中所有其他元素都是 0
 * 给你一个大小为 n x n 的二维整数数组 grid ，表示一个正方形矩阵。如果 grid 是一个 X 矩阵 ，返回 true ；否则，返回 false 。
 * <p>
 * 提示：
 * <li>n == grid.length == grid[i].length</li>
 * <li>3 <= n <= 100</li>
 * <li>0 <= grid[i][j] <= 105</li>
 *
 * @author MCW 2023/1/31
 */
public class leetCode2319 {

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || (i + j) == (n - 1)) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
