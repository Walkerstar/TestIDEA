package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无向、连通的树。树中有 n 个标记为 0...n-1 的节点以及 n-1 条边 。
 * <p>
 * 给定整数 n 和数组 edges ， edges[i] = [ai, bi]表示树中的节点 ai 和 bi 之间有一条边。
 * <p>
 * 返回长度为 n 的数组 answer ，其中 answer[i] 是树中第 i 个节点与所有其他节点之间的距离之和。
 * <p>
 * 示例 1:
 * 输入: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释: 树如图所示。
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 * <p>
 * 示例 2:
 * 输入: n = 1, edges = []
 * 输出: [0]
 * <p>
 * 示例 3:
 * 输入: n = 2, edges = [[1,0]]
 * 输出: [1,1]
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 3 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * 给定的输入保证为有效的树
 *
 * @author MCW 2023/7/16
 */
public class leetCode834 {

    int[] ans;

    /**
     * 定义 sz[u] 表示以  u 为根的子树的节点数量
     */
    int[] sz;

    /**
     * 定义  dp[u] 表示以  u 为根的子树，它的所有子节点到它的距离之和
     */
    int[] dp;

    List<List<Integer>> graph;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ans = new int[n];
        sz = new int[n];
        dp = new int[n];
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public void dfs(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        for (int v : graph.get(u)) {
            if (v == f) {
                continue;
            }
            dfs(v, u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    public void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v : graph.get(u)) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];
            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            dfs2(v, u);
            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }
}
