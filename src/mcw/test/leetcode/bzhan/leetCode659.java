package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 分割数组为连续子序列
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，
 * 其中每个子序列都由连续整数组成且长度至少为 3 。如果可以完成上述分割，则返回 true ；
 * 否则，返回 false 。
 *
 * @author mcw 2020\12\4 0004-19:58
 */
public class leetCode659 {
    /**
     * 方法一：哈希表+最小堆
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<>());
            }
            if (map.containsKey(x - 1)) {
                int preLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(preLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二：贪心
     * 1.首先判断是否存在以 x-1 结尾的子序列，即根据第二个哈希表判断 x-1 作为结尾的子序列的数量是否大于 0，如果大于 0，
     * 则将元素 x 加入该子序列中。由于 x 被使用了一次，因此需要在第一个哈希表中将 x 的剩余次数减 1。又由于该子序列的
     * 最后一个数字从 x−1 变成了 x，因此需要在第二个哈希表中将 x−1 作为结尾的子序列的数量减 1，以及将 x 作为结尾的
     * 子序列的数量加 1。
     *
     * 2.否则，x 为一个子序列的第一个数，为了得到长度至少为 3 的子序列，x+1 和 x+2 必须在子序列中，因此需要判断在
     * 第一个哈希表中 x+1 和 x+2 的剩余次数是否都大于 0。
     *   2.1  当 x+1 和 x+2 的剩余次数都大于 0 时，可以新建一个长度为 3 的
     * 子序列 [x,x+1,x+2]。由于这三个数都被使用了一次，因此需要在第一个哈希表中将这三个数的剩余次数分别减 1。又由于
     * 该子序列的最后一个数字是 x+2，因此需要在第二个哈希表中将 x+2 作为结尾的子序列的数量加 1。
     *
     *   2.2  否则，无法得到长度为 3 的子序列 [x,x+1,x+2]，因此无法完成分割，返回 false。
     *
     * 如果整个数组遍历结束时，没有遇到无法完成分割的情况，则可以完成分割，返回 true。
     *
     */
    public boolean isPossible1(int[] nums) {
        // 第一个哈希表存储数组中的每个数字的剩余次数，
        // 第二个哈希表存储数组中的每个数字作为结尾的子序列的数量。
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        //统计每个数出现的次数
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0) + 1;
            countMap.put(x, count);
        }
        for (int x : nums) {
            int count = countMap.getOrDefault(x, 0);
            if (count > 0) {
                int preEndCount = endMap.getOrDefault(x - 1, 0);
                if (preEndCount > 0) {
                    countMap.put(x, count - 1);
                    endMap.put(x - 1, preEndCount - 1);
                    endMap.put(x, endMap.getOrDefault(x, 0) + 1);
                } else {
                    int count1 = countMap.getOrDefault(x + 1, 0);
                    int count2 = endMap.getOrDefault(x + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(x, count - 1);
                        countMap.put(x + 1, count1 - 1);
                        countMap.put(x + 2, count2 - 1);
                        endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
