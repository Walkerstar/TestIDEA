package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
 * <p>
 * 两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
 * <p>
 * 整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
 * <p>
 * 给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * 输出：4
 * 解释：城市 0 和 1 的网络秩是 4，因为共有 4 条道路与城市 0 或 1 相连。位于 0 和 1 之间的道路只计算一次。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * 输出：5
 * 解释：共有 5 条道路与城市 1 或 2 相连。
 * <p>
 * 示例 3：
 * <p>
 * 输入：n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * 输出：5
 * 解释：2 和 5 的网络秩为 5，注意并非所有的城市都需要连接起来。
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 100
 * 0 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 2
 * 0 <= ai, bi <= n-1
 * ai != bi
 * 每对城市之间 最多只有一条 道路相连
 *
 * @author mcw 2023/3/15 10:46
 */
public class leetCode1615 {

    /**
     * 枚举
     * 根据题意可知，两座不同城市构成的城市对的网络秩定义为：与这两座城市直接相连的道路总数，这两座城市之间的道路只计算一次。
     * 假设城市 x 的度数为 degree[x]，则此时我们可以知道城市对 (i,j) 的网络秩为如下：
     * <p>
     * 如果 i 与 j 之间没有道路连接，则此时 (i,j) 的网络秩为 degree[i]+degree[j]；
     * 如果 i 与 j 之间存在道路连接，则此时 (i,j) 的网络秩为 degree[i]+degree[j]−1；
     * 根据以上求网络秩的方法，我们首先求出所有城市在图中的度数，然后枚举所有可能的城市对 (i,j)，求出城市对 (i,j) 的网络秩，即可找到最大的网络秩。
     */
    public static int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] v : roads) {
            connect[v[0]][v[1]] = true;
            connect[v[1]][v[0]] = true;
            degree[v[0]]++;
            degree[v[1]]++;
        }
        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degree[i] + degree[j] - (connect[i][j] ? 1 : 0);
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }

    /**
     * 贪心
     */
    public static int maximalNetworkRank2(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] road : roads) {
            int x = road[0], y = road[1];
            connect[x][y] = true;
            connect[y][x] = true;
            degree[x]++;
            degree[y]++;
        }

        //设 first 表示所有节点中度数的最大值，second 表示所有节点中度数的次大值
        int first = -1, second = -2;

        //我们可以求出度数为 first 的城市集合 firstArr，同时求出度数为 second 的城市集合 secondArr
        List<Integer> firstArr = new ArrayList<>();
        List<Integer> secondArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] > first) {
                second = first;
                secondArr = new ArrayList<>(firstArr);
                first = degree[i];
                firstArr.clear();
            } else if (degree[i] == first) {
                firstArr.add(i);
            } else if (degree[i] > second) {
                secondArr.clear();
                second = degree[i];
                secondArr.add(i);
            } else if (degree[i] == second) {
                secondArr.add(i);
            }
        }
        /*
         * 我们可以求出度数为 first 的城市集合 firstArr，同时求出度数为 second 的城市集合 secondArr。
         * 设城市的总数量为 n，道路的总数量为 m，集合 firstArr 的数量为 x，则此时该集合可以构造的城市对数量为 x(x−1)/2 ，分以下几种情况来讨论:
         *
         * 1.如果 x=1，此时我们必须选择 firstArr 中唯一的城市，另一个城市只能在 secondArr 中选择，枚举 secondArr 中的每个城市，找到最大的网络秩即可，此时需要的时间复杂度为 O(n)；
         * 2.如果 x>1 时，分类讨论如下：
         * 2.1 如果满足 (x/2)>m= 时，此时集合 firstArr 一定存在一对城市，他们之间没有道路连接，此时最大的网络秩即为 2×first；
         * 2.2 如果满足 (x/2)≤m 时，此时枚举集合 firstArr 中所有不同的城市对即可，此时不需要再考虑次大的城市集合 secondArr，
         *     因为此时一定满足 2 × first − 1 ≥ first + second > 2 × second ，此时时间复杂度不超过 O(m)；
         * 因此通过以上分析，上述解法的时间复杂度为 O(n+m)O(n + m)O(n+m)。
         *
         */
        if (firstArr.size() == 1) {
            int u = firstArr.get(0);
            for (int v : secondArr) {
                if (!connect[u][v]) {
                    return first + second;
                }
            }
            return first + second - 1;
        } else {
            int m = roads.length;
            if (firstArr.size() * (firstArr.size() - 1) / 2 > m) {
                return first * 2;
            }
            for (int u : firstArr) {
                for (int v : firstArr) {
                    if (u != v && !connect[u][v]) {
                        return first * 2;
                    }
                }
            }
            return first * 2 - 1;
        }
    }
}
