package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 * <p>
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 *
 * @author MCW 2022/4/23
 */
public class leetCode587 {
    /**
     * Jarvis 算法
     */
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        int leftMost = 0;
        for (int i = 0; i < n; i++) {
            if (trees[i][0] < trees[leftMost][0]) {
                leftMost = i;
            }
        }

        List<int[]> res = new ArrayList<int[]>();
        boolean[] visit = new boolean[n];
        int p = leftMost;
        do {
            int q = (p + 1) % n;
            for (int r = 0; r < n; r++) {
                /* 如果 r 在 pq 的右侧，则 q = r */
                if (cross(trees[p], trees[q], trees[r]) < 0) {
                    q = r;
                }
            }
            /* 是否存在点 i, 使得 p 、q 、i 在同一条直线上 */
            for (int i = 0; i < n; i++) {
                if (visit[i] || i == p || i == q) {
                    continue;
                }
                if (cross(trees[p], trees[q], trees[i]) == 0) {
                    res.add(trees[i]);
                    visit[i] = true;
                }
            }
            if (!visit[q]) {
                res.add(trees[q]);
                visit[q] = true;
            }
            p = q;
        } while (p != leftMost);
        return res.toArray(new int[][]{});
    }

    public int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }

    /**
     * Graham 算法
     */
    public int[][] outerTrees2(int[][] tress) {
        int n = tress.length;
        if (n < 4) {
            return tress;
        }
        int bottom = 0;
        //找到 y 最小的点 bottom
        for (int i = 0; i < n; i++) {
            if (tress[i][1] < tress[bottom][1]) {
                bottom = i;
            }
        }
        swap(tress, bottom, 0);
        //以 bottom 原点，按照极坐标的角度大小进行排序
        Arrays.sort(tress, 1, n, (a, b) -> {
            int diff = cross(tress[0], a, b) - cross(tress[0], b, a);
            if (diff == 0) {
                return distance(tress[0], a) - distance(tress[0], b);
            } else {
                return -diff;
            }
        });
        //对于凸包最后且在同一条直线的元素按照距离从小到大进行排序
        int r = n - 1;
        while (r > 0 && cross(tress[0], tress[n - 1], tress[r]) == 0) {
            r--;
        }
        for (int l = r + 1, h = n - 1; l < h; l++, h--) {
            r--;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        stack.push(1);
        for (int i = 2; i < n; i++) {
            int top = stack.pop();
            //如果当前元素与栈顶的两个元素构成的向量顺时针旋转，则弹出栈顶元素
            while (!stack.isEmpty() && cross(tress[stack.peek()], tress[top], tress[i]) < 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(i);
        }
        int size = stack.size();
        int[][] res = new int[size][2];
        for (int i = 0; i < size; i++) {
            res[i] = tress[stack.pop()];
        }
        return res;
    }

    public int distance(int[] p, int[] q) {
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }

    public void swap(int[][] tress, int i, int j) {
        int temp0 = tress[i][0];
        int temp1 = tress[i][1];
        tress[i][0] = tress[j][0];
        tress[i][1] = tress[j][1];
        tress[j][0] = temp0;
        tress[j][1] = temp1;
    }


    /**
     * Andrew 算法
     */
    public int[][] outerTrees3(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        /* 按照 x 大小进行排序，如果 x 相同，则按照 y 的大小进行排序 */
        Arrays.sort(trees, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<Integer> hull = new ArrayList<Integer>();
        boolean[] used = new boolean[n];
        /* hull[0] 需要入栈两次，不进行标记 */
        hull.add(0);
        /* 求出凸包的下半部分 */
        for (int i = 1; i < n; i++) {
            while (hull.size() > 1 && cross(trees[hull.get(hull.size() - 2)], trees[hull.get(hull.size() - 1)], trees[i]) < 0) {
                used[hull.get(hull.size() - 1)] = false;
                hull.remove(hull.size() - 1);
            }
            used[i] = true;
            hull.add(i);
        }
        int m = hull.size();
        /* 求出凸包的上半部分 */
        for (int i = n - 2; i >= 0; i--) {
            if (!used[i]) {
                while (hull.size() > m && cross(trees[hull.get(hull.size() - 2)], trees[hull.get(hull.size() - 1)], trees[i]) < 0) {
                    used[hull.get(hull.size() - 1)] = false;
                    hull.remove(hull.size() - 1);
                }
                used[i] = true;
                hull.add(i);
            }
        }
        /* hull[0] 同时参与凸包的上半部分检测，因此需去掉重复的 hull[0] */
        hull.remove(hull.size() - 1);
        int size = hull.size();
        int[][] res = new int[size][2];
        for (int i = 0; i < size; i++) {
            res[i] = trees[hull.get(i)];
        }
        return res;
    }

    public static void main(String[] args) {
        leetCode587 obj = new leetCode587();
        System.out.println(Arrays.deepToString(obj.outerTrees(new int[][]{{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}})));
    }
}
