package mcw.test.leetcode.bzhan;

/**
 * 在本问题中, 树指的是一个连通且无环的无向图。
 *<p></p>
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，
 * 这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对 [u, v]，满足 u < v，表示连接顶点 u 和 v 的无向图的边。
 * <p></p>
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。
 * 答案边 [u, v] 应满足相同的格式 u < v。
 * @author mcw 2021/1/13 19:20
 */
public class leetCode684 {

    public int[] findRedundantConnection(int[][] edges) {
        int nodesCount = edges.length;
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < nodesCount; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            //两个顶点不属于相同的连通分量，则说明在遍历到当前的边之前，这两个顶点之间不连通，因此当前的边不会导致环出现
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);
            } else {
                //如果相同，则当前边会导致环出现，将当前的边作为答案返回。
                return edge;
            }
        }
        return new int[0];
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }
}
