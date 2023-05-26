package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
 * <p>
 * 游戏地图用大小为  m x n  的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
 * <p>
 * 现在你将作为玩家参与游戏，按规则将箱子  'B'  移动到目标位置  'T' ：
 * <p>
 * 玩家用字符  'S'  表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
 * 地板用字符  '.'  表示，意味着可以自由行走。
 * 墙用字符  '#'  表示，意味着障碍物，不能通行。
 * 箱子仅有一个，用字符  'B'  表示。相应地，网格上有一个目标位置  'T'。
 * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
 * 玩家无法越过箱子。
 * 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回  -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [["#","#","#","#","#","#"],
 * ["#","T","#","#","#","#"],
 * ["#",".",".","B",".","#"],
 * ["#",".","#","#",".","#"],
 * ["#",".",".",".","S","#"],
 * ["#","#","#","#","#","#"]]
 * 输出：3
 * 解释：我们只需要返回推箱子的次数。
 * 示例 2：
 * <p>
 * 输入：grid = [["#","#","#","#","#","#"],
 * ["#","T","#","#","#","#"],
 * ["#",".",".","B",".","#"],
 * ["#","#","#","#",".","#"],
 * ["#",".",".",".","S","#"],
 * ["#","#","#","#","#","#"]]
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：grid = [["#","#","#","#","#","#"],
 * ["#","T",".",".","#","#"],
 * ["#",".","#","B",".","#"],
 * ["#",".",".",".",".","#"],
 * ["#",".",".",".","S","#"],
 * ["#","#","#","#","#","#"]]
 * 输出：5
 * 解释：向下、向左、向左、向上再向上。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * grid 仅包含字符  '.', '#', 'S' , 'T', 以及  'B'。
 * grid  中  'S', 'B'  和  'T'  各只能出现一个。
 *
 * @author MCW 2023/5/8
 */
public class leetCode1263 {

    /**
     * 方法一：01-广度优先搜索
     * 由题意可知，目标位置固定时，将箱子推到目标位置的最小推动次数与箱子位置和玩家位置相关。
     * 我们把箱子位置和玩家位置当成一个状态，那么状态的转移主要由玩家向上、下、左、右四个方向移动触发（
     * 如果玩家移动后的位置与箱子位置重叠，那么箱子也相应的作出同样的移动，即一次“推动”）。
     * 我们把状态看成有向图的节点，状态的转移看成有向图的边，对应的边长与是否推动箱子有关（推动箱子时，边长为 1，否则为 0）。
     * <p>
     * 将箱子推到目标位置对应多个状态，这些状态中箱子位置等于目标位置。因此问题可以转化为：
     * 给定一个有向图，边长为 0 或 1，求某一节点到符合条件的任一节点的最短路径。
     * 边权非负时，可以使用 textDijkstra 算法求解，但是本题的边权限定在 0 与 1 之间，可以应用时间复杂度更优的 01-广度优先搜索算法。
     * 不同于等边权的广度优先搜索，01-广度优先搜索在访问节点时，下一个节点的路径长度可能与当前访问节点的路径长度相同，
     * 也可能等于当前访问节点的路径长度加 1，我们需要先访问完前者的节点，然后才开始访问后者的节点。
     * <p>
     * 记地图行数为 m，列数为 n，将坐标 (x,y) 按照 timesn + y 的形式进行编码。
     * 首先我们遍历整个地图 textitgrid，得到箱子初始位置为(b_x  ,b_y )，玩家初始位置为 (s_x ,s_y )。
     * 使用 textitdp 记录各个状态的最小推动次数，初始状态对应的最小推动次数为 0，即 dp[s_x + s_y][b_x + b_y] = 0。
     * 我们使用队列 q 保存相同最小推动次数的状态，队列 q1 保存最小推动次数等于 q 中的状态的最小推动次数加 1 的状态。
     * 初始时队列 q 的元素为初始状态，队列 q1 为空。
     * <p>
     * 我们不断地从队列 q 中取出状态，如果状态对应的箱子位置等于目标位置，那么返回对应状态的最小推动次数，否则执行状态转移，即玩家位置移动：
     * <p>
     * 1. 如果玩家移动后的位置不合法，即越界或在墙上，那么说明转移无效，等价于图中不存在对应的边，执行下一次状态转移，否则执行步骤 2。
     * 2. 根据玩家移动后的位置与箱子位置是否一致，分为两种情况：
     * 2.1 玩家移动后位置与箱子位置一致：箱子发生相同的移动，如果箱子位置不合法或状态已被访问，那么执行下一次状态转移，否则记录转移后状态的最小推动次数等于当前状态的最小推动次数加 1，并且将转移后状态放入 q1 中。
     * 2.2 玩家移动后位置与箱子位置不一致：如果状态已被访问，那么执行下一次状态转移，否则记录转移后状态的最小推动次数等于当前状态的最小推动次数，并且将转移后状态放入 q 中。
     * <p>
     * 当队列 q 为空时，如果队列 q1 非空，那么将 q 与 q1  交换，否则说明无法将箱子推动到目标位置，返回  −1。
     */
    public int minPlushBox(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //玩家，箱子的初始位置
        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == 'S') {
                    sx = x;
                    sy = y;
                } else if (grid[x][y] == 'B') {
                    bx = x;
                    by = y;
                }
            }
        }

        int[] d = {0, -1, 0, 1, 0};
        int[][] dp = new int[m * n][m * n];
        for (int i = 0; i < m * n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        //初始状态的推动次数为 0
        dp[sx * n + sy][bx * n + by] = 0;
        queue.offer(new int[]{sx * n + sy, bx * n + by});
        while (!queue.isEmpty()) {
            Queue<int[]> queue1 = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0], b1 = arr[1];
                int sx1 = s1 / n, sy1 = s1 % n, bx1 = b1 / n, by1 = b1 % n;
                //箱子已被推倒目标处
                if (grid[bx1][by1] == 'T') {
                    return dp[s1][b1];
                }
                //玩家像四个方向移动到另一个状态
                for (int i = 0; i < 4; i++) {
                    int sx2 = sx1 + d[i], sy2 = sy1 + d[i + 1], s2 = sx2 * n + sy2;
                    //玩家位置不合法
                    if (!ok(grid, m, n, sx2, sy2)) {
                        continue;
                    }
                    //推动箱子
                    if (bx1 == sx2 && by1 == sy2) {
                        int bx2 = bx1 + d[i], by2 = by1 + d[i + 1], b2 = bx2 * n + by2;
                        //箱子位置不合格，或状态已访问
                        if (!ok(grid, m, n, bx2, by2) || dp[s2][b2] <= dp[s1][b1] + 1) {
                            continue;
                        }
                        dp[s2][b2] = dp[s1][b1] + 1;
                        queue1.offer(new int[]{s2, b2});
                    } else {
                        //状态已访问
                        if (dp[s2][b1] <= dp[s1][b1]) {
                            continue;
                        }
                        dp[s2][b1] = dp[s1][b1];
                        queue.offer(new int[]{s2, b1});
                    }
                }
            }
            queue = queue1;
        }
        return -1;
    }

    public boolean ok(char[][] grid, int m, int n, int x, int y) {
        //不越界不在墙上
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#';
    }
}