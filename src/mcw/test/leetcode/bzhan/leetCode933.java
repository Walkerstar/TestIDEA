package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 * <p>
 * 请你实现 RecentCounter 类：
 *
 * <li>RecentCounter() 初始化计数器，请求数为 0 。</li>
 * <li>int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。
 * 确切地说，返回在 [t-3000, t] 内发生的请求数。</li>
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 *
 * @author mcw 2022/5/6 11:56
 */
public class leetCode933 {

    /**
     * 方法一：队列
     * 我们可以用一个队列维护发生请求的时间，当在时间 t 收到请求时，将时间 t 入队。
     * <p>
     * 由于每次收到的请求的时间都比之前的大，因此从队首到队尾的时间值是单调递增的。当在时间 t 收到请求时，
     * 为了求出 [t-3000,t] 内发生的请求数，我们可以不断从队首弹出早于 t-3000 的时间。
     * 循环结束后队列的长度就是 [t-3000,t] 内发生的请求数。
     */
    class recentCounter {
        Queue<Integer> queue;

        public recentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }
}
