package mcw.test.leetcode.bzhan;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * @author mcw 2021/2/3 20:59
 */
public class leetCode480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.getMedian();
        for (int i = k; i < nums.length; i++) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            ans[i - k + 1] = dh.getMedian();
        }
        return ans;
    }

    class DualHeap {
        //大根堆，维护较小的一半元素
        private PriorityQueue<Integer> small;

        //小根堆，维护较小的一半元素
        private PriorityQueue<Integer> large;

        //哈希表，记录 [ 延迟删除] 的元素，key 为元素，value 为需要删除的次数
        private Map<Integer, Integer> delayed;

        private int k;

        //small 和 large 当前包含的元素个数，需要扣除被 [延迟删除] 的元素
        private int smallSize, largeSize;

        public DualHeap(int k) {
            this.small = new PriorityQueue<>(Comparator.reverseOrder()/*or (o1, o2) -> o2.compareTo(o1)*/);
            this.large = new PriorityQueue<>(Integer::compareTo/*or (o1, o2) -> o1.compareTo(o2)*/);
            this.delayed = new HashMap<>();
            this.k = k;
            this.smallSize = 0;
            this.largeSize = 0;
        }

        public double getMedian() {
            return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
        }

        public void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                ++smallSize;
            } else {
                large.offer(num);
                ++largeSize;
            }
            makeBalance();
        }

        public void erase(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            if (num <= small.peek()) {
                --smallSize;
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                --largeSize;
                if (num == large.peek()) {
                    prune(large);
                }
            }
            makeBalance();
        }

        // 不断地弹出 heap 的堆顶元素，并且更新哈希表
        private void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                int num = heap.peek();
                if (delayed.containsKey(num)) {
                    delayed.put(num, delayed.get(num) - 1);
                    if (delayed.get(num) == 0) {
                        delayed.remove(num);
                    }
                    heap.poll();
                } else {
                    break;
                }
            }
        }

        // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
        private void makeBalance() {
            if (smallSize > largeSize + 1) {
                //small 比 large 元素多 2 个
                large.offer(small.poll());
                --smallSize;
                --largeSize;
                //small 堆顶元素被移除，需要进行 prune
                prune(small);
            } else if (smallSize < largeSize) {
                //large 比 small 元素多 1 个
                small.offer(large.poll());
                ++smallSize;
                --largeSize;
                //// large 堆顶元素被移除，需要进行 prune
                prune(large);
            }
        }
    }
}
