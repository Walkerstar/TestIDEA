package mcw.test.leetcode.bzhan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
 * <p>
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * <p>
 * 例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
 * <p>
 * 实现 StockSpanner 类：
 * StockSpanner() 初始化类对象。
 * int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 * <p>
 * <p>
 * 示例：
 * 输入：
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * 输出：
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * <p>
 * 解释：
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // 返回 1
 * stockSpanner.next(80);  // 返回 1
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(70);  // 返回 2
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
 * stockSpanner.next(85);  // 返回 6
 * <p>
 * 提示：
 * <p>
 * 1 <= price <= 10^5
 * 最多调用 next 方法 10^4 次
 *
 * @author MCW 2023/10/7
 */
public class leetCode901 {

    /**
     * 方法一：单调栈
     * 思路
     * <p>
     * 调用 next 时，输入是新的一天的股票价格，需要返回包含此日在内的，往前数最多有连续多少日的股票价格是小于等于今日股票价格的。
     * 如果把每日的 price 当成数组不同下标的值，即需要求出每个值与上一个更大元素之间的下标之差。
     * 这种题目可以用单调栈求解，具体原理可以参考「496. 下一个更大元素 I 的官方题解的方法二」。
     * 此题的具体解法上，栈的元素可以是股票价格的下标（即天数）和股票价格的二元数对，并且在栈中先插入一个最大值作为天数为 −1 天的价格，来保证栈不会为空。
     * 调用 next 时，先将栈中价格小于等于此时 price 的元素都弹出，直到遇到一个大于 price 的值，并将 price 入栈，计算下标差返回。
     */
    class StockSpanner {
        Deque<int[]> stack;
        int idx;

        public StockSpanner() {
            stack = new ArrayDeque<>();
            stack.push(new int[]{-1, Integer.MAX_VALUE});
            idx = -1;
        }

        public int next(int price) {
            idx++;
            while (price >= stack.peek()[1]) {
                stack.pop();
            }
            int ret = idx - stack.peek()[0];
            stack.push(new int[]{idx, price});
            return ret;
        }
    }


}
