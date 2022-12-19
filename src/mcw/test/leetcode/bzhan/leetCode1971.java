package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 有一个具有 n 个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
 * <p>
 * 请你确定是否存在从顶点 source 开始，到顶点 destination 结束的 有效路径 。
 * <p>
 * 给你数组 edges 和整数 n、source 和 destination，如果从 source 到 destination 存在 有效路径 ，则返回 true，否则返回 false 。
 * <p>
 * 提示：
 * <li>1 <= n <= 2 * 10^5</li>
 * <li>0 <= edges.length <= 2 * 10^5</li>
 * <li>edges[i].length == 2</li>
 * <li>0 <= ui, vi <= n - 1</li>
 * <li>ui != vi</li>
 * <li>0 <= source, destination <= n </li>- 1
 * <li>不存在重复边</li>
 * <li>不存在指向顶点自身的边</li>
 *
 * @author mcw 2022/12/19 17:05
 */
public class leetCode1971 {

    /**
     * 方法一：广度优先搜索
     * <p>
     * 使用广度优先搜索判断顶点 source 到顶点 destination 的连通性，需要我们从顶点 source 开始按照层次依次遍历每一层的顶点，
     * 检测是否可以到达顶点 destination。遍历过程我们使用队列存储最近访问过的顶点，同时记录每个顶点的访问状态，每次从队列中取出顶点 vertex 时，
     * 将其未访问过的邻接顶点入队列。
     * <p>
     * 初始时将顶点 source 设为已访问，并将其入队列。每次将队列中的节点 vertex 出队列，并将与 vertex 相邻且未访问的顶点 next 入队列，
     * 并将 next 设为已访问。当队列为空或访问到顶点 destination 时遍历结束，返回顶点 destination 的访问状态即可。
     */
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            adj[x].add(y);
            adj[y].add(x);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (vertex == destination) {
                break;
            }
            for (int next : adj[vertex]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        return visited[destination];
    }

    /**
     * 方法二：深度优先搜索
     * <p>
     * 我们使用深度优先搜索检测顶点 source,destination 的连通性，需要从顶点 source 开始依次遍历每一条可能的路径，
     * 判断可以到达顶点 \destination，同时还需要记录每个顶点的访问状态防止重复访问。
     * <p>
     * 首先从顶点 source 开始遍历并进行递归搜索。搜索时每次访问一个顶点 vertex 时，如果 vertex 等于 destination 则直接返回，
     * 否则将该顶点设为已访问，并递归访问与 vertex 相邻且未访问的顶点 next。如果通过 next 的路径可以访问到 destination，
     * 此时直接返回 true，当访问完所有的邻接节点仍然没有访问到 destination，此时返回 false。
     */
    public static boolean validPath2(int n, int[][] edges, int source, int destination) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            adj[x].add(y);
            adj[y].add(x);
        }
        boolean[] visited = new boolean[n];
        return dfs(source, destination, adj, visited);
    }

    public static boolean dfs(int source, int destination, List<Integer>[] adj, boolean[] visited) {
        if (source == destination) {
            return true;
        }
        visited[source] = true;
        for (int next : adj[source]) {
            if (!visited[next] && dfs(next, destination, adj, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法三：并查集
     * <p>
     * 我们将图中的每个强连通分量视为一个集合，强连通分量中任意两点均可达，如果两个点 source 和 destination 处在同一个强连通分量中，
     * 则两点一定可连通，因此连通性问题可以使用并查集解决。
     * <p>
     * 并查集初始化时，n 个顶点分别属于 n 个不同的集合，每个集合只包含一个顶点。初始化之后遍历每条边，由于图中的每条边均为双向边，
     * 因此将同一条边连接的两个顶点所在的集合做合并。
     * <p>
     * 遍历所有的边之后，判断顶点 source 和顶点 destination 是否在同一个集合中，如果两个顶点在同一个集合则两个顶点连通，
     * 如果两个顶点所在的集合不同则两个顶点不连通。
     */
    public static boolean validPath3(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.connect(source, destination);
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                if (rank[rx] > rank[ry]) {
                    parent[ry] = rx;
                } else if (rank[rx] < rank[ry]) {
                    parent[rx] = ry;
                } else {
                    parent[ry] = rx;
                    rank[rx]++;
                }
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean connect(int x, int y) {
            return find(x) == find(y);
        }
    }
}
