package mcw.test.leetcode.niuke;

/**
 * @author mcw 2020\3\14 0014-19:45
 *
 * 假设你有一个数组，其中第 i 个元素是某只股票在第 i 天的价格。
 * 设计一个算法来求最大的利润。 最多可以进行两次交易。  注意：  不能同时进行多个交易（即，必须在再次购买之前出售之前的股票）
 */
public class Test28 {


    public static int maxProfit(int[] arr) {
        int min1 = Integer.MIN_VALUE, min2 = Integer.MIN_VALUE, max1 = 0, max2 = 0;

        for (int price : arr) {
            min1 = Math.max(min1, -price);      //第一次买入
            max1 = Math.max(max1, min1 + price);   //第一次卖出
            min2 = Math.max(min2, max1 - price);   //第二次买入
            max2 = Math.max(max2, min2 + price);   //第二次卖出
        }
        return max2;
    }


    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{-1, -8, 7, 4, 2,}));
    }
}
