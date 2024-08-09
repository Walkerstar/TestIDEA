package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1976. 到达目的地的方案数
 * <p>
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。
 * 输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * <p>
 * 给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei]
 * 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
 * <p>
 * 请返回花费 最少时间 到达目的地的 路径数目 。由于答案可能很大，将结果对 109 + 7 取余 后返回。
 * <p>
 * 示例 1：
 * 输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * 输出：4
 * 解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
 * 四条花费 7 分钟的路径分别为：
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 2, roads = [[1,0,10]]
 * 输出：1
 * 解释：只有一条从路口 0 到路口 1 的路，花费 10 分钟。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 200
 * n - 1 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * 1 <= timei <= 10^9
 * ui != vi
 * 任意两个路口之间至多有一条路。
 * 从任意路口出发，你能够到达其他任意路口。
 *
 * @author MCW 2024/3/5
 */
public class leetCode1976 {

    /**
     * 方法一：优先队列实现的 Dijkstra 算法
     * 思路
     * <p>
     * 正数权值的连通图中，求两点之间的最短路，很容易想到经典的「Dijkstra 算法」。但是本题不仅仅是求出最短路，还要求出最短路径的数目。
     * 我们将在「Dijkstra 算法」的基础上，进行一些变动，来求出最短路径的数目。
     * <p>
     * 观察优先队列实现的「Dijkstra 算法」，有以下数据结构：
     * <p>
     * e 是邻接表，这道题中需要我们自己根据 roads 创建。
     * <p>
     * q 是优先队列，元素是路径长度和点的编号。不停往外抛出队列中的最短路径和点。
     * 如果这个点是未被确定最短路径的点，那么这次出队列的操作，就将确定源到这个点的最短路径。
     * 然后依次访问这个点相邻的点，判断从这个点到相邻的点的路径，是否能刷新源相邻点的最短路径，如果能，则将路径长度和相邻点放入队列。
     * <p>
     * dis 用来记录源到各个点当前最短路径的长度。会在访问当前出队列点的相邻点的过程中被刷新。
     * <p>
     * vis 用来记录哪些点的最短路径已经被确定。在这里略显多余，可以用当前出队列的路径长度和点的最短路径的比较来代替。
     * <p>
     * 除此之外，我们还需要一个新的数组 ways。ways[v] 就表示源到点 i 最短的路径数目，且最短路径长度为 dis[v]。
     * ways 的更新时机与 dis 相同。在访问当前点 u 的各个相邻点 v 时，
     * <p>
     * 如果从点 u 到点 v 路径，能刷新 dis[v]，则更新 dis[v]，并将 ways[v] 更新为 ways[u]，
     * 表示有多少条源到点 u 的最短路径，就有多少条源到点 v 的最短路径。
     * <p>
     * 如果从点 u 到点 v 路径，与 dis[v] 相等。那么 ways[v] 的值要加上 ways[u]，表示点 u 到点 v 路径贡献了另一部分源到点 v 的最短路径。
     * <p>
     * 如果从点 u 到点 v 路径，大于 dis[v]。那么无需操作 dis[v]。
     * <p>
     * 除了这个变动，其他部分与优先队列实现的「Dijkstra 算法」完全相同。到优先队列为空后，返回 ways 最后一个元素即可。
     */
    public int countPaths(int n, int[][] roads) {
        int mod = 1000000007;
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int x = road[0], y = road[1], d = road[2];
            e[x].add(new int[]{y, d});
            e[y].add(new int[]{x, d});
        }

        long[] dis = new long[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int[] ways = new int[n];
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, 0});
        dis[0] = 0;
        ways[0] = 1;
        while (!pq.isEmpty()) {
            long[] arr = pq.poll();
            long t = arr[0];
            int u = (int) arr[1];
            if (t > dis[u]) {
                continue;
            }

            for (int[] next : e[u]) {
                int v = next[0], w = next[1];
                if (t + w < dis[v]) {
                    dis[v] = t + w;
                    ways[v] = ways[u];
                    pq.offer(new long[]{t + w, v});
                } else if (t + w == dis[v]) {
                    ways[v] = (ways[u] + ways[v]) % mod;
                }
            }
        }
        return ways[n - 1];
    }
}
