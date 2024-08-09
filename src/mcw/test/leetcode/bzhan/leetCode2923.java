package mcw.test.leetcode.bzhan;

/**
 * 2923. 找到冠军 I
 * <p>
 * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。
 * <p>
 * 给你一个下标从 0 开始、大小为 n * n 的二维布尔矩阵 grid 。对于满足 0 <= i, j <= n - 1 且 i != j 的所有 i, j ：
 * 如果 grid[i][j] == 1，那么 i 队比 j 队 强 ；否则，j 队比 i 队 强 。
 * <p>
 * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
 * <p>
 * 返回这场比赛中将会成为冠军的队伍。
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[0,1],[0,0]]
 * 输出：0
 * 解释：比赛中有两支队伍。
 * grid[0][1] == 1 表示 0 队比 1 队强。所以 0 队是冠军。
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [[0,0,1],[1,0,1],[0,0,0]]
 * 输出：1
 * 解释：比赛中有三支队伍。
 * grid[1][0] == 1 表示 1 队比 0 队强。
 * grid[1][2] == 1 表示 1 队比 2 队强。
 * 所以 1 队是冠军。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == grid.length
 * n == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 的值为 0 或 1
 * 对于所有 i， grid[i][i] 等于 0.
 * 对于满足 i != j 的所有 i, j ，grid[i][j] != grid[j][i] 均成立
 * 生成的输入满足：如果 a 队比 b 队强，b 队比 c 队强，那么 a 队比 c 队强
 *
 * @author MCW 2024/4/12
 */
public class leetCode2923 {

    /**
     * 方法一：每行求和
     * 思路
     * <p>
     * 矩阵每一行 grid[i] 表示队伍 i 与其他队伍的强弱情况。
     * 如果队伍 i 是冠军，那么对于所有 j!=i，均有 grid[i][j]=1，而 grid[i][i]=0。
     * 因此如果队伍 i 是冠军，则有 sum(grid[i])=n−1。
     * 队伍的强弱关系具有传递性，不能成环，所以一定存在冠军。又因为任何两个队伍，存在强弱关系。
     * 基于以上两点，有且只有一个冠军。只要遍历 i，找到第一个满足 sum(grid[i])=n−1 的 i 即可。
     */
    public int findChampion(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int[] line = grid[i];
            int sum = 0;
            for (int num : line) {
                sum += num;
            }
            if (sum == n - 1) {
                return -1;
            }
        }
        return -1;
    }
}
