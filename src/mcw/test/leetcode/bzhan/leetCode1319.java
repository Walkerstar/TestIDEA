package mcw.test.leetcode.bzhan;

import java.util.Arrays;

/**
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b]
 * 连接了计算机 a 和 b。网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
 * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
 *
 * @author mcw 2021/1/23 19:49
 */
public class leetCode1319 {

    /**
     * 当计算机的数量为 n 时，我们至少需要 n−1 根线才能将它们进行连接。如果线的数量少于 n−1，那么我们无论如何都无法将这 n 台
     * 计算机进行连接。因此如果数组 connections 的长度小于 n−1，我们可以直接返回 −1 作为答案。
     * 合并集合中的电脑，如果成功，则减去 1 根线，当集合中电脑合并完成后，剩余的电脑合并，则需要 [剩余的线的数量-1]根线
     */
    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] connection : connections) {
            unionFind.union(connection[0], connection[1]);
        }
        return unionFind.setCount - 1;
    }

    static class UnionFind {
        int[] parent;
        int[] size;
        int setCount;
        int n;


        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.size = new int[n];
            Arrays.fill(size, 1);
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
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
            --setCount;
            return true;
        }
    }
}
