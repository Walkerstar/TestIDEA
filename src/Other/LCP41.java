package Other;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 在 n * m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。
 * 当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
 * <p>
 * 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。
 * 若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
 * <p>
 * 注意：
 * <p>
 * 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
 * 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
 * <p>
 * 示例 1：
 * 输入：chessboard = ["....X.","....X.","XOOO..","......","......"]
 * 输出：3
 * 解释：
 * 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。
 * <p>
 * 示例 2：
 * 输入：chessboard = [".X.",".O.","XO."]
 * 输出：2
 * 解释：
 * 可以选择下在 [2,2] 处，能够翻转白方两枚棋子。
 * <p>
 * 示例 3：
 * 输入：chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]
 * 输出：4
 * 解释：
 * 可以选择下在 [6,3] 处，能够翻转白方四枚棋子。
 * <p>
 * 提示：
 * 1 <= chessboard.length, chessboard[i].length <= 8
 * chessboard[i] 仅包含 "."、"O" 和 "X"
 *
 * @author MCW 2023/6/21
 */
public class LCP41 {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    /**
     * 方法一：广度优先搜索
     * 思路与算法
     * <p>
     * 题目给出一个大小为 n×m 的黑白棋盘 chessboard，其中黑棋用 ‘X’ 表示，白棋用 ‘O’ 表示，空余位置用 ‘.’ 表示。
     * 现在我们可以在棋盘中的一个空余位置下一个黑棋，现在我们称「翻转操作」为：
     * 若存在在放置黑棋的位置的行、列或者对角线有另一颗黑棋能完全包围（中间不存在空白位置）白棋，那么我们可以翻转这些白棋，使其变为黑棋，
     * 并且这些翻转后得到的新黑棋同样可以进行「翻转操作」。现在我们需要求在放置一次黑棋的情况下最多能翻转多少颗白棋。
     * <p>
     * 我们可以用「广度优先搜索」来解决这个问题，我们对每一个空余位置尝试黑棋放置，用一个队列来存储正在进行「翻转操作」的黑棋位置，
     * 若队列非空，我们从队列中取出队首元素，进行行、列和对角线 8 个方向判断是否有可以翻转的白棋——判断沿着方向是否是连续的一段白棋并以另一颗黑棋结尾。
     * 若有可以翻转的白棋，则将这些白旗进行翻转，并加入队列中。直至队列为空表示一次放置黑棋结束。
     * <p>
     * 初始可以放置黑棋的全部位置中可以翻转的白棋个数最大值即为最后的答案。
     */
    public int flipChess(String[] chessboard) {
        int res = 0;
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length(); j++) {
                // 找到一个空白处，在此下一个黑棋，看看能转换几个白棋
                if (chessboard[i].charAt(j) == '.') {
                    res = Math.max(res, bfs(chessboard, i, j));
                }
            }
        }
        return res;
    }

    public int bfs(String[] chessboard, int px, int py) {
        // 把 String[] 转换为 char[][]
        char[][] board = new char[chessboard.length][chessboard[0].length()];
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length(); j++) {
                board[i][j] = chessboard[i].charAt(j);
            }
        }
        // 统计白棋转换个数
        int cnt = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{px, py});
        // 将 此处 空余位置，放置黑棋
        board[px][py] = 'X';
        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            // 向八方寻找
            for (int i = 0; i < 8; i++) {
                // 如果找到符合条件的白棋，则进行计数
                if (judge(board, t[0], t[1], dirs[i][0], dirs[i][1])) {
                    int x = t[0] + dirs[i][0], y = t[1] + dirs[i][1];
                    // 将此方向的所有白棋转换为黑棋
                    while (board[x][y] != 'X') {
                        queue.offer(new int[]{x, y});
                        board[x][y] = 'X';
                        x += dirs[i][0];
                        y += dirs[i][1];
                        ++cnt;
                    }
                }
            }
        }
        return cnt;
    }

    public boolean judge(char[][] board, int x, int y, int dx, int dy) {
        // 计算其他方位的坐标点
        x += dx;
        y += dy;
        while (0 <= x && x < board.length && 0 <= y && y < board[0].length) {
            // 如果其他方向上是黑棋，则返回true
            if (board[x][y] == 'X') {
                return true;
            } else if (board[x][y] == '.') {
                // 如果是空白位置，则返回 false
                return false;
            }
            // 如果是白棋，则继续沿着这个方向寻找，直到上述条件不满足退出循环
            x += dx;
            y += dy;
        }
        // 如果在同一方向上，只有白棋，则不满足题目条件，则返回 false
        return false;
    }
}
