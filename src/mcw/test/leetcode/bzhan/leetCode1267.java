package mcw.test.leetcode.bzhan;

import java.util.HashMap;

/**
 * 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
 * <p>
 * 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
 * <p>
 * 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,0],[0,1]]
 * 输出：0
 * 解释：没有一台服务器能与其他服务器进行通信。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,0],[1,1]]
 * 输出：3
 * 解释：所有这些服务器都至少可以与一台别的服务器进行通信。
 * <p>
 * 示例 3：
 * 输入：grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * 输出：4
 * 解释：第一行的两台服务器互相通信，第三列的两台服务器互相通信，但右下角的服务器无法与其他服务器通信。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 *
 * @author MCW 2023/8/24
 */
public class leetCode1267 {

    /**
     * 方法一：两次遍历 + 哈希表
     * 思路与算法
     * <p>
     * 我们可以使用两次遍历解决本题。
     * <p>
     * 在第一次遍历中，我们遍历数组 grid，如果 grid[i,j] 的值为 1，说明位置 (i,j) 有一台服务器，
     * 我们可以将第 i 行服务器的数量，以及第 j 行服务器的数量，均加上 1。为了维护行列中服务器的数量，
     * 我们可以使用两个哈希映射 row 和 col，row 中存储行的编号以及每一行服务器的数量，col 存储列的编号以及每一列服务器的数量。
     * <p>
     * 在第二次遍历中，我们就可以根据 row 和 col 来判断每一台服务器是否能与至少其它一台服务器进行通信了。
     * 如果 grid(i,j) 的值为 1，并且 row[i] 和 col[j] 中至少有一个严格大于 1，
     * 就说明位置 (i,j) 的服务器能与同一行或者同一列的另一台服务器进行通信，答案加 1。
     */
    public int countServes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 我们可以统计每一行、每一列的服务器数量
        var rows = new HashMap<Integer, Integer>();
        var cols = new HashMap<Integer, Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.put(i, rows.getOrDefault(i, 0) + 1);
                    cols.put(i, cols.getOrDefault(i, 0) + 1);
                }
            }
        }
        // 然后遍历每个服务器，若当前服务器所在的行或者列的服务器数量超过 1，说明当前服务器满足条件，结果加 1。
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (rows.get(i) > 1 || cols.get(j) > 1)) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
