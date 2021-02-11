package mcw.test.leetcode.bzhan;

import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p></p>
 * 请实现 KthLargest 类：
 *<br>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。<br>
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 * @author mcw 2021/2/11 13:45
 */
public class leetCode703 {

    class KthLargest {
        /*private int[] nums = new int[20001];
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int i : nums) {
                this.nums[i + 10000]++;
            }
        }

        public int add(int val) {
            nums[val + 10000]++;
            int count = 0;
            for (int i = 20000; i >= 0; i--) {
                count += nums[i];
                if (count >= k) {
                    return i - 10000;
                }
            }
            return -1;
        }*/

        /**
         * 我们可以使用一个大小为 k 的优先队列来存储前 k 大的元素，其中优先队列的队头为队列中最小的元素，也就是第 k 大的元素。
         *
         * 在单次插入的操作中，我们首先将元素 val 加入到优先队列中。如果此时优先队列的大小大于 k，我们需要将优先队列的队头元素弹出，
         * 以保证优先队列的大小为 k。
         */
        PriorityQueue<Integer> pq;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<>();
            for (int num : nums) {
                add(num);
            }
        }

        private int add(int val) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }
    }
}
