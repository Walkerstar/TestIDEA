package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 2477. 到达首都的最少油耗
 * <p>
 * 给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。
 * 0 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
 * <p>
 * 每个城市里有一个代表，他们都要去首都参加一个会议。
 * <p>
 * 每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
 * <p>
 * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
 * <p>
 * 请你返回到达首都最少需要多少升汽油。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：roads = [[0,1],[0,2],[0,3]], seats = 5
 * 输出：3
 * 解释：
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 2 直接到达首都，消耗 1 升汽油。
 * - 代表 3 直接到达首都，消耗 1 升汽油。
 * 最少消耗 3 升汽油。
 * <p>
 * 示例 2：
 * <p>
 * 输入：roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
 * 输出：7
 * 解释：
 * - 代表 2 到达城市 3 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 5 直接到达首都，消耗 1 升汽油。
 * - 代表 6 到达城市 4 ，消耗 1 升汽油。
 * - 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
 * 最少消耗 7 升汽油。
 * <p>
 * 示例 3：
 * <p>
 * 输入：roads = [], seats = 1
 * 输出：0
 * 解释：没有代表需要从别的城市到达首都。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * roads.length == n - 1
 * roads[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * roads 表示一棵合法的树。
 * 1 <= seats <= 10^5
 *
 * @author MCW 2023/12/5
 */
public class leetCode2477 {

    long res = 0;

    /**
     * 方法一：贪心 + 深度优先搜索
     * 思路与算法
     * <p>
     * 题目等价于给出了一棵以节点 0 为根结点的树，并且初始树上的每一个节点上都有一个人，现在所有人都需要通过「车子」向结点 0 移动。
     * <p>
     * 对于某一个节点 x，x≠0，其父节点为 y。
     * 因为以节点 x 为根结点的子树上的人都需要通过边 x→y 向节点 0 移动，所以为了使这条边上的「车子」利用率最高，
     * 我们贪心的让 x 的全部子节点上的人到了节点 x 后再一起坐车向上移动，
     * 我们不妨设以节点 x 为根节点的子树大小为 cnt_x，那么我们至少需要「车子」的数量为 ⌈cnt_x/ seats⌉，其中 seats 为一辆车的给定座位数。
     * <p>
     * 那么我们可以通过从根结点 0 往下进行「深度优先搜索」，每一条边上「车子」的数目即为该条边上汽油的开销，
     * 统计全部边上汽油的开销即为最终答案。
     */
    public long minimumFueCost(int[][] roads, int seats) {
        int n = roads.length;
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : roads) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        dfs(0, -1, seats, g);
        return res;
    }

    public int dfs(int cur, int fa, int seats, List<Integer>[] g) {
        int peopleSum = 1;
        for (int ne : g[cur]) {
            if (ne != fa) {
                int peopleCnt = dfs(ne, cur, seats, g);
                peopleSum += peopleCnt;
                res += (peopleCnt + seats - 1) / seats;
            }
        }
        return peopleSum;
    }
}
