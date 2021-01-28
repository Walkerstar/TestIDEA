package mcw.test.leetcode.bzhan;

/**
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3 种类型的边：
 * <p>
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi]表示节点 ui 和 vi 之间存在类型为 typei 的双向边。
 * 请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob
 * 都可以到达所有其他节点，则认为图是可以完全遍历的。
 * <p>
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 * @author mcw 2021/1/27 19:04
 */
public class leetCode1579 {

    /**
     * 我们可以遵从优先添加「公共边」的策略。具体地，我们遍历每一条「公共边」，对于其连接的的两个节点：<p>
     * 如果这两个节点在同一个连通分量中，那么添加这条「公共边」是无意义的；
     * 如果这两个节点不在同一个连通分量中，我们就可以（并且一定）添加这条「公共边」，然后合并这两个节点所在的连通分量。
     *
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int ans = 0;
        //节点编号改为从 0 开始
        for (int[] edge : edges) {
            --edge[1];
            --edge[2];
        }

        //公共边
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!ufa.union(edge[1], edge[2])) {
                    ++ans;
                } else {
                    ufb.union(edge[1], edge[2]);
                }
            }
        }

        //独占边
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                //Alice
                if (!ufa.union(edge[1], edge[2])) {
                    ++ans;
                }
            }else if (edge[0] == 2) {
                //Bob
                if (!ufb.union(edge[1], edge[2])) {
                    ++ans;
                }
            }
        }
        if (ufa.Count != 1 || ufb.Count != 1) {
            return -1;
        }
        return ans;
    }

    class UnionFind {
        int[] parent;
        int[] size;
        int Count;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            this.Count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            --Count;
            return true;
        }
    }
}
