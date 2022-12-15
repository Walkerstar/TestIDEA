package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
 * <p>
 * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
 * <p>
 * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
 * <p>
 * 提示：
 * <li>2 <= n <= 105</li>
 * <li>1 <= edgeList.length, queries.length <= 105</li>
 * <li>edgeList[i].length == 3</li>
 * <li>queries[j].length == 3</li>
 * <li>0 <= ui, vi, pj, qj <= n - 1</li>
 * <li>ui != vi</li>
 * <li>pj != qj</li>
 * <li>1 <= disi, limitj <= 109</li>
 * <li>两个点之间可能有 多条 边。</li>
 *
 * @author mcw 2022/12/14 16:06
 */
public class leetCode1697 {

    /**
     * 方法一：离线查询 + 并查集
     * <p>
     * 给定一个查询时，我们可以遍历 edgeList 中的所有边，依次将长度小于 limit 的边加入到并查集中，然后使用并查集查询 p 和 q 是否属于同一个集合。
     * 如果 p 和 q 属于同一个集合，则说明存在从 p 到 q 的路径，且这条路径上的每一条边的长度都严格小于 limit，查询返回 true，否则查询返回 false。
     * <p>
     * 如果 queries 的 limit 是非递减的，显然上一次查询的并查集里的边都是满足当前查询的 limit 要求的，我们只需要将剩余的长度小于 limit 的边加入并查集中即可。
     * 基于此，我们首先将 edgeList 按边长度从小到大进行排序，然后将 queries 按 limit 从小到大进行排序，使用 k 指向上一次查询中不满足 limit 要求的长度最小的边，显然初始时 k=0。
     * <p>
     * 我们依次遍历 queries：如果 k 指向的边的长度小于对应查询的 limit，则将该边加入并查集中，然后将 k 加 1，直到 k 指向的边不满足要求；
     * 最后根据并查集查询对应的 p 和 q 是否属于同一集合来保存查询的结果。
     */
    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        //按边长从小到大排列
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        Integer[] index = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            index[i] = i;
        }
        //按照 queries的 limit 边从小到大排列
        Arrays.sort(index, (a, b) -> queries[a][2] - queries[b][2]);

        //初始化并查集
        int[] uf = new int[n];
        for (int i = 0; i < n; i++) {
            uf[i] = i;
        }
        boolean[] res = new boolean[queries.length];
        int k = 0;
        for (int i : index) {
            while (k < edgeList.length && edgeList[k][2] < queries[i][2]) {
                merge(uf, edgeList[k][0], edgeList[k][1]);
                k++;
            }
            res[i] = find(uf, queries[i][0]) == find(uf, queries[i][1]);
        }
        return res;
    }

    public static int find(int[] uf, int x) {
        if (uf[x] == x) {
            return x;
        }
        return uf[x] = find(uf, uf[x]);
    }

    public static void merge(int[] uf, int x, int y) {
        x = find(uf, x);
        y = find(uf, y);
        uf[y] = x;
    }
}
