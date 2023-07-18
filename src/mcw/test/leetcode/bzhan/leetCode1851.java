package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 lefti 、结束于 righti（包含两侧取值，闭区间）。
 * 区间的 长度 定义为区间中包含的整数数目，更正式地表达是 righti - lefti + 1 。
 * <p>
 * 再给你一个整数数组 queries 。第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小区间 i 的长度 。如果不存在这样的区间，那么答案是 -1 。
 * <p>
 * 以数组形式返回对应查询的所有答案。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * 输出：[3,3,1,4]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
 * - Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
 * <p>
 * 示例 2：
 * 输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * 输出：[2,-1,4,6]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
 * - Query = 19：不存在包含 19 的区间，答案为 -1 。
 * - Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
 * - Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 *
 * @author MCW 2023/7/18
 */
public class leetCode1851 {

    /**
     * 方法一：排序 + 离线查询 + 优先队列（小根堆）
     * <p>
     * 我们注意到，题目中查询的顺序并不会影响答案，并且涉及到的区间也不会发生变化，
     * 因此，我们考虑将所有的查询按照从小到大的顺序进行排序，同时将所有的区间按照左端点从小到大的顺序进行排序。
     * <p>
     * 我们使用一个优先队列（小根堆） pq 来维护当前所有的区间，
     * 队列的每个元素是一个二元组 (v,r)，表示一个区间长度为 v，右端点为 r 的区间。
     * 初始时，优先队列为空。另外，我们定义一个指针 i，指向当前遍历到的区间，初始时 i=0。
     * <p>
     * 我们按照从小到大的顺序依次遍历每个查询 (x,j)，并进行如下操作：
     * <p>
     * 1. 如果指针 i 尚未遍历完所有的区间，并且当前遍历到的区间 [a,b] 的左端点小于等于 x，那么我们将该区间加入优先队列中，并将指针 i 后移一位，循环此过程；
     * 2. 如果优先队列不为空，并且堆顶元素的区间右端点小于 x，那么我们将堆顶元素弹出，循环此过程；
     * 3. 此时，如果优先队列不为空，那么堆顶元素就是包含 x 的最小区间。我们将其长度 v 加入答案数组 ans 中。
     * <p>
     * 在上述过程结束后，我们返回答案数组 ans 即可。
     */
    public static int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length;
        int m = queries.length;

        // 按 intervals[0] 从小到大 排序 intervals
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // 存储 queries 的值与下标的对照关系
        int[][] qs = new int[m][0];
        for (int i = 0; i < m; i++) {
            qs[i] = new int[]{queries[i], i};
        }

        // 排序，避免 在 查找 中 重复 遍历 intervals 数组
        Arrays.sort(qs, Comparator.comparingInt(a -> a[0]));

        int[] ans = new int[m];
        // 初始所有数字都未找到，所以 全部填充 -1
        Arrays.fill(ans, -1);

        // 小根堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int i = 0;
        for (int[] q : qs) {
            // 将 左端点 小于等于 查询数字的 区间，全部加入到队列中
            while (i < n && intervals[i][0] <= q[0]) {
                int a = intervals[i][0], b = intervals[i][1];
                // 区间长度，右端点
                pq.offer(new int[]{b - a + 1, b});
                ++i;
            }
            // 这里可以放心弹出，是因为 qs 已经从小到大排序了，
            // 此时队列中的右端点如果小于当前的 q[0] ，则 这些 右端点 肯定也小于 后续的 q[0],所以直接弹出即可
            while (!pq.isEmpty() && pq.peek()[1] < q[0]) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                ans[q[1]] = pq.peek()[0];
            }
        }
        return ans;
    }
}
