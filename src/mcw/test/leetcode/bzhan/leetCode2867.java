package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2867. 统计树中的合法路径数目
 * <p>
 * 给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
 * 其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
 * <p>
 * 请你返回树中的 合法路径数目 。
 * <p>
 * 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
 * <p>
 * 注意：
 * <p>
 * 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
 * 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
 * 输出：4
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * 只有 4 条合法路径。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
 * 输出：6
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (1, 6) 因为路径 1 到 6 只包含一个质数 3 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * - (3, 6) 因为路径 3 到 6 只包含一个质数 3 。
 * 只有 6 条合法路径。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * 输入保证 edges 形成一棵合法的树。
 *
 * @author MCW 2024/2/27
 */
public class leetCode2867 {

    // 埃氏筛
    // 如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，
    private static final int N = 100001;
    private static boolean[] isPrime = new boolean[N];

    static {
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i * i < N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }


    /**
     * 方法一：埃氏筛 + 深度优先搜索
     * 思路与算法
     * <p>
     * 根据题意，我们需要知道一个数是不是质数，可以采用「埃氏筛」来找出范围内所有的质数。关于质数筛选，可以参考题解204. 计数质数。
     * <p>
     * 然后我们分别以质数节点为根，用「深度优先搜索」的方式，递归搜索所有的非质数的子树，并求出所有子树的大小，搜索过程中只搜索非质数节点。
     * 任何两个来自不同子树的节点，其路径都通过质数根节点，路径上恰好只有根节点一个质数节点，根据题意路径是合法的。
     * 我们只需要把所子树大小，两两相乘并求和，就可以得到包含根节点的所有合法路径。
     * <p>
     * 遍历所有质数节点，并且重复上述过程，便可以得到所有合法路径的数目，返回为最终结果。
     */
    public long countPaths(int n, int[][] edges) {
        List<Integer>[] G = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            G[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int i = edge[0], j = edge[1];
            G[i].add(j);
            G[j].add(i);
        }

        List<Integer> seen = new ArrayList<>();
        long res = 0;
        long[] count = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            long cur = 0;
            for (int j : G[i]) {
                if (count[j] == 0) {
                    seen.clear();
                    dfs(G, seen, j, 0);
                    long cnt = seen.size();
                    for (int k : seen) {
                        count[k] = cnt;
                    }
                }
                res += count[j] * cur;
                cur += count[j];
            }
            res += cur;
        }
        return res;
    }


    private void dfs(List<Integer>[] G, List<Integer> seen, int i, int pre) {
        seen.add(i);
        for (int j : G[i]) {
            if (j != pre && !isPrime[j]) {
                dfs(G, seen, j, i);
            }
        }
    }
}
