package mcw.test.leetcode.bzhan;

/**
 * 2639. 查询网格图中每一列的宽度
 * <p>
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。矩阵中某一列的宽度是这一列数字的最大 字符串长度 。
 * <p>
 * 比方说，如果 grid = [[-10], [3], [12]] ，那么唯一一列的宽度是 3 ，因为 -10 的字符串长度为 3 。
 * 请你返回一个大小为 n 的整数数组 ans ，其中 ans[i] 是第 i 列的宽度。
 * <p>
 * 一个有 len 个数位的整数 x ，如果是非负数，那么 字符串长度 为 len ，否则为 len + 1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[1],[22],[333]]
 * 输出：[3]
 * 解释：第 0 列中，333 字符串长度为 3 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [[-15,1,3],[15,7,12],[5,6,-2]]
 * 输出：[3,1,2]
 * 解释：
 * 第 0 列中，只有 -15 字符串长度为 3 。
 * 第 1 列中，所有整数的字符串长度都是 1 。
 * 第 2 列中，12 和 -2 的字符串长度都为 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -10^9 <= grid[r][c] <= 10^9
 *
 * @author MCW 2024/4/27
 */
public class leetCode2639 {

    /**
     * 方法一：一次遍历
     * <p>
     * 思路与算法
     * <p>
     * 遍历 grid 的每一列，求出将数字视为字符串时长度的最大值。
     * <p>
     * 计算长度时，可以手动计算，也可以将数字转为字符串直接获取其长度。
     */
    public int[] findColumnWidth(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] res = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = grid[i][j];
                int length = 0;
                if (x <= 0) {
                    length = 1;
                }
                while (x != 0) {
                    length += 1;
                    x /= 10;
                }
                res[j] = Math.max(res[j], length);
            }
        }
        return res;
    }

    /**
     * 转成字符串后直接获取数字长度
     */
    public int[] findColumnWidth2(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] res = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[j] = Math.max(res[j], String.valueOf(grid[i][j]).length());
            }
        }
        return res;
    }
}
