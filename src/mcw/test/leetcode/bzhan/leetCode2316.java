package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数 n ，表示一张 无向图 中有 n 个节点，编号为 0 到 n - 1 。
 * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边。
 * <p>
 * 请你返回 无法互相到达 的不同 点对数目 。
 * <p>
 * 示例 1：
 * 输入：n = 3, edges = [[0,1],[0,2],[1,2]]
 * 输出：0
 * 解释：所有点都能互相到达，意味着没有点对无法互相到达，所以我们返回 0 。
 * <p>
 * 示例 2：
 * 输入：n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * 输出：14
 * 解释：总共有 14 个点对互相无法到达：
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]]
 * 所以我们返回 14 。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * 不会有重复边。
 *
 * @author MCW 2023/10/21
 */
public class leetCode2316 {


    /**
     * 方法一：并查集
     * 思路
     * <p>
     * 部分点通过 edges 连接，可以组成连通分量。同属于同一个连通分量的两个点可以互相到达，不属于同一个连通分量的点一定无法互相到达。
     * 我们可以利用并查集，找到图中所有的连通分量和每个连通分量的点数 size。
     * 再遍历每个点，并查询它所在的连通分量的点数 size，而 n−size 就是与这个点无法互相到达的点数。
     * 对每个点进行这样的计算后求和，但这样的方法计算，每个点对会被计算两次，因此最后结果需要除以 2。
     * <p>
     * 在使用并查集时，为了提高效率，我们可以使用路径压缩的方法。
     * 因为计算点对数目的时候我们需要用到连通分量的点数，我们也可以使用按点数大小合并的优化。
     */
    public long countPairs(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            uf.union(x, y);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += n - uf.getSize(uf.find(i));
        }
        return res / 2;
    }

    class UnionFind {
        int[] parents;
        int[] sizes;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            sizes = new int[n];
            Arrays.fill(sizes, 1);
        }

        public int find(int x) {
            if (parents[x] == x) {
                return x;
            } else {
                parents[x] = find(parents[x]);
                return parents[x];
            }
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                if (sizes[rx] > sizes[ry]) {
                    parents[ry] = rx;
                    sizes[rx] += sizes[ry];
                } else {
                    parents[rx] = ry;
                    sizes[ry] += sizes[rx];
                }
            }
        }

        public int getSize(int x) {
            return sizes[x];
        }
    }


    private List<Integer>[] g;
    private boolean[] vis;

    /**
     * 方法一：DFS
     * <p>
     * 对于无向图中的任意两个节点，如果它们之间存在一条路径，那么它们之间就是互相可达的。
     * <p>
     * 因此，我们可以通过深度优先搜索的方式，找出每一个连通分量中的节点个数 t，
     * 然后将当前连通分量中的节点个数 t 与之前所有连通分量中的节点个数 s 相乘，
     * 即可得到当前连通分量中的不可达点对数目 s×t，然后将 t 加到 s 中。
     * 继续搜索下一个连通分量，直到搜索完所有连通分量，即可得到答案。
     */
    public long countPairs2(int n, int[][] edges) {
        g = new List[n];
        vis = new boolean[n];

        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        long ans = 0, s = 0;

        for (int i = 0; i < n; i++) {
            int t = dfs(i);
            ans += s * t;
            s += t;
        }
        return ans;
    }

    public int dfs(int i) {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        int cnt = 1;
        for (int j : g[i]) {
            cnt += dfs(j);
        }
        return cnt;
    }
}
