package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * <p>
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * <p>
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * <p>
 * 请你返回「表现良好时间段」的最大长度。
 * <p>
 * 提示：
 *
 * <li>1 <= hours.length <= 10^4</li>
 * <li>0 <= hours[i] <= 16</li>
 *
 * @author mcw 2023/2/14 11:00
 */
public class leetCode1124 {

    /**
     * 贪心
     * <p>
     * 整个求解过程如下：
     * <p>
     * 我们遍历整个 s，求出维护递减序列的栈 stk，注意它并不是我们通常意义上的单调栈。
     * 倒序遍历 r，对于每个 r：
     * 如果当前 stk 不为空并且栈顶元素小于 s[r]，我们设栈顶元素在原数组的下标为 l，用 r−l 更新答案，再令栈顶元素出栈。
     * 该过程不断循环直到条件不被满足。否则，继续考虑下一个 r。
     * 这样做的正确性在于：
     * <p>
     * 1.如果有 r1 < r2，并且 s[r1] > s[r2]，那么 r1 所匹配的左端点 l1 和 r2  所匹配的左端点 l2 一定有 l1 < l2 。
     * 在 stk 中， l2 相比 l1 更靠近栈顶，倘若求解 l2 的过程中弹出了某些元素，也不会影响 l1 的求解。对于 l1 = l2 的情况，
     * 由于此时满足 r2 - l2 > r1 - l1，因此我们将 l2 弹出栈也不会影响最终答案的求解。
     * 2.如果有 r1 < r2，并且 s[r1] ≤ s[r2]，那么 r1 永远不会成为最优答案的右端点。
     * 至此，我们通过维护一个栈 stk，倒序遍历 r 求解可能成为最优区间的左端点 l，在 O(n) 的时间复杂度内得到答案。
     */
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] s = new int[n + 1];
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(0);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (s[stk.peek()] > s[i]) {
                stk.push(i);
            }
        }
        int res = 0;
        for (int i = n; i >= 1; i--) {
            while (!stk.isEmpty() && s[stk.peek()] < s[i]) {
                res = Math.max(res, i - stk.pop());
            }
        }
        return res;
    }

    /**
     * 哈希表
     * <p>
     * 在方法一中，我们记工作小时数大于 8 的为 1 分，小于等于 8 的为 −1 分，
     * 原问题由求解最长的「表现良好的时间段」长度转变为求解分数和大于 0 的最长区间长度。
     * <p>
     * 我们仍然使用前缀和 s，对于某个下标 i（从 00 开始），我们期待找到最小的 (j < i)，满足 s[j] < s[i]。
     * 接下来，我们按照 s[i] 是否大于 0 来分情况讨论：
     * <p>
     * 1.如果 s[i] > 0，那么前 i + 1 项元素之和大于 0，表示有一个长度为 i + 1 的大于 0 的区间。
     * 2.如果 s[i] < 0，我们在前面试图寻找一个下标 j，满足 s[j] = s[i] - 1。如果有，则表示区间 [j + 1, i] 是我们要找的以 i 结尾的最长区间。
     * <p>
     * 为什么第 2 种情况要找 s[i] - 1，而不是 s[i] - 2 或更小的一项？
     * 因为在本题中分数只有 1 或者 −1，如果前缀和数组中在 i 之前要出现小于 s[i] 的元素，它的值一定是 s[i] - 1。
     * 也就是说当 s[i] < 0 时，我们要找到 j 使得 s[j] < s[i]，如果有这样的 j 存在，这个 j 一定满足 s[j] = s[i] - 1。
     * <p>
     * 实现过程中，我们可以使用哈希表记录每一个前缀和第一次出现的位置，即可在 O(1)的时间内判断前缀和等于 s[i] - 1 的位置 j 是否存在。
     */
    public int longestWPI2(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        int s = 0, res = 0;
        for (int i = 0; i < n; i++) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                res = Math.max(res, i + 1);
            } else {
                if (map.containsKey(s - 1)) {
                    res = Math.max(res, i - map.get(s - 1));
                }
            }
            if (!map.containsKey(s)) {
                map.put(s, i);
            }
        }
        return res;
    }

}
