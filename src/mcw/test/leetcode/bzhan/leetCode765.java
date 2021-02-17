package mcw.test.leetcode.bzhan;

/**
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，
 * 让他们站起来交换座位。
 *
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 *
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 *
 * @author mcw 2021/2/14 20:44
 */
public class leetCode765 {

    public int minSwapsCouples(int[] rows) {
        int len = rows.length;
        int N = len / 2;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2) {
            unionFind.union(rows[i] / 2, rows[i + 1] / 2);
        }
        return N - unionFind.getCount();
    }

    class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (parent[x] = find(parent[x]));
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return;
            }
            parent[x] = y;
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}
