package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。
 * 也就是说 points[i] = [xi, yi] ，并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。
 * <p>
 * 请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。
 * <p>
 * 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
 * <p>
 * 示例 1：
 * 输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * 输出：4
 * 解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
 * 没有其他满足条件的点，所以返回 4 和 1 中最大的那个。
 * <p>
 * 示例 2：
 * 输入：points = [[0,0],[3,0],[9,2]], k = 3
 * 输出：3
 * 解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= points.length <= 10^5
 * points[i].length == 2
 * -10^8 <= points[i][0], points[i][1] <= 10^8
 * 0 <= k <= 2 * 10^8
 * 对于所有的1 <= i < j <= points.length ，points[i][0] < points[j][0] 都成立。也就是说，xi 是严格递增的。
 *
 * @author MCW 2023/7/21
 */
public class leetCode1499 {


    /**
     * 方法一：堆
     * 思路
     * <p>
     * 题目要求 yi + yj + |xi-xj|，其中 ∣xi-xj∣ ≤ k 的最大值。
     * 根据题目条件， i < j 时，xi < xj，可以拆去绝对值符号，得到 (-xi + yi) + (xj + yj)，其中 xj - xi ≤ k。
     * <p>
     * 根据这个等式，可以遍历 points 所有点，使每个点作为 [xj,yj]，并且求出满足 xj - xi ≤ k 的最大的 (-xi + yi)，而这一步，可以用堆来完成。
     * 用一个最小堆，堆的元素是 [x−y,x]，堆顶元素的  (x−y) 值最小，即 (−x+y) 值最大。
     * 每次遍历一个点时，先弹出堆顶不满足当前 xj-xi ≤ k 的元素，然后用堆顶元素和当前元素计算 (-xi + yi) + (xj + yj)，再将当前元素放入堆。
     * <p>
     * 遍历完后，即得到了式子的最大值。
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
        int res = Integer.MIN_VALUE;
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!heap.isEmpty() && x - heap.peek()[1] > k) {
                heap.poll();
            }
            if (!heap.isEmpty()) {
                res = Math.max(res, x + y - heap.peek()[0]);
            }
            heap.offer(new int[]{x - y, x});
        }
        return res;
    }

    /**
     * 方法二：双端队列
     * 思路
     * <p>
     * 与方法一思路类似，方法一用堆来求满足 xj-xi ≤ k 的最大的 (-xi + yi)，而这一步可以用双端队列来求，从而降低时间复杂度。
     * 使用一个双端队列 q，元素为 [y−x,x]。每次遍历一个点时，首先同样要弹出队列头部不满足 xj-xi ≤ k 的元素，
     * 然后用队列头部元素和当前元素计算  (yi-xi) + (xj+yj)。
     * <p>
     * 在当前元素进入队列尾端时，需要弹出队列末端小于等于当前 yj-xj 的元素，这样以来，可以使得双端队列保持递减，从而头部元素最大。
     * <p>
     * 然后将当前元素压入队列末端。遍历完后，即得到了式子的最大值。
     */
    public int findMaxValueOfEquation2(int[][] points, int k) {
        int res = Integer.MIN_VALUE;
        Deque<int[]> queue = new ArrayDeque<int[]>();
        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!queue.isEmpty() && x - queue.peekFirst()[1] > k) {
                queue.pollFirst();
            }
            if (!queue.isEmpty()) {
                res = Math.max(res, x + y + queue.peekFirst()[0]);
            }
            while (!queue.isEmpty() && y - x >= queue.peekLast()[0]) {
                queue.poll();
            }
            queue.offer(new int[]{y - x, x});
        }
        return res;
    }
}
