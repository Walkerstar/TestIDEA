package mcw.test.leetcode.bzhan;

/**
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 * <p>
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 * <p>
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 *
 * @author mcw 2022/4/19 11:45
 */
public class leetCode821 {

    /**
     * 方法一：两次遍历
     * 问题可以转换成，对 s 的每个下标 i，求
     * <p>
     * s[i] 到其左侧最近的字符 c 的距离
     * s[i] 到其右侧最近的字符 c 的距离
     * 这两者的最小值。
     * <p>
     * 对于前者，我们可以从左往右遍历 s，若 s[i]=c 则记录下此时字符 c 的的下标 idx。遍历的同时更新 answer[i]=i−idx。
     * 对于后者，我们可以从右往左遍历 s，若 s[i]=c 则记录下此时字符 c 的的下标 idx。遍历的同时更新 answer[i]=min(answer[i],idx−i)。
     * <p>
     * 代码实现时，在开始遍历的时候 idx 可能不存在，为了简化逻辑，我们可以用 −n 或 2n 表示，这里 n 是 s 的长度。
     */
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        for (int i = 0, idx = -n; i < n; i++) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }

        for (int i = n - 1, idx = 2 * n; i >= 0; i--) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }
}
