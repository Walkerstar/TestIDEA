package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 * <p>
 * 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：barcodes = [1,1,1,2,2,2]
 * 输出：[2,1,2,1,2,1]
 * 示例 2：
 * <p>
 * 输入：barcodes = [1,1,1,1,2,2,3,3]
 * 输出：[1,3,1,3,2,1,2,1]
 * <p>
 * 提示：
 * <p>
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 *
 * @author MCW 2023/5/14
 */
public class leetCode1054 {
    /**
     * 方法一：最大堆
     * 思路
     * <p>
     * 题目要求重新排列这些条形码，使其中任意两个相邻的条形码不能相等，可以返回任何满足该要求的答案，并且此题保证存在答案。
     * 我们首先想到的思路就是，找到剩余数量最多的元素，尽可能优先排列它。
     * <p>
     * 我们首先统计 barcodes 每个元素的个数，然后遍历这个频数表，把每个元素的 (剩余数量， 元素值) 二元数组，依次插入最大堆。
     * 这样操作后，堆顶的元素就是剩余数量最多的元素。
     * <p>
     * 然后我们每次从堆顶拿出一个剩余最多的元素，放入排列中，再更新剩余数量，重新放入最大堆中。
     * 如果这个元素和排列结果中的最后一个元素相同，那么我们就需要再从最大堆中取出第二多的元素，放入排列中，之后再把这两个元素放回最大堆中。
     * <p>
     * 依次重复上面的操作，直到我们把所有元素都重新排列。
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int b : barcodes) {
            if (!count.containsKey(b)) {
                count.put(b, 0);
            }
            count.put(b, count.get(b) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            pq.offer(new int[]{entry.getValue(), entry.getKey()});
        }
        int n = barcodes.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int[] p = pq.poll();
            int cx = p[0], x = p[1];
            if (i == 0 || res[i - 1] != x) {
                res[i] = x;
                if (cx > 1) {
                    pq.offer(new int[]{cx - 1, x});
                }
            } else {
                int[] p2 = pq.poll();
                int cy = p2[0], y = p2[1];
                res[i] = y;
                if (cy > 1) {
                    pq.offer(new int[]{cy - 1, y});
                }
                pq.offer(new int[]{cx, x});
            }
        }
        return res;
    }

    /**
     * 方法二：计数统计
     * 思路
     * <p>
     * 通过观察我们可以发现，出现次数最多的元素，会始终在最大堆的顶部，我们实际上并不需要关心其他元素的相对大小顺序。
     * 在这个思路上可以进行优化，先统计所有元素的频数，找到出现次数最多的元素，然后将出现次数最多的元素交替排列。
     * <p>
     * 这个方法在实现过程，会应用不常规的小技巧，具体证明过程可以参考题目「767. 重构字符串的官方题解」的方法二。
     * <p>
     * 首先统计每个元素的出现次数，然后根据每个元素的出现次数重构数组。
     * <p>
     * 当 n 是奇数且出现最多的元素的出现次数是 (n+1)/2 时，出现次数最多的元素必须全部放置在偶数下标，否则一定会出现相邻的元素相同的情况。
     * 其余情况下，每个元素放置在偶数下标或者奇数下标都是可行的。
     * <p>
     * 维护偶数下标 evenIndex 和奇数下标oddIndex，初始值分别为 0 和 1。遍历每个元素，根据每个元素的出现次数判断元素应该放置在偶数下标还是奇数下标。
     * <p>
     * 首先考虑是否可以放置在奇数下标。根据上述分析可知，只要元素的出现次数不超过数组的长度的一半（即出现次数小于或等于 ⌊ n/2 ⌋），
     * 就可以放置在奇数下标，只有当元素的出现次数超过数组的长度的一半时，才必须放置在偶数下标。
     * <p>
     * 元素的出现次数超过数组的长度的一半只可能发生在 n 是奇数的情况下，且最多只有一个元素的出现次数会超过数组的长度的一半。
     * <p>
     * 因此通过如下操作在重构的数组中放置元素。
     * <p>
     * 如果元素的出现次数大于 0 且小于或等于 ⌊ n/2 ⌋，且 oddIndex 没有超出数组下标范围，则将元素放置在 oddIndex，然后将 oddIndex 的值加 2。
     * <p>
     * 如果元素的出现次数大于 ⌊ n/2 ⌋，或  oddIndex 超出数组下标范围，则将元素放置在  evenIndex，然后将  evenIndex 的值加  2。
     * <p>
     * 如果一个元素出现了多次，则重复上述操作，直到该元素全部放置完毕。
     */
    public static int[] rearrangeBarcodes2(int[] barcodes) {
        int length = barcodes.length;
        if (length < 2) {
            return barcodes;
        }
        Map<Integer, Integer> counts = new HashMap<>();
        int maxCount = 0;
        for (int b : barcodes) {
            counts.put(b, counts.getOrDefault(b, 0) + 1);
            maxCount = Math.max(maxCount, counts.get(b));
        }
        int evenIndex = 0;
        int oddIndex = 1;
        int halfLength = length / 2;
        int[] res = new int[length];
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int x = entry.getKey();
            int count = entry.getValue();
            //如果该数的个数小于数组的一半长度，则先从 奇数索引 开始放入
            while (count > 0 && count <= halfLength && oddIndex < length) {
                res[oddIndex] = x;
                count--;
                oddIndex += 2;
            }
            //若 该数的个数大于数组的一半长度， 则 先从 偶数索引 开始放入
            // 此处，存在数组越界异常，若该数的个数过大，则下标索引会越界 。虽然这种情况一定不会符合题目的要求。
            while (count > 0) {
                res[evenIndex] = x;
                count--;
                evenIndex += 2;
            }
        }
        return res;
    }

}
