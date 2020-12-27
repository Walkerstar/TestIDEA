package mcw.test.leetcode.niuke;

/**
 * @author mcw 2020\3\21 0021-16:45
 * 假设你有一个数组，其中第 i 个元素是某只股票在第 i 天的价格。
 * 设计一个算法来求最大的利润。最多只能完成**一次**交易
 */
public class Test30 {

    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for (int i = 0; i < prices.length; i++) {
            buy = Math.max(buy, -prices[i]);
            sell = Math.max(buy, prices[i] + buy);
        }
        return sell;
    }
}
