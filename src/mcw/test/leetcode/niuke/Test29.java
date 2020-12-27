package mcw.test.leetcode.niuke;

/**
 * @author mcw 2020\3\19 0019-19:06
 *
 * 假设你有一个数组，其中第 i 个元素是某只股票在第 i 天的价格。
 * 设计一个算法来求最大的利润。可以任意次交易
 */
public class Test29 {

    //在波谷买入，波峰卖出，即找出一个**递增**序列
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                res += prices[i] - prices[i - 1];
        }
        return res;
    }
}
