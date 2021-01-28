package mcw.test.leetcode.bzhan;

/**
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。   返回区域的数目。
 *
 * @author mcw 2021/1/25 18:30
 */
public class leetCode959 {

    /**
     * 设网格为 n×n 大小，则图中有 4*n*n个节点，每个方块对应其中的 4 个节点。、
     * 为了下文中描述方便，我们按照下图的方式，给每个方块的四个小三角形编号 0,1,2,3：
     * -----------
     * |  \ 0 /  |
     * |   \ / 1 |
     * | 3 / \   |
     * |  / 2 \  |
     * -----------
     *
     */
    public int regionBySlashes(String[] grid) {
        int N = grid.length;
        int size = 4 * N * N;
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < N; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                //二维网格转换为一维表格
                int index = 4 * (i * N + j);
                char c = row[j];
                //单元格合并
                if (c == '/') {
                    //合并0、3，合并1、2
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                } else if (c == '\\') {
                    //合并0、1，合并2、3
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                } else {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }
                //单元格合并
                //向右合并：1（当前）、3（右一列）
                if (j + 1 < N) {
                    unionFind.union(index + 1, 4 * (i * N + j + 1) + 3);
                }
                //向下合并：2（当前）、0（下一行）
                if (i + 1 < N) {
                    unionFind.union(index + 2, 4 * ((i + 1) * N + j));
                }
            }
        }
        return unionFind.getCount();
    }

    class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            return parent[x] == x ? x : parent[find(parent[x])];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}
