package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * <p>
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 *
 * @author mcw 2022/4/27 14:22
 */
public class leetCode417 {

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    /**
     * 方法一：深度优先搜索
     * 雨水的流动方向是从高到低，每个单元格上的雨水只能流到高度小于等于当前单元格的相邻单元格。从一个单元格开始，通过搜索的方法模拟雨水的流动，
     * 则可以判断雨水是否可以从该单元格流向海洋。
     * <p>
     * 如果直接以每个单元格作为起点模拟雨水的流动，则会重复遍历每个单元格，导致时间复杂度过高。为了降低时间复杂度，可以从矩阵的边界开始
     * 反向搜索寻找雨水流向边界的单元格，反向搜索时，每次只能移动到高度相同或更大的单元格。
     * <p>
     * 由于矩阵的左边界和上边界是太平洋，矩阵的右边界和下边界是大西洋，因此从矩阵的左边界和上边界开始反向搜索即可找到雨水流向太平洋的
     * 单元格，从矩阵的右边界和下边界开始反向搜索即可找到雨水流向大西洋的单元格。
     * <p>
     * 可以使用深度优先搜索实现反向搜索，搜索过程中需要记录每个单元格是否可以从太平洋反向到达以及是否可以从大西洋反向到达。
     * 反向搜索结束之后，遍历每个网格，如果一个网格既可以从太平洋反向到达也可以从大西洋反向到达，则该网格满足太平洋和大西洋都可以到达，
     * 将该网格添加到答案中。
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        //左边界
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific);
        }
        //上边界
        for (int j = 1; j < n; j++) {
            dfs(0, j, pacific);
        }
        //右边界
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, atlantic);
        }
        //下边界
        for (int j = 0; j < n - 1; j++) {
            dfs(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    public void dfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, ocean);
            }
        }
    }

    /**
     * 广度优先
     */
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            bfs(i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            bfs(0, j, pacific);
        }
        for (int i = 0; i < m; i++) {
            bfs(i, n - 1, atlantic);
        }
        for (int j = 0; j < n - 1; j++) {
            bfs(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<Integer>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    public void bfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : dirs) {
                int newRow = cell[0] + dir[0], newCol = cell[1] + dir[1];
                if (newRow >= 0 && newRow < m &&
                        newCol >= 0 && newCol < n &&
                        heights[newRow][newCol] >= heights[cell[0]][cell[1]] && !ocean[newRow][newCol]) {
                    ocean[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        System.out.println(new leetCode417().pacificAtlantic2(heights));
    }
}
