package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 * <p>
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 * <p>
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 * <p>
 * MyCalendarThree() 初始化对象。
 * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 *
 * @author mcw 2022/6/6 10:50
 */
public class leetCode732 {

    /**
     * 方法一：差分数组
     * 思路与算法
     * <p>
     * 可以参考「731. 我的日程安排表 II」的解法二，我们可以采用同样的思路即可。利用差分数组的思想，
     * 每当我们预定一个新的日程安排 [start,end)，
     * 在 start 计数 cnt[start] 加 1，表示从 start 预定的数目加 1；
     * 从 end 计数 cnt[end] 减 1，表示从 end 开始预定的数目减 1。
     * 此时以起点 x 开始的预定的数目 book_x=∑_(y≤x) cnt[y]，我们对计数进行累加依次求出最大的预定数目即可。
     * 由于本题中 start,end 数量较大，我们利用 TreeMap 计数即可。
     */
    class MyCalenderThree {
        private TreeMap<Integer, Integer> cnt;

        public MyCalenderThree() {
            cnt = new TreeMap<>();
        }

        public int book(int start, int end) {
            int ans = 0;
            int maxBook = 0;
            cnt.put(start, cnt.getOrDefault(start, 0) + 1);
            cnt.put(end, cnt.getOrDefault(end, 0) - 1);
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int freq = entry.getValue();
                maxBook += freq;
                ans = Math.max(maxBook, ans);
            }
            return ans;
        }
    }

    /**
     * 方法二：线段树
     * 思路与算法
     * <p>
     * 利用线段树，假设我们开辟了数组 arr[0,⋯,10^9]，初始时每个元素的值都为 0，
     * 对于每次行程预定的区间 [start,end) ，则我们将区间中的元素 arr[start,⋯,end−1] 中的每个元素加 1，最终只需要求出数组 arr 的最大元素即可。
     * 实际我们不必实际开辟数组 arr，可采用动态线段树，懒标记 lazy 标记区间 [l,r] 进行累加的次数，tree 记录区间 [l,r] 的最大值，最终返回区间 [0,10^9]中的最大元素即可。
     */
    class MyCalendarTree1 {
        private Map<Integer, Integer> tree;
        private Map<Integer, Integer> lazy;

        public MyCalendarTree1() {
            tree = new HashMap<>();
            lazy = new HashMap<>();
        }

        public int book(int start, int end) {
            update(start, end - 1, 0, 1000000000, 1);
            return tree.getOrDefault(1, 0);
        }

        public void update(int start, int end, int l, int r, int idx) {
            if (r < start || end < l) {
                return;
            }
            if (start <= 1 && r <= end) {
                tree.put(idx, tree.getOrDefault(idx, 0) + 1);
                lazy.put(idx, lazy.getOrDefault(idx, 0) + 1);
            } else {
                int mid = (l + r) >> 1;
                update(start, end, l, mid, 2 * idx);
                update(start, end, mid + 1, r, 2 * idx + 1);
                tree.put(idx, lazy.getOrDefault(idx, 0) +
                        Math.max(tree.getOrDefault(2 * idx, 0), tree.getOrDefault(2 * idx + 1, 0)));
            }
        }
    }
}
