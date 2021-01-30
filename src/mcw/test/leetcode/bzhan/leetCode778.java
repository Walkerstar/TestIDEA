package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位
 * 必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
 *
 * @author mcw 2021/1/30 18:07
 */
public class leetCode778 {

    /**
     * 并查集
     */
    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.max(grid[i][j], grid[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.max(grid[i][j - 1], grid[i][j])});
                }
            }
        }
        edges.sort(Comparator.comparingInt(o -> o[2]));
        UnionFind uf = new UnionFind(m * n);
        int ans = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], d = edge[2];
            ans = d;
            uf.union(x, y);
            if (uf.isConnected(0, n * m - 1)) {
                break;
            }
        }
        return ans ;
    }

    /**
     * 二分查找 + 深度优先/广度优先
     */
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int n;

    public int swimInWater1(int[][] grid) {
        n = grid.length;
        int left = 0;
        int right = n * n - 1;
        while (left < right) {
            //left + right 不会溢出
            int mid = (left + right) / 2;
            boolean[][] visited = new boolean[n][n];
            if (grid[0][0] <= mid && dfs(grid, 0, 0, visited, mid)/*或者 bfs(grid, mid))*/) {
                // mid 可以，尝试 mid 小一点是不是也可以呢？下一轮搜索的区间 [left, mid]
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 使用深度优先遍历得到从 (x, y) 开始向四个方向的所有小于等于 threshold 且与 (x, y) 连通的结点
     */
    private boolean dfs(int[][] grid, int x, int y, boolean[][] visited, int threshold) {
        visited[x][y] = true;
        for (int[] direction : directions) {
            int X = x + direction[0];
            int Y = y + direction[1];
            if (inArea(X, Y) && !visited[X][Y] && grid[X][Y] <= threshold) {
                if (X == n - 1 && Y == n - 1) {
                    return true;
                }
                if (dfs(grid, X, Y, visited, threshold)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 使用广度优先遍历得到从 (x, y) 开始向四个方向的所有小于等于 threshold 且与 (x, y) 连通的结点
     */
    private boolean bfs(int[][] grid, int threshold) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] front = queue.poll();
            int x = front[0];
            int y = front[1];
            for (int[] direction : directions) {
                int X = x + direction[0];
                int Y = y + direction[1];
                if (inArea(X, Y) && !visited[X][Y] && grid[X][Y] <= threshold) {
                    if (X == n - 1 && Y == n - 1) {
                        return true;
                    }
                    queue.offer(new int[]{X, Y});
                    visited[X][Y] = true;
                }
            }
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    /**
     * Dijkstra 算法( 前提: 没有负权边，找单源最短路径)
     */
    public int swimInWater2(int[][] grid) {
        int n = grid.length;
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> grid[0][o[1]]));
        minHeap.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        //distTo[i][j]表示: 到顶点[i,j]需要等待的最少时间
        int[][] distTo = new int[n][n];
        for (int[] to : distTo) {
            Arrays.fill(to, n * n);
        }
        distTo[0][0] = grid[0][0];
        while (!minHeap.isEmpty()) {
            //找最短的边
            int[] front = minHeap.poll();
            int currentX = front[0];
            int currentY = front[1];
            if (visited[currentX][currentY]) {
                continue;
            }
            //确定最短路径顶点
            visited[currentX][currentY] = true;
            if (currentX == n - 1 && currentY == n - 1) {
                return distTo[n - 1][n - 1];
            }

            //更新
            for (int[] direction : directions) {
                int newX = currentX + direction[0];
                int newY = currentY + direction[1];
                if (inArea(newX, newY) && !visited[newX][newY] &&
                        Math.max(distTo[currentX][currentY], grid[newX][newY]) < distTo[newX][newY]) {
                    distTo[newX][newY] = Math.max(distTo[currentX][currentY], grid[newX][newY]);
                    minHeap.offer(new int[]{newX, newY});
                }
            }
        }
        return -1;
    }

    class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (parent[x] = find(parent[x]));
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            return true;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) {
        System.out.println(new leetCode778().swimInWater(new int[][]{{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}}));
    }
}
