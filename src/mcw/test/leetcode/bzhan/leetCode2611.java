package mcw.test.leetcode.bzhan;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
 * <p>
 * 下标为 i 处的奶酪被吃掉的得分为：
 * <p>
 * 如果第一只老鼠吃掉，则得分为 reward1[i] 。
 * 如果第二只老鼠吃掉，则得分为 reward2[i] 。
 * 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。
 * <p>
 * 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。
 * <p>
 * <p>
 * 示例 1：
 * 输入：reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
 * 输出：15
 * 解释：这个例子中，第一只老鼠吃掉第 2 和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
 * 总得分为 4 + 4 + 3 + 4 = 15 。
 * 15 是最高得分。
 * <p>
 * 示例 2：
 * 输入：reward1 = [1,1], reward2 = [1,1], k = 2
 * 输出：2
 * 解释：这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
 * 总得分为 1 + 1 = 2 。
 * 2 是最高得分。
 * <p>
 * 提示：
 * <p>
 * 1 <= n == reward1.length == reward2.length <= 10^5
 * 1 <= reward1[i], reward2[i] <= 1000
 * 0 <= k <= n
 *
 * @author MCW 2023/6/7
 */
public class leetCode2611 {
    /**
     * 方法一：贪心 + 排序
     * 有 n 块不同类型的奶酪，分别位于下标 0 到 n−1。
     * 下标 i 处的奶酪被第一只老鼠吃掉的得分为 reward_1[i]，被第二只老鼠吃掉的得分为 reward_2[i]。
     * <p>
     * 如果 n 块奶酪都被第二只老鼠吃掉，则得分为数组 reward_2 的元素之和，记为 sum。
     * 如果下标 i 处的奶酪被第一只老鼠吃掉，则得分的变化量是 reward_1[i] − reward_2[i] 。
     * <p>
     * 创建长度为 n 的数组 diffs，其中 diffs[i]=reward_1[i] − reward_2[i]。
     * 题目要求计算第一只老鼠恰好吃掉 k 块奶酪的情况下的最大得分，
     * 假设第一只老鼠吃掉的 k 块奶酪的下标分别是 i_1 到 i_k ，则总得分为：
     * j=1
     * sum + ∑ diffs[i_j]
     * k
     * <p>
     * 其中 sum 为确定的值。
     * 根据贪心思想，为了使总得分最大化，应使下标 i_1 到 i_k 对应的 diffs 的值最大，即应该选择 diffs 中的 k 个最大值。
     * <p>
     * 贪心思想的正确性说明如下：
     * 假设下标 i_1 到 i_k 对应的 diffs 的值不是最大的 k 个值，
     * 则一定存在下标 i_j 和下标 p 满足 diffs[p] ≥ diffs[i_j] 且 p 不在 i-1 到 i_k 的 k 个下标中，
     * 将 diffs[i_j] 替换成 diffs[p] 之后的总得分不变或增加。
     * 因此使用贪心思想可以使总得分最大。
     * <p>
     * 具体做法是，将数组 diffs 排序，然后计算 sum 与数组 diffs 的 k 个最大值之和，即为第一只老鼠恰好吃掉 k 块奶酪的情况下的最大得分。
     */
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int ans = 0;
        int n = reward1.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            ans += reward2[i];
            diffs[i] = reward1[i] - reward2[i];
        }
        Arrays.sort(diffs);
        for (int i = 1; i <= k; i++) {
            ans += diffs[n - i];
        }
        return ans;
    }

    /**
     * 方法二：贪心 + 优先队列
     * 方法一当中，计算最大得分的做法是创建长度为  n 的数组  diffs，
     * 其中 diffs[i] = reward_1[i] − reward_2[i]，将数组  diffs 排序之后计算  sum 与数组 diffs 的 k 个最大值之和。
     * 也可以使用优先队列存储数组  diffs 中的 k 个最大值，优先队列的队首元素为最小元素，优先队列的空间是 O(k)。
     * <p>
     * 用 sum 表示数组 reward_2 的元素之和。同时遍历数组  reward_1 和 reward_2  ，当遍历到下标  i 时，执行如下操作。
     * <p>
     * 1. 将 reward_1[i] − reward_2[i] 添加到优先队列。
     * 2. 如果优先队列中的元素个数大于 k，则取出优先队列的队首元素，确保优先队列中的元素个数不超过 k。
     * <p>
     * 遍历结束时，优先队列中有 k 个元素，为数组 reward_1 和 reward_2 的 k 个最大差值。
     * 计算 sum 与优先队列中的 k 个元素之和，即为第一只老鼠恰好吃掉 k 块奶酪的情况下的最大得分。
     */
    public int miceAndCheese2(int[] reward1, int[] reward2, int k) {
        int ans = 0;
        int n = reward1.length;
        var pq = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            ans += reward2[i];
            pq.offer(reward1[i] - reward2[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }
}
