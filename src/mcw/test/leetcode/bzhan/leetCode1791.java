package mcw.test.leetcode.bzhan;

/**
 * 有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
 *
 * 给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回 edges 所表示星型图的中心节点。
 *
 * @author MCW 2022/2/18
 */
public class leetCode1791 {

    /**
     * 方法一：计算每个节点的度
     * 由 n 个节点组成的星型图中，有一个中心节点，有 n - 1条边分别连接中心节点和其余的每个节点。
     * 因此，中心节点的度是 n - 1，其余每个节点的度都是 1。一个节点的度的含义是与该节点相连的边数。
     *
     * 遍历 edges 中的每条边并计算每个节点的度，度为 n - 1 的节点即为中心节点。
     */
    public int findCenter(int[][] edges) {
        int n = edges.length + 1;
        int[] degrees = new int[n + 1];
        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
        for (int i = 1; ; i++) {
            if (degrees[i] == n - 1) {
                return i;
            }
        }
    }

    /**
     * 方法二：寻找出现在两条边中的节点
     * 由于只有星型图的中心节点的度是 n−1，其余每个节点的度都是 1，因此只有星型图在所有的边中都出现，其余每个节点分别只在一条边中出现。
     *
     * 根据星型图的上述性质可知，对于星型图中的任意两条边，星型图的中心节点一定同时在这两条边中出现，其余节点一定不会同时在这两条边中出现。
     * 因此，可以任选两条边，然后寻找这两条边的公共节点，该节点即为星型图的中心节点。
     *
     * 具体做法是，选择 edges[0] 和 edges[1] 这两条边，则星型图的中心节点是 edges[0][0] 或者 edges[0][1]。
     * 如果 edges[0][0] 和 edges[1] 的两个节点之一相同则 edges[0][0] 是星型图的中心节点，
     * 如果 edges[0][0] 和 edges[1] 的两个节点都不相同则 edges[0][1] 是星型图的中心节点。
     */
    public int findCenter2(int[][] edges) {
        return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }
}
