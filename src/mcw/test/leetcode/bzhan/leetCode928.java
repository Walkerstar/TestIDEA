package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 928. 尽量减少恶意软件的传播 II
 * <p>
 * 给定一个由 n 个节点组成的网络，用 n x n 个邻接矩阵 graph 表示。在节点网络中，只有当 graph[i][j] = 1 时，节点 i 能够直接连接到另一个节点 j。
 * <p>
 * 一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 * <p>
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * <p>
 * 我们可以从 initial 中删除一个节点，并完全移除该节点以及从该节点到任何其他节点的任何连接。
 * <p>
 * 请返回移除后能够使 M(initial) 最小化的节点。如果有多个节点满足条件，返回索引 最小的节点 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
 * 输出：0
 * <p>
 * 示例 2：
 * <p>
 * 输入：graph = [[1,1,0],[1,1,1],[0,1,1]], initial = [0,1]
 * 输出：1
 * <p>
 * 示例 3：
 * <p>
 * 输入：graph = [[1,1,0,0],[1,1,1,0],[0,1,1,1],[0,0,1,1]], initial = [0,1]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == graph.length
 * n == graph[i].length
 * 2 <= n <= 300
 * graph[i][j] 是 0 或 1.
 * graph[i][j] == graph[j][i]
 * graph[i][i] == 1
 * 1 <= initial.length < n
 * 0 <= initial[i] <= n - 1
 * initial 中每个整数都不同
 *
 * @author MCW 2024/4/17
 */
public class leetCode928 {

    /**
     * 方法一： 深度优先搜索
     * 思路和算法
     * <p>
     * 首先构建一个图 G，其节点为所有不在 initial 中的剩余节点。
     * <p>
     * 对于不在 initial 中的节点 v，检查会被 initial 中哪些节点 u 感染。 之后再看哪些节点 v 只会被一个节点 u 感染。
     * 具体的算法可以看代码中的注释。
     */
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int N = graph.length;
        int[] clean = new int[N];
        Arrays.fill(clean, -1);
        for (int i : initial) {
            clean[i] = 0;
        }

        // For each node u in initial,dfs to find
        // 'seen': all nodes not in initial that it can reach.
        ArrayList<Integer>[] infectionBy = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            infectionBy[i] = new ArrayList<>();
        }
        for (int u : initial) {
            Set<Integer> seen = new HashSet<>();
            dfs(graph, clean, u, seen);
            for (int v : seen) {
                infectionBy[v].add(u);
            }
        }

        // For each node u in initial,for every v not in initial
        // that is uniquely infected by u,add 1 to the contribution for u.
        int[] contribution = new int[N];
        for (int v = 0; v < N; v++) {
            if (infectionBy[v].size() == 1) {
                contribution[infectionBy[v].get(0)]++;
            }
        }

        // Take the best answer.
        Arrays.sort(initial);
        int ans = initial[0], ansSize = -1;
        for (int u : initial) {
            int score = contribution[u];
            if (score > ansSize || score == ansSize && u < ans) {
                ans = u;
                ansSize = score;
            }
        }
        return ans;
    }

    public void dfs(int[][] graph, int[] clean, int u, Set<Integer> seen) {
        for (int v = 0; v < graph.length; v++) {
            if (graph[u][v] == 1 && clean[v] == 1 && !seen.contains(v)) {
                seen.add(v);
                dfs(graph, clean, v, seen);
            }
        }
    }


    /**
     * 方法二： 并查集
     * 思路
     * <p>
     * 对于并查集中的一个集合，集合中会有一定数量的节点在 initial 里面，只需要关注那些只有一个 initial 节点的集合就可以了。
     * <p>
     * 算法
     * <p>
     * 首先构建一个图 G，其节点为所有不在 initial 中的剩余节点。然后用并查集找出所有的连通分量。
     * <p>
     * 对于原始图中的每条边 n => v，u 为 initial 中的节点，v 为不在 initial 中的节点。对于 initial 中的每个节点 u，
     * 如果 u 所在的集合中只有它是唯一的 initial 节点，那么这个集合的大小就是移除 u 之后能得到的收益。
     * <p>
     * 之后遍历所有的可能找到最终答案。
     */
    public int minMalwareSpread2(int[][] graph, int[] initial) {
        int N = graph.length;
        DSU dsu = new DSU(N);

        // clean[u]==1 if its a node in the graph not in initial
        int[] clean = new int[N];
        Arrays.fill(clean, 1);
        for (int i : initial) {
            clean[i] = 0;
        }

        for (int u = 0; u < N; u++) {
            if (clean[u] == 1) {
                for (int v = 0; v < N; v++) {
                    if (clean[v] == 1 && graph[u][v] == 1) {
                        dsu.union(u, v);
                    }
                }
            }
        }

        // dsu now represents the components of the graph without
        // any nodes from initial. Let's call this graph G.
        int[] count = new int[N];
        Map<Integer, Set<Integer>> nodeToCompo = new HashMap<>();

        for (int u : initial) {
            var components = new HashSet<Integer>();
            for (int v = 0; v < N; v++) {
                if (clean[v] == 1 && graph[u][v] == 1) {
                    components.add(dsu.find(v));
                }
            }

            nodeToCompo.put(u, components);
            for (int c : components) {
                count[c]++;
            }
        }

        // For each node u in initial,nodeToCompo.get(u)
        // now has every component from G that u neighbors.
        int ans = -1, ansSize = -1;
        for (int u : nodeToCompo.keySet()) {
            Set<Integer> components = nodeToCompo.get(u);
            int score = 0;
            for (int c : components) {
                if (count[c] == 1) { // uniquely ifected
                    score += dsu.size(c);
                }
            }

            if (score > ansSize || score == ansSize && u < ans) {
                ansSize = score;
                ans = u;
            }
        }
        return ans;
    }

    class DSU {
        int[] p, sz;

        public DSU(int N) {
            p = new int[N];
            for (int i = 0; i < N; i++) {
                p[i] = i;
            }
            sz = new int[N];
            Arrays.fill(sz, 1);
        }

        public int find(int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        }

        public void union(int x, int y) {
            int px = find(x), py = find(y);
            p[py] = px;
            sz[px] += sz[py];
        }

        public int size(int x) {
            return sz[find(x)];
        }
    }
}
