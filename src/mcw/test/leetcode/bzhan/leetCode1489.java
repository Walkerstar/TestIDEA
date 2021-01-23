package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges，其中 edges[i] = [fromi, toi, weighti]表示
 * 在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 * <p></p>
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
 * 伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 * <p></p>
 * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]<p>
 * 输出：[[0,1],[2,3,4,5]]
 *
 * @author mcw 2021/1/21 18:29
 */
public class leetCode1489 {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            System.arraycopy(edges[i], 0, newEdges[i], 0, 3);
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, Comparator.comparingInt(u -> u[2]));
        //计算 value
        UnionFind unionFind = new UnionFind(n);
        int value = 0;
        for (int i = 0; i < m; i++) {
            if (unionFind.union(newEdges[i][0], newEdges[i][1])) {
                value += newEdges[i][2];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ans.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            //判断是否是关建边
            UnionFind uf = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < m; j++) {
                if (i != j && uf.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (uf.setCount != 1 || (uf.setCount == 1 && v > value)) {
                ans.get(0).add(newEdges[i][3]);
                continue;
            }
            //判断是否是伪关建边
            uf = new UnionFind(n);
            uf.union(newEdges[i][0], newEdges[i][1]);
            v = newEdges[i][2];
            for (int j = 0; j < m; j++) {
                if (i != j && uf.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            if (v == value) {
                ans.get(1).add(newEdges[i][3]);
            }
        }
        return ans;
    }

    class UnionFind {
        int[] parent;
        int[] size;
        int n;
        //当前连通分量数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
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
            //等价于下面的写法
            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            --setCount;
            /*
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }*/
            return true;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    class TarjanSCC {
        List<Integer>[] edges;
        List<Integer>[] edgesId;
        int[] low;
        int[] dfn;
        List<Integer> ans;
        int n;
        int ts;

        public TarjanSCC( int n, List<Integer>[] edges, List<Integer>[] edgesId) {
            this.edges = edges;
            this.edgesId = edgesId;
            this.n = n;
            this.low = new int[n];
            Arrays.fill(low, 1);
            this.dfn = new int[n];
            Arrays.fill(dfn, 1);
            this.ts = -1;
            this.ans = new ArrayList<>();
        }

        public List<Integer> getCuttingEdge() {
            for (int i = 0; i < n; i++) {
                if (dfn[i] == -1) {
                    getCuttingEdge(i, -1);
                }
            }
            return ans;
        }

        private void getCuttingEdge(int u, int parentEdgeId) {
            low[u] = dfn[n] = ++ts;
            for (int i = 0; i < edges[u].size(); i++) {
                int v = edges[u].get(i);
                int id = edgesId[u].get(i);
                if (dfn[v] == -1) {
                    getCuttingEdge(v, id);
                    low[u] = Math.min(low[u], low[v]);
                    if (low[v] > dfn[u]) {
                        ans.add(id);
                    }
                } else if (id != parentEdgeId) {
                    low[u] = Math.min(low[u], dfn[v]);
                }
            }
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges1(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            System.arraycopy(edges[i], 0, newEdges[i], 0, 3);
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, Comparator.comparingInt(u -> u[2]));
        UnionFind uf = new UnionFind(n);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ans.add(new ArrayList<>());
        }
        int[] label = new int[m];
        for (int i = 0; i < m; i++) {
            //找出所有权值为 W 的边，下标范围为 [i,j)
            int w = newEdges[i][2];
            int j = i;
            while (j < m && newEdges[j][2] == newEdges[i][2]) {
                ++j;
            }
            //存储每个连通分量在图 G 中编号
            Map<Integer, Integer> compToId = new HashMap<>();
            // 图 G 的节点数
            int gn = 0;
            for (int k = i; k < j; k++) {
                int x = uf.find(newEdges[k][0]);
                int y = uf.find(newEdges[k][1]);
                if (x != y) {
                    if (!compToId.containsKey(x)) {
                        compToId.put(x, gn++);
                    }
                    if (!compToId.containsKey(y)) {
                        compToId.put(y, gn++);
                    }
                } else {
                    //将自环边标记为 -1
                    label[newEdges[k][3]] = -1;
                }
            }
            //图 G 的边
            List<Integer>[] gm = new List[gn];
            List<Integer>[] gMid = new List[gn];
            for (int k = 0; k < gn; k++) {
                gm[k] = new ArrayList<>();
                gMid[k] = new ArrayList<>();
            }
            for (int k = i; k < j; k++) {
                int x = uf.find(newEdges[k][0]);
                int y = uf.find(newEdges[k][1]);
                if (x != y) {
                    int idx = compToId.get(x);
                    int idy = compToId.get(y);
                    gm[idx].add(idy);
                    gMid[idx].add(newEdges[k][3]);
                    gm[idy].add(idx);
                    gMid[idy].add(newEdges[k][3]);
                }
            }
            List<Integer> bridges = new TarjanSCC(gn, gm, gMid).getCuttingEdge();
            //将桥边（关建边）标记为 1
            for (int id : bridges) {
                ans.get(0).add(id);
                label[id] = 1;
            }
            for (int k = i; k < j; k++) {
                uf.union(newEdges[k][0], newEdges[k][1]);
            }
            i = j;
        }

        //未标记的边即为非桥边（伪关建边）
        for (int i = 0; i < m; i++) {
            if (label[i] == 0) {
                ans.get(1).add(i);
            }
        }
        return ans;
    }
}
