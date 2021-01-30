package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi]。
 *
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 *
 * @author mcw 2021/1/19 20:44
 */
public class leetCode1584 {

    /**
     * 将这张完全图中的边全部提取到边集数组中，然后对所有边进行排序，从小到大进行枚举，每次贪心选边加入答案。
     * 使用并查集维护连通性，若当前边两端不连通即可选择这条边
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        DisJoinSetUnion dsu = new DisJoinSetUnion(n);
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edgeList.add(new Edge(dist(points, i, j), i, j));
            }
        }
        edgeList.sort(Comparator.comparingInt(o -> o.len));
        int ret = 0, num = 1;
        for (Edge edge : edgeList) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (dsu.unionSet(x, y)) {
                ret += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 计算两点间的距离的绝对值
     */
    public int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }

    class DisJoinSetUnion {
        int[] parent;
        int[] rank;
        int n;

        public DisJoinSetUnion(int n) {
            this.n = n;
            this.rank = new int[n];
            Arrays.fill(this.rank, 1);
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        public boolean unionSet(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (rank[fx] < rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            rank[fx] += rank[fy];
            parent[fy] = fx;
            return true;
        }
    }

    class Edge{
        int len,x,y;

        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }

    class Pos{
        int id,x,y;

        public Pos(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    class BIT {
        int[] tree;
        int[] idRec;
        int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n];
            Arrays.fill(this.tree, Integer.MAX_VALUE);
            this.idRec = new int[n];
            Arrays.fill(this.idRec, -1);
        }

        public int lowBit(int k) {
            return k & (-k);
        }

        public void update(int pos, int val, int id) {
            while (pos > 0) {
                if (tree[pos] > val) {
                    tree[pos] = val;
                    idRec[pos] = id;
                }
                pos -= lowBit(pos);
            }
        }

        public int query(int pos) {
            int minVal = Integer.MAX_VALUE;
            int j = -1;
            while (pos < n) {
                if (minVal > tree[pos]) {
                    minVal = tree[pos];
                    j = idRec[pos];
                }
                pos += lowBit(pos);
            }
            return j;
        }
    }


    List<Edge> edges=new ArrayList<>();
    Pos[] pos;

    public int minCostConnectPoints1(int[][] points) {
        int n = pos.length;
        solve(points, n);
        DisJoinSetUnion dus = new DisJoinSetUnion(n);
        Collections.sort(edges, Comparator.comparingInt(o1 -> o1.len));
        int ret = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (dus.unionSet(x, y)) {
                ret += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return ret;
    }

    public void solve(int[][] points, int n) {
        pos = new Pos[n];
        for (int i = 0; i < n; i++) {
            pos[i] = new Pos(i, points[i][0], points[i][1]);
        }
        build(n);
        for (int i = 0; i < n; i++) {
            int temp = pos[i].x;
            pos[i].x = pos[i].y;
            pos[i].y = temp;
        }
        build(n);
        for (int i = 0; i < n; i++) {
            pos[i].x = -pos[i].x;
        }
        build(n);
        for (int i = 0; i < n; i++) {
            int temp = pos[i].x;
            pos[i].x = pos[i].y;
            pos[i].y = temp;
        }
        build(n);
    }

    public void build(int n) {
        Arrays.sort(pos, (p1, p2) -> p1.x == p2.x ? p1.y - p2.y : p1.x - p2.x);
        int[] a = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = pos[i].y - pos[i].x;
            set.add(pos[i].y - pos[i].x);
        }
        int num = set.size();
        int[] b = new int[num];
        int index = 0;
        for (int element : set) {
            b[index++] = element;
        }
        Arrays.sort(b);
        BIT bit = new BIT(num + 1);
        for (int i = n - 1; i >= 0; i--) {
            int poss = binarySearch(b, a[i]) + 1;
            int j = bit.query(poss);
            if (j != -1) {
                int dis = Math.abs(pos[i].x - pos[j].x) + Math.abs(pos[i].y - pos[j].y);
                edges.add(new Edge(dis, pos[i].id, pos[j].id));
            }
            bit.update(poss, pos[i].x + pos[i].y, i);
        }
    }

    public int binarySearch(int[] array, int target) {
        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int num = array[mid];
            if (num < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
