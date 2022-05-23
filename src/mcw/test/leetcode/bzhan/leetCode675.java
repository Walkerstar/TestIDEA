package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 m x n 的矩阵表示， 在这个矩阵中：
 * <li>0 表示障碍，无法触碰</li>
 * <li>1 表示地面，可以行走</li>
 * <li>比 1 大的数 表示有树的单元格，可以行走，数值表示树的高度</li>
 * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
 * <p>
 * 你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
 * <p>
 * 你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
 * <p>
 * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
 *
 * @author mcw 2022/5/23 10:27
 */
public class leetCode675 {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 方法一：广度优先搜索
     * 思路与算法
     * <p>
     * 首先对矩阵中的树按照树的高度进行排序，我们依次求出相邻的树之间的最短距离。
     * 利用广度优先搜索，按照层次遍历，处理队列中的节点（网格位置）。
     * visited 记录在某个时间点已经添加到队列中的节点，这些节点已被处理或在等待处理的队列中。
     * 对于下一个要处理的每个节点，查看他们的四个方向上相邻的点，如果相邻的点没有被遍历过且不是障碍，将其加入到队列中，直到找到终点为止，
     * 返回当前的步数即可。最终返回所有的步数之和即为最终结果。
     */
    public int cutOffTree(List<List<Integer>> forest) {
        //找出所有的树
        List<int[]> trees = new ArrayList<>();
        int row = forest.size();
        int col = forest.get(0).size();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        //将所有的树按照高度从小到大排列
        Collections.sort(trees, Comparator.comparingInt(a -> forest.get(a[0]).get(a[1])));

        int cx = 0;
        int cy = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); i++) {
            //计算每个树之间需要多少步
            int steps = bfs(forest, cx, cy, trees.get(i)[0], trees.get(i)[1]);
            if (steps == -1) {
                return -1;
            }
            ans += steps;
            cx = trees.get(i)[0];
            cy = trees.get(i)[1];
        }
        return ans;
    }


    public int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        //如果 当前的坐标是起始树，则步数为 0
        if (sx == tx && sy == ty) {
            return 0;
        }
        int row = forest.size();
        int col = forest.get(0).size();
        int step = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[row][col];
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        while (!queue.isEmpty()) {
            step++;
            int sz = queue.size();

            for (int i = 0; i < sz; i++) {
                int[] cell = queue.poll();
                //取出 当前树的坐标，向 四个方向寻找，
                int cx = cell[0], cy = cell[1];
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dirs[j][0];
                    int ny = cy + dirs[j][1];
                    if (nx > 0 && nx < row && ny >= 0 && ny < col) {
                        if (!visited[nx][ny] && forest.get(nx).get(ny) > 0) {
                            if (nx == tx && ny == ty) {
                                return step;
                            }

                            queue.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }

                }
            }
        }
        return -1;
    }


    /**
     * 方法二：Dijkstra 算法
     * 思路与算法
     * <p>
     * 我们还可以利用 Dijkstra 算法求矩阵中两点的最短距离，Dijkstra 算法也是利用的广度优先搜索，不同的是，每次对队列中优先选择最短路径的元素。
     * visited 记录在某个时间点已经添加到队列中的节点，这些节点已被处理或在等待处理的队列中。每次从队列中取出当前从起点开始的最少步数的点，
     * 对于下一个要处理的每个节点，查看他们的四个方向上相邻的点，如果相邻的点没有被遍历过且不是障碍，将其加入到队列中，直到找到终点为止，
     * 返回当前的步数即可。最终返回所有的步数之和即为最终结果。
     * <p>
     * 使用该算法需要考虑的问题：由于题目中遇到障碍物无法通行的，因此当前选择的最短路径的节点并不是最优的，所以该解法在此题中性能不太好。
     */
    public int cutOffTree2(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<int[]>();
        int row = forest.size();
        int col = forest.get(0).size();
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        Collections.sort(trees, Comparator.comparingInt(a -> forest.get(a[0]).get(a[1])));

        int cx = 0;
        int cy = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); ++i) {
            int steps = bfs2(forest, cx, cy, trees.get(i)[0], trees.get(i)[1]);
            if (steps == -1) {
                return -1;
            }
            ans += steps;
            cx = trees.get(i)[0];
            cy = trees.get(i)[1];
        }
        return ans;
    }

    public int bfs2(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return 0;
        }

        int row = forest.size();
        int col = forest.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[row][col];
        pq.offer(new int[]{0, sx * col + sy});
        visited[sx][sy] = true;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int dist = arr[0], loc = arr[1];
            for (int j = 0; j < 4; ++j) {
                int nx = loc / col + dirs[j][0];
                int ny = loc % col + dirs[j][1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                    if (!visited[nx][ny] && forest.get(nx).get(ny) > 0) {
                        if (nx == tx && ny == ty) {
                            return dist + 1;
                        }
                        pq.offer(new int[]{dist + 1, nx * col + ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 方法三：A* 启发式搜索算法
     * 思路与算法
     * <p>
     * 「A* 算法」算法是另一种路径查找算法。设当前搜索的起点为 (sx,sy)，终点为 (tx,ty)， 对于位置 (x,y) 的每个节点，
     * 设 A* 的估算函数为 f(x, y) = g(x, y) + h(x, y)，其中 g(x, y) 表示从起点 (sx,sy) 到 (x, y) 的实际距离，
     * 评估函数 h(x, y) 在此选择 (x, y) 到 (tx,ty) 的曼哈顿距离。
     * <p>
     * 我们利用优先队列优先选择估算函数值最小的节点，实际上 A* 搜索是 Dijkstra 的一个特例，当评估函数的 h(x, y) = 0时，此时该算法即为 Dijkstra 搜索。
     */
    public int cutOffTree3(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<int[]>();
        int row = forest.size();
        int col = forest.get(0).size();
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        Collections.sort(trees, (a, b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));

        int cx = 0;
        int cy = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); ++i) {
            int steps = bfs3(forest, cx, cy, trees.get(i)[0], trees.get(i)[1]);
            if (steps == -1) {
                return -1;
            }
            ans += steps;
            cx = trees.get(i)[0];
            cy = trees.get(i)[1];
        }
        return ans;
    }

    public int bfs3(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return 0;
        }

        int row = forest.size();
        int col = forest.get(0).size();
        int[][] costed = new int[row][col];
        for (int i = 0; i < row; ++i) {
            Arrays.fill(costed[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        costed[sx][sy] = Math.abs(sx - tx) + Math.abs(sy - ty);
        pq.offer(new int[]{costed[sx][sy], 0, sx * col + sy});
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int cost = arr[0], dist = arr[1], loc = arr[2];
            int cx = loc / col;
            int cy = loc % col;
            if (cx == tx && cy == ty) {
                return dist;
            }
            for (int i = 0; i < 4; ++i) {
                int nx = cx + dirs[i][0];
                int ny = cy + dirs[i][1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && forest.get(nx).get(ny) > 0) {
                    int ncost = dist + 1 + Math.abs(nx - tx) + Math.abs(ny - ty);
                    if (ncost < costed[nx][ny]) {
                        pq.offer(new int[]{ncost, dist + 1, nx * col + ny});
                        costed[nx][ny] = ncost;
                    }
                }
            }
        }
        return -1;
    }
}
