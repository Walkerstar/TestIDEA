package mcw.test.leetcode.bzhan;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。<p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。<p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author mcw 2021/1/11 20:05
 */
public class leetCode123 {

    public int maxProfit(int[] prices){
        int buy1=Integer.MIN_VALUE,buy2= Integer.MIN_VALUE,sell1=0,sell2=0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, price + buy1);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }
}
